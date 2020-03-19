/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlinx.sharing.compiler.pluginapi

import org.jetbrains.kotlin.backend.common.extensions.IrGenerationExtension
import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.descriptors.ClassDescriptor
import org.jetbrains.kotlin.descriptors.DeclarationDescriptor
import org.jetbrains.kotlin.descriptors.PropertyDescriptor
import org.jetbrains.kotlin.descriptors.SimpleFunctionDescriptor
import org.jetbrains.kotlin.ir.IrElement
import org.jetbrains.kotlin.ir.declarations.IrClass
import org.jetbrains.kotlin.ir.declarations.IrModuleFragment
import org.jetbrains.kotlin.ir.visitors.IrElementVisitorVoid
import org.jetbrains.kotlin.ir.visitors.acceptChildrenVoid
import org.jetbrains.kotlin.ir.visitors.acceptVoid
import org.jetbrains.kotlin.name.Name
import org.jetbrains.kotlin.resolve.BindingContext
import org.jetbrains.kotlin.resolve.extensions.SyntheticResolveExtension
import java.util.*

abstract class CompilerPlugin : IrGenerationExtension,
    SyntheticResolveExtension {

    // todo: unnecessary after KT-37255?
    private val tracked: MutableMap<ClassDescriptor, MutableSet<DeclarationDescriptor>> = hashMapOf()

    final override fun generate(moduleFragment: IrModuleFragment, pluginContext: IrPluginContext) {
        val lowering = createIrTransformer(pluginContext)
        for (file in moduleFragment.files) {
            file.acceptVoid(object : IrElementVisitorVoid {
                override fun visitElement(element: IrElement) {
                    element.acceptChildrenVoid(this)
                }

                override fun visitClass(declaration: IrClass) {
                    if (!isApplied(declaration.descriptor)) return
                    lowering.createMissingParts(declaration, tracked[declaration.descriptor] ?: emptySet())
                    lowering.lower(declaration)
                    declaration.acceptChildrenVoid(this)
                }
            })
        }
    }

    // todo: other methods from synthetic resolve ex
    override fun generateSyntheticProperties(
        thisDescriptor: ClassDescriptor,
        name: Name,
        bindingContext: BindingContext,
        fromSupertypes: ArrayList<PropertyDescriptor>,
        result: MutableSet<PropertyDescriptor>
    ) {
        if (!isApplied(thisDescriptor)) return
        if (name !in creator.propertiesNames) return
        if (result.isNotEmpty()) error("Can't add plugin-defined function with the same name $name as user-defined one")
        val added = listOfNotNull(creator.createPropertyForFrontend(thisDescriptor, name, bindingContext))
        tracked.getOrPut(thisDescriptor, ::hashSetOf).addAll(added)
        result.addAll(added)
    }

    override fun generateSyntheticMethods(
        thisDescriptor: ClassDescriptor,
        name: Name,
        bindingContext: BindingContext,
        fromSupertypes: List<SimpleFunctionDescriptor>,
        result: MutableCollection<SimpleFunctionDescriptor>
    ) {
        if (!isApplied(thisDescriptor)) return
        if (name !in creator.functionNames) return
        // todo: support overloads
//        if (result.isNotEmpty()) error("Can't add plugin-defined function with the same name $name as user-defined one")
        val added = listOfNotNull(creator.createFunctionForFrontend(thisDescriptor, name, bindingContext))
        tracked.getOrPut(thisDescriptor, ::hashSetOf).addAll(added)
        result.addAll(added)
    }

    override fun getSyntheticFunctionNames(thisDescriptor: ClassDescriptor): List<Name> {
        return creator.functionNames.toList()
    }

    override fun getSyntheticPropertiesNames(thisDescriptor: ClassDescriptor): List<Name> {
        return creator.propertiesNames.toList()
    }

    abstract fun isApplied(toClass: ClassDescriptor): Boolean

    abstract fun createIrTransformer(context: IrPluginContext): IrTransformer

    abstract val creator: PluginDeclarationsCreator
}

interface PluginDeclarationsCreator {
    // todo: add others
    // todo: should ClassDescriptor be here?
    val propertiesNames: Set<Name> get() = setOf()

    val functionNames: Set<Name> get() = setOf()

    fun createPropertyForFrontend(owner: ClassDescriptor, name: Name, context: BindingContext): PropertyDescriptor? = null
    fun createFunctionForFrontend(owner: ClassDescriptor, name: Name, context: BindingContext): SimpleFunctionDescriptor? = null
}