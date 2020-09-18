/*
 * Copyright 2010-2015 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jetbrains.kotlin.serialization.deserialization.descriptors

import org.jetbrains.kotlin.descriptors.*
import org.jetbrains.kotlin.incremental.components.LookupLocation
import org.jetbrains.kotlin.metadata.ProtoBuf
import org.jetbrains.kotlin.name.ClassId
import org.jetbrains.kotlin.name.Name
import org.jetbrains.kotlin.protobuf.AbstractMessageLite
import org.jetbrains.kotlin.protobuf.MessageLite
import org.jetbrains.kotlin.protobuf.Parser
import org.jetbrains.kotlin.resolve.MemberComparator
import org.jetbrains.kotlin.resolve.scopes.DescriptorKindFilter
import org.jetbrains.kotlin.resolve.scopes.MemberScopeImpl
import org.jetbrains.kotlin.serialization.deserialization.DeserializationContext
import org.jetbrains.kotlin.serialization.deserialization.MemberDeserializer
import org.jetbrains.kotlin.serialization.deserialization.getName
import org.jetbrains.kotlin.storage.getValue
import org.jetbrains.kotlin.utils.Printer
import org.jetbrains.kotlin.utils.addIfNotNull
import org.jetbrains.kotlin.utils.compact
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.util.*

abstract class DeserializedMemberScope protected constructor(
    protected val c: DeserializationContext,
    functionList: List<ProtoBuf.Function>,
    propertyList: List<ProtoBuf.Property>,
    typeAliasList: List<ProtoBuf.TypeAlias>,
    classNames: () -> Collection<Name>
) : MemberScopeImpl() {

    private val helper: DeserializedMemberScopeHelper = createMemberScopeHelper(functionList, propertyList, typeAliasList)

    internal val classNames by c.storageManager.createLazyValue { classNames().toSet() }


    private val classifierNamesLazy by c.storageManager.createNullableLazyValue {
        val nonDeclaredNames = getNonDeclaredClassifierNames() ?: return@createNullableLazyValue null
        this.classNames + helper.typeAliasNames + nonDeclaredNames
    }

    override fun getFunctionNames() = helper.functionNames
    override fun getVariableNames() = helper.variableNames
    override fun getClassifierNames(): Set<Name>? = classifierNamesLazy

    override fun definitelyDoesNotContainName(name: Name): Boolean {
        return name !in helper.functionNames && name !in helper.variableNames && name !in classNames && name !in helper.typeAliasNames
    }

    /**
     * Can be overridden to filter specific declared functions. Not called on non-declared functions.
     */
    protected open fun isDeclaredFunctionAvailable(function: SimpleFunctionDescriptor): Boolean = true

    protected open fun computeNonDeclaredFunctions(name: Name, functions: MutableCollection<SimpleFunctionDescriptor>) {
    }

    override fun getContributedFunctions(name: Name, location: LookupLocation): Collection<SimpleFunctionDescriptor> {
        return helper.getContributedFunctions(name, location)
    }

    protected open fun computeNonDeclaredProperties(name: Name, descriptors: MutableCollection<PropertyDescriptor>) {
    }

    private fun getTypeAliasByName(name: Name): TypeAliasDescriptor? {
        return helper.getTypeAliasByName(name)
    }

    override fun getContributedVariables(name: Name, location: LookupLocation): Collection<PropertyDescriptor> {
        return helper.getContributedVariables(name, location)
    }

    protected fun computeDescriptors(
        kindFilter: DescriptorKindFilter,
        nameFilter: (Name) -> Boolean,
        location: LookupLocation
    ): Collection<DeclarationDescriptor> {
        //NOTE: descriptors should be in the same order they were serialized in
        // see MemberComparator
        val result = ArrayList<DeclarationDescriptor>(0)

        if (kindFilter.acceptsKinds(DescriptorKindFilter.SINGLETON_CLASSIFIERS_MASK)) {
            addEnumEntryDescriptors(result, nameFilter)
        }

        helper.addFunctionsAndPropertiesTo(result, kindFilter, nameFilter, location)

        if (kindFilter.acceptsKinds(DescriptorKindFilter.CLASSIFIERS_MASK)) {
            for (className in classNames) {
                if (nameFilter(className)) {
                    result.addIfNotNull(deserializeClass(className))
                }
            }
        }

        if (kindFilter.acceptsKinds(DescriptorKindFilter.TYPE_ALIASES_MASK)) {
            for (typeAliasName in helper.typeAliasNames) {
                if (nameFilter(typeAliasName)) {
                    result.addIfNotNull(helper.getTypeAliasByName(typeAliasName))
                }
            }
        }

        return result.compact()
    }

    override fun getContributedClassifier(name: Name, location: LookupLocation): ClassifierDescriptor? =
        when {
            hasClass(name) -> deserializeClass(name)
            name in helper.typeAliasNames -> getTypeAliasByName(name)
            else -> null
        }

    private fun deserializeClass(name: Name): ClassDescriptor? =
        c.components.deserializeClass(createClassId(name))

    protected open fun hasClass(name: Name): Boolean =
        name in classNames

    protected abstract fun createClassId(name: Name): ClassId

    protected abstract fun getNonDeclaredFunctionNames(): Set<Name>
    protected abstract fun getNonDeclaredVariableNames(): Set<Name>
    protected abstract fun getNonDeclaredClassifierNames(): Set<Name>?

    protected abstract fun addEnumEntryDescriptors(result: MutableCollection<DeclarationDescriptor>, nameFilter: (Name) -> Boolean)

    override fun printScopeStructure(p: Printer) {
        p.println(this::class.java.simpleName, " {")
        p.pushIndent()

        p.println("containingDeclaration = " + c.containingDeclaration)

        p.popIndent()
        p.println("}")
    }

    /**
     * This interface were introduces to fix KT-41346.
     *
     * The first implementation, [OptimizedDeserializedMemberScopeHelper], is more space-efficient and performant. It does not
     * preserve the order of declarations in [addFunctionsAndPropertiesTo] though, and have to restore it manually. It is used
     * in most situations when the [DeserializedMemberScope] is created.
     *
     * The second implementation, [NoReorderDeserializedMemberScopeHelper], is less efficient, but it keeps the descriptors
     * in the same order as in serialized ProtoBuf objects in [addFunctionsAndPropertiesTo]. It should be used only when
     * [org.jetbrains.kotlin.serialization.deserialization.DeserializationConfiguration.preserveDeclarationsOrdering] is
     * set to `true`, which is done during decompilation from deserialized descriptors.
     *
     * The decompiled descriptors are used to build PSI, which is then compared with PSI built directly from classfiles and metadata.
     *
     * If the declarations in the first and the second PSI go in different order, PSI-Stub mismatch error is raised.
     *
     * PSI from classfiles and metadata uses the same order of the declarations as in the serialized ProtoBuf objects.
     * This order is dictated by [MemberComparator].
     *
     * [OptimizedDeserializedMemberScopeHelper] uses [MemberComparator.NameAndTypeMemberComparator] to restore the same order
     * of the declarations as it should be in serialized objects. However, this does not always work, for example when
     * the Kotlin classes were obfuscated by ProGuard.
     *
     * ProGuard may rename some declarations in serialized objects, and then the comparator will reorder them based on their new names.
     * This will lead to PSI-Stub mismatch error, since the declarations are now differently ordered.
     *
     * To avoid this, we have [NoReorderDeserializedMemberScopeHelper] implementation. It performs no reordering of the declarations at
     * all. Since it is less space-efficient, it is used only the scope is going to be used during decompilation.

     * [createMemberScopeHelper] is used to create the correct implementation of [DeserializedMemberScopeHelper].
     *
     * Both [OptimizedDeserializedMemberScopeHelper] and [NoReorderDeserializedMemberScopeHelper] are made inner classes to have
     * access to protected `getNonDeclared*` functions.
     */
    private interface DeserializedMemberScopeHelper {
        val functionNames: Set<Name>
        val variableNames: Set<Name>
        val typeAliasNames: Set<Name>

        fun getContributedFunctions(name: Name, location: LookupLocation): Collection<SimpleFunctionDescriptor>
        fun getContributedVariables(name: Name, location: LookupLocation): Collection<PropertyDescriptor>
        fun getTypeAliasByName(name: Name): TypeAliasDescriptor?

        fun addFunctionsAndPropertiesTo(
            result: MutableCollection<DeclarationDescriptor>,
            kindFilter: DescriptorKindFilter,
            nameFilter: (Name) -> Boolean,
            location: LookupLocation
        )
    }

    private fun createMemberScopeHelper(
        functionList: List<ProtoBuf.Function>,
        propertyList: List<ProtoBuf.Property>,
        typeAliasList: List<ProtoBuf.TypeAlias>
    ): DeserializedMemberScopeHelper =
        if (c.components.configuration.preserveDeclarationsOrdering)
            NoReorderDeserializedMemberScopeHelper(functionList, propertyList, typeAliasList)
        else
            OptimizedDeserializedMemberScopeHelper(functionList, propertyList, typeAliasList)

    private inner class OptimizedDeserializedMemberScopeHelper(
        functionList: Collection<ProtoBuf.Function>,
        propertyList: Collection<ProtoBuf.Property>,
        typeAliasList: Collection<ProtoBuf.TypeAlias>
    ) : DeserializedMemberScopeHelper {
        private val functionProtosBytes = functionList.groupByName { it.name }.packToByteArray()

        private val propertyProtosBytes = propertyList.groupByName { it.name }.packToByteArray()

        private val typeAliasBytes =
            if (c.components.configuration.typeAliasesAllowed)
                typeAliasList.groupByName { it.name }.packToByteArray()
            else
                emptyMap()

        private fun Map<Name, Collection<AbstractMessageLite>>.packToByteArray(): Map<Name, ByteArray> =
            mapValues { entry ->
                val byteArrayOutputStream = ByteArrayOutputStream()
                entry.value.map { proto -> proto.writeDelimitedTo(byteArrayOutputStream) }
                byteArrayOutputStream.toByteArray()
            }

        private val functions =
            c.storageManager.createMemoizedFunction<Name, Collection<SimpleFunctionDescriptor>> { computeFunctions(it) }
        private val properties =
            c.storageManager.createMemoizedFunction<Name, Collection<PropertyDescriptor>> { computeProperties(it) }
        private val typeAliasByName =
            c.storageManager.createMemoizedFunctionWithNullableValues<Name, TypeAliasDescriptor> { createTypeAlias(it) }

        override val functionNames by c.storageManager.createLazyValue {
            functionProtosBytes.keys + getNonDeclaredFunctionNames()
        }

        override val variableNames by c.storageManager.createLazyValue {
            propertyProtosBytes.keys + getNonDeclaredVariableNames()
        }

        override val typeAliasNames: Set<Name> get() = typeAliasBytes.keys

        private inline fun <M : MessageLite> Collection<M>.groupByName(
            getNameIndex: (M) -> Int
        ) = groupBy { c.nameResolver.getName(getNameIndex(it)) }

        private fun computeFunctions(name: Name) =
            computeDescriptors(
                name,
                functionProtosBytes,
                ProtoBuf.Function.PARSER,
                { c.memberDeserializer.loadFunction(it).takeIf(::isDeclaredFunctionAvailable) },
                { computeNonDeclaredFunctions(name, it) }
            )

        private inline fun <M : MessageLite, D : DeclarationDescriptor> computeDescriptors(
            name: Name,
            bytesByName: Map<Name, ByteArray>,
            parser: Parser<M>,
            factory: (M) -> D?,
            computeNonDeclared: (MutableCollection<D>) -> Unit
        ): Collection<D> =
            computeDescriptors(
                bytesByName[name]?.let {
                    val inputStream = ByteArrayInputStream(it)
                    generateSequence {
                        parser.parseDelimitedFrom(inputStream, c.components.extensionRegistryLite)
                    }.toList()
                } ?: emptyList(),
                factory,
                computeNonDeclared
            )

        private inline fun <M : MessageLite, D : DeclarationDescriptor> computeDescriptors(
            protos: Collection<M>,
            factory: (M) -> D?,
            computeNonDeclared: (MutableCollection<D>) -> Unit
        ): Collection<D> {
            val descriptors = protos.mapNotNullTo(arrayListOf(), factory)

            computeNonDeclared(descriptors)
            return descriptors.compact()
        }

        private fun computeProperties(name: Name) =
            computeDescriptors(
                name,
                propertyProtosBytes,
                ProtoBuf.Property.PARSER,
                { c.memberDeserializer.loadProperty(it) },
                { computeNonDeclaredProperties(name, it) }
            )

        private fun createTypeAlias(name: Name): TypeAliasDescriptor? {
            val byteArray = typeAliasBytes[name] ?: return null
            val proto =
                ProtoBuf.TypeAlias.parseDelimitedFrom(
                    ByteArrayInputStream(byteArray), c.components.extensionRegistryLite
                ) ?: return null
            return c.memberDeserializer.loadTypeAlias(proto)
        }

        override fun getContributedFunctions(name: Name, location: LookupLocation): Collection<SimpleFunctionDescriptor> {
            if (name !in functionNames) return emptyList()
            return functions(name)
        }

        override fun getTypeAliasByName(name: Name): TypeAliasDescriptor? {
            return typeAliasByName(name)
        }

        override fun getContributedVariables(name: Name, location: LookupLocation): Collection<PropertyDescriptor> {
            if (name !in variableNames) return emptyList()
            return properties(name)
        }

        override fun addFunctionsAndPropertiesTo(
            result: MutableCollection<DeclarationDescriptor>,
            kindFilter: DescriptorKindFilter,
            nameFilter: (Name) -> Boolean,
            location: LookupLocation
        ) {
            if (kindFilter.acceptsKinds(DescriptorKindFilter.VARIABLES_MASK)) {
                addMembers(
                    variableNames,
                    nameFilter,
                    result
                ) { getContributedVariables(it, location) }
            }

            if (kindFilter.acceptsKinds(DescriptorKindFilter.FUNCTIONS_MASK)) {
                addMembers(
                    functionNames,
                    nameFilter,
                    result
                ) { getContributedFunctions(it, location) }
            }
        }

        private inline fun addMembers(
            names: Collection<Name>,
            nameFilter: (Name) -> Boolean,
            result: MutableCollection<DeclarationDescriptor>,
            descriptorsByName: (Name) -> Collection<DeclarationDescriptor>
        ) {
            val subResult = ArrayList<DeclarationDescriptor>()
            for (name in names) {
                if (nameFilter(name)) {
                    subResult.addAll(descriptorsByName(name))
                }
            }

            // We perform the sort just in case
            subResult.sortWith(MemberComparator.NameAndTypeMemberComparator.INSTANCE)
            result.addAll(subResult)
        }
    }

    /**
     * Take a note that [NoReorderDeserializedMemberScopeHelper] still adds non-declared members together with directly declared in class.
     * This is not a problem for ordering, since during decompilation from descriptors those non-declared members are just ignored,
     * and the declared members will be added to decompiled text in the proper (i.e. original) order.
     */
    private inner class NoReorderDeserializedMemberScopeHelper(
        private val functionList: List<ProtoBuf.Function>,
        private val propertyList: List<ProtoBuf.Property>,
        typeAliasList: List<ProtoBuf.TypeAlias>
    ) : DeserializedMemberScopeHelper {

        private val typeAliasList = if (c.components.configuration.typeAliasesAllowed) typeAliasList else emptyList()

        private val declaredFunctions: List<SimpleFunctionDescriptor>
                by c.storageManager.createLazyValue { computeFunctions() }

        private val declaredProperties: List<PropertyDescriptor>
                by c.storageManager.createLazyValue { computeProperties() }

        private val allTypeAliases: List<TypeAliasDescriptor>
                by c.storageManager.createLazyValue { computeTypeAliases() }

        private val nonDeclaredFunctions: List<SimpleFunctionDescriptor>
                by c.storageManager.createLazyValue { computeNonDeclaredFunctions() }

        private val nonDeclaredProperties: List<PropertyDescriptor>
                by c.storageManager.createLazyValue { computeNonDeclaredProperties() }

        private val allFunctions: List<SimpleFunctionDescriptor>
                by c.storageManager.createLazyValue { declaredFunctions + nonDeclaredFunctions }

        private val allProperties: List<PropertyDescriptor>
                by c.storageManager.createLazyValue { declaredProperties + nonDeclaredProperties }

        override val functionNames by c.storageManager.createLazyValue {
            functionList.mapToNames { it.name } + getNonDeclaredFunctionNames()
        }

        override val variableNames by c.storageManager.createLazyValue {
            propertyList.mapToNames { it.name } + getNonDeclaredVariableNames()
        }

        override val typeAliasNames: Set<Name>
            get() = typeAliasList.mapToNames { it.name }

        private fun computeFunctions(): List<SimpleFunctionDescriptor> =
            functionList.mapWithDeserializer { loadFunction(it).takeIf(::isDeclaredFunctionAvailable) }

        private fun computeProperties(): List<PropertyDescriptor> =
            propertyList.mapWithDeserializer { loadProperty(it) }

        private fun computeTypeAliases(): List<TypeAliasDescriptor> =
            typeAliasList.mapWithDeserializer { loadTypeAlias(it) }

        @OptIn(ExperimentalStdlibApi::class)
        private fun computeNonDeclaredFunctions(): List<SimpleFunctionDescriptor> =
            buildList {
                for (name in getNonDeclaredFunctionNames()) {
                    computeNonDeclaredFunctions(name, this)
                }
            }

        @OptIn(ExperimentalStdlibApi::class)
        private fun computeNonDeclaredProperties(): List<PropertyDescriptor> =
            buildList {
                for (name in getNonDeclaredVariableNames()) {
                    computeNonDeclaredProperties(name, this)
                }
            }

        override fun getContributedFunctions(name: Name, location: LookupLocation): Collection<SimpleFunctionDescriptor> {
            if (name !in functionNames) return emptyList()
            return allFunctions.filter { it.name == name }
        }

        override fun getTypeAliasByName(name: Name): TypeAliasDescriptor? {
            return allTypeAliases.find { it.name == name }
        }

        override fun getContributedVariables(name: Name, location: LookupLocation): Collection<PropertyDescriptor> {
            if (name !in variableNames) return emptyList()
            return allProperties.filter { it.name == name }
        }

        override fun addFunctionsAndPropertiesTo(
            result: MutableCollection<DeclarationDescriptor>,
            kindFilter: DescriptorKindFilter,
            nameFilter: (Name) -> Boolean,
            location: LookupLocation
        ) {
            if (kindFilter.acceptsKinds(DescriptorKindFilter.VARIABLES_MASK)) {
                allProperties.filterTo(result) { nameFilter(it.name) }
            }

            if (kindFilter.acceptsKinds(DescriptorKindFilter.FUNCTIONS_MASK)) {
                allFunctions.filterTo(result) { nameFilter(it.name) }
            }
        }

        private inline fun <T : MessageLite> List<T>.mapToNames(getNameIndex: (T) -> Int): Set<Name> {
            // `mutableSetOf` returns `LinkedHashSet`, it is important to preserve the order of the declarations.
            return mapTo(mutableSetOf()) { c.nameResolver.getName(getNameIndex(it)) }
        }

        private inline fun <T : MessageLite, K : MemberDescriptor> List<T>.mapWithDeserializer(
            deserialize: MemberDeserializer.(T) -> K?
        ): List<K> {
            return mapNotNull { c.memberDeserializer.deserialize(it) }
        }
    }
}
