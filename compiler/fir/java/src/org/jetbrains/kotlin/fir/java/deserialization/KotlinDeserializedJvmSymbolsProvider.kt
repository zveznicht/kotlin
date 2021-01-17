/*
 * Copyright 2010-2019 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.fir.java.deserialization

import com.intellij.openapi.progress.ProcessCanceledException
import com.intellij.openapi.project.Project
import org.jetbrains.kotlin.descriptors.SourceElement
import org.jetbrains.kotlin.fir.FirSession
import org.jetbrains.kotlin.fir.caches.firCachesFactory
import org.jetbrains.kotlin.fir.declarations.*
import org.jetbrains.kotlin.fir.deserialization.FirConstDeserializer
import org.jetbrains.kotlin.fir.deserialization.FirDeserializationContext
import org.jetbrains.kotlin.fir.deserialization.deserializeClassToSymbol
import org.jetbrains.kotlin.fir.expressions.*
import org.jetbrains.kotlin.fir.expressions.builder.*
import org.jetbrains.kotlin.fir.java.topLevelName
import org.jetbrains.kotlin.fir.resolve.providers.*
import org.jetbrains.kotlin.fir.scopes.KotlinScopeProvider
import org.jetbrains.kotlin.fir.symbols.impl.*
import org.jetbrains.kotlin.load.java.JavaClassFinder
import org.jetbrains.kotlin.load.kotlin.*
import org.jetbrains.kotlin.load.kotlin.header.KotlinClassHeader
import org.jetbrains.kotlin.metadata.ProtoBuf
import org.jetbrains.kotlin.metadata.deserialization.Flags
import org.jetbrains.kotlin.metadata.jvm.deserialization.JvmMetadataVersion
import org.jetbrains.kotlin.metadata.jvm.deserialization.JvmNameResolver
import org.jetbrains.kotlin.metadata.jvm.deserialization.JvmProtoBufUtil
import org.jetbrains.kotlin.name.ClassId
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.name.Name
import org.jetbrains.kotlin.name.isOneSegmentFQN
import org.jetbrains.kotlin.resolve.jvm.JvmClassName
import org.jetbrains.kotlin.serialization.deserialization.IncompatibleVersionErrorData
import org.jetbrains.kotlin.serialization.deserialization.getName
import org.jetbrains.kotlin.utils.addToStdlib.firstNotNullResult

class KotlinDeserializedJvmSymbolsProvider(
    session: FirSession,
    val project: Project,
    private val packagePartProvider: PackagePartProvider,
    private val kotlinClassFinder: KotlinClassFinder,
    javaClassFinder: JavaClassFinder,
    kotlinScopeProvider: KotlinScopeProvider,
) : FirSymbolProvider(session) {
    private val knownNameInPackageCache = KnownNameInPackageCache(session, javaClassFinder)

    private val classCache = KotlinDeserializedJvmSymbolsProviderClassCache(
        session,
        kotlinClassFinder,
        kotlinScopeProvider,
        knownNameInPackageCache
    )

    private val typeAliasCache = session.firCachesFactory.createCache(::findAndDeserializeTypeAlias)
    private val packagePartsCache = session.firCachesFactory.createCache(::tryComputePackagePartInfos)

    private class PackagePartsCacheData(
        val proto: ProtoBuf.Package,
        val context: FirDeserializationContext,
    ) {
        val topLevelFunctionNameIndex by lazy {
            proto.functionList.withIndex()
                .groupBy({ context.nameResolver.getName(it.value.name) }) { (index) -> index }
        }
        val topLevelPropertyNameIndex by lazy {
            proto.propertyList.withIndex()
                .groupBy({ context.nameResolver.getName(it.value.name) }) { (index) -> index }
        }
        val typeAliasNameIndex by lazy {
            proto.typeAliasList.withIndex()
                .groupBy({ context.nameResolver.getName(it.value.name) }) { (index) -> index }
        }
    }


    private fun computePackagePartsInfos(packageFqName: FqName): List<PackagePartsCacheData> {

        return packagePartProvider.findPackageParts(packageFqName.asString()).mapNotNull { partName ->
            val classId = ClassId.topLevel(JvmClassName.byInternalName(partName).fqNameForTopLevelClassMaybeWithDollars)
            if (knownNameInPackageCache.hasNoTopLevelClassOf(classId)) return@mapNotNull null
            val (kotlinJvmBinaryClass, byteContent) =
                kotlinClassFinder.findKotlinClassOrContent(classId) as? KotlinClassFinder.Result.KotlinClass ?: return@mapNotNull null

            val facadeName = kotlinJvmBinaryClass.classHeader.multifileClassName?.takeIf { it.isNotEmpty() }
            val facadeFqName = facadeName?.let { JvmClassName.byInternalName(it).fqNameForTopLevelClassMaybeWithDollars }
            val facadeBinaryClass = facadeFqName?.let { kotlinClassFinder.findKotlinClass(ClassId.topLevel(it)) }

            val header = kotlinJvmBinaryClass.classHeader
            val data = header.data ?: header.incompatibleData ?: return@mapNotNull null
            val strings = header.strings ?: return@mapNotNull null
            val (nameResolver, packageProto) = JvmProtoBufUtil.readPackageDataFrom(data, strings)

            val source = JvmPackagePartSource(
                kotlinJvmBinaryClass, packageProto, nameResolver,
                kotlinJvmBinaryClass.incompatibility, kotlinJvmBinaryClass.isPreReleaseInvisible,
            )

            PackagePartsCacheData(
                packageProto,
                FirDeserializationContext.createForPackage(
                    packageFqName, packageProto, nameResolver, session,
                    JvmBinaryAnnotationDeserializer(session, kotlinJvmBinaryClass, byteContent),
                    FirConstDeserializer(session, facadeBinaryClass ?: kotlinJvmBinaryClass),
                    source
                ),
            )
        }
    }

    private val KotlinJvmBinaryClass.incompatibility: IncompatibleVersionErrorData<JvmMetadataVersion>?
        get() {
            // TODO: skipMetadataVersionCheck
            if (classHeader.metadataVersion.isCompatible()) return null
            return IncompatibleVersionErrorData(classHeader.metadataVersion, JvmMetadataVersion.INSTANCE, location, classId)
        }

    private val KotlinJvmBinaryClass.isPreReleaseInvisible: Boolean
        get() = classHeader.isPreRelease

    override fun getClassLikeSymbolByFqName(classId: ClassId): FirClassLikeSymbol<*>? {
        return classCache.getFirClass(classId) ?: getTypeAlias(classId)
    }

    private fun getTypeAlias(
        classId: ClassId,
    ): FirTypeAliasSymbol? {
        if (!classId.relativeClassName.isOneSegmentFQN()) return null
        return typeAliasCache.getValue(classId)
    }

    private fun findAndDeserializeTypeAlias(classId: ClassId) = getPackageParts(classId.packageFqName).firstNotNullResult { part ->
        val ids = part.typeAliasNameIndex[classId.shortClassName]
        if (ids == null || ids.isEmpty()) return@firstNotNullResult null
        val aliasProto = ids.map { part.proto.getTypeAlias(it) }.single()
        part.context.memberDeserializer.loadTypeAlias(aliasProto).symbol
    }

    private fun loadFunctionsByName(part: PackagePartsCacheData, name: Name): List<FirNamedFunctionSymbol> {
        val functionIds = part.topLevelFunctionNameIndex[name] ?: return emptyList()
        return functionIds.map {
            part.context.memberDeserializer.loadFunction(part.proto.getFunction(it)).symbol
        }
    }

    private fun loadPropertiesByName(part: PackagePartsCacheData, name: Name): List<FirPropertySymbol> {
        val propertyIds = part.topLevelPropertyNameIndex[name] ?: return emptyList()
        return propertyIds.map {
            part.context.memberDeserializer.loadProperty(part.proto.getProperty(it)).symbol
        }
    }

    @FirSymbolProviderInternals
    override fun getTopLevelCallableSymbolsTo(destination: MutableList<FirCallableSymbol<*>>, packageFqName: FqName, name: Name) {
        getPackageParts(packageFqName).flatMapTo(destination) { part ->
            loadFunctionsByName(part, name) + loadPropertiesByName(part, name)
        }
    }

    @FirSymbolProviderInternals
    override fun getTopLevelFunctionSymbolsTo(destination: MutableList<FirNamedFunctionSymbol>, packageFqName: FqName, name: Name) {
        getPackageParts(packageFqName).flatMapTo(destination) { part ->
            loadFunctionsByName(part, name)
        }
    }

    @FirSymbolProviderInternals
    override fun getTopLevelPropertySymbolsTo(destination: MutableList<FirPropertySymbol>, packageFqName: FqName, name: Name) {
        getPackageParts(packageFqName).flatMapTo(destination) { part ->
            loadPropertiesByName(part, name)
        }
    }

    private fun getPackageParts(packageFqName: FqName): Collection<PackagePartsCacheData> {
        return packagePartsCache.getValue(packageFqName)
    }

    private fun tryComputePackagePartInfos(packageFqName: FqName): List<PackagePartsCacheData> = try {
        computePackagePartsInfos(packageFqName)
    } catch (e: ProcessCanceledException) {
        emptyList()
    }

    override fun getPackage(fqName: FqName): FqName? = null
}

private class KnownNameInPackageCache(session: FirSession, private val javaClassFinder: JavaClassFinder) {
    private val knownClassNamesInPackage = session.firCachesFactory.createMapLikeCache<FqName, Set<String>?>()

    /**
     * This function returns true if we are sure that no top-level class with this id is available
     * If it returns false, it means we can say nothing about this id
     */
    fun hasNoTopLevelClassOf(classId: ClassId): Boolean {
        val knownNames = knownClassNamesInPackage.getOrCreateValue(classId.packageFqName) {
            javaClassFinder.knownClassNamesInPackage(classId.packageFqName)
        } ?: return false
        return classId.relativeClassName.topLevelName() !in knownNames
    }
}

private class KotlinDeserializedJvmSymbolsProviderClassCache(
    private val session: FirSession,
    private val kotlinClassFinder: KotlinClassFinder,
    private val kotlinScopeProvider: KotlinScopeProvider,
    private val knownNameInPackageCache: KnownNameInPackageCache
) {
    private val cache =
        session.firCachesFactory.createCacheWithPostCompute<ClassId, FirRegularClassSymbol?, FirDeserializationContext?, KotlinClassFinder.Result.KotlinClass?>(
            createValue = { classId, nestedContext -> findAndDeserializeClass(classId, nestedContext) },
            postCompute = { _, symbol, data ->
                if (symbol != null && data != null) {
                    symbol.fir.convertAnnotations(data.kotlinJvmBinaryClass, data.byteContent)
                }
            }
        )
    private val annotationsLoader = AnnotationsLoader(session)

    fun getFirClass(classId: ClassId): FirRegularClassSymbol? =
        getCachedClassOrDeserialize(classId, nestedContext = null)

    private fun getCachedClassOrDeserialize(classId: ClassId, nestedContext: FirDeserializationContext?): FirRegularClassSymbol? {
        if (knownNameInPackageCache.hasNoTopLevelClassOf(classId)) return null
        return cache.getValue(classId, nestedContext)
    }

    private fun findAndDeserializeClass(
        classId: ClassId,
        parentContext: FirDeserializationContext? = null
    ): Pair<FirRegularClassSymbol?, KotlinClassFinder.Result.KotlinClass?> {
        val classFindingResult = try {
            kotlinClassFinder.findKotlinClassOrContent(classId)
        } catch (e: ProcessCanceledException) {
            return null to null
        }

        val foundKotlinClass = when (classFindingResult) {
            is KotlinClassFinder.Result.KotlinClass -> classFindingResult
            is KotlinClassFinder.Result.ClassFileContent -> {
                // Java class will be handled by java symbol provider later
                return null to null
            }
            null -> {
                return findAndDeserializeClassViaParent(classId) to null
            }
        }

        val classSymbol = deserializeClass(foundKotlinClass.kotlinJvmBinaryClass, parentContext, classId, foundKotlinClass.byteContent)
        return classSymbol to foundKotlinClass
    }


    private fun deserializeClass(
        kotlinJvmBinaryClass: KotlinJvmBinaryClass,
        parentContext: FirDeserializationContext?,
        classId: ClassId,
        byteContent: ByteArray?
    ): FirRegularClassSymbol? {
        if (!kotlinJvmBinaryClass.isClass()) return null
        val (nameResolver, classProto) = kotlinJvmBinaryClass.readClassDataFrom() ?: return null

        if (parentContext == null && classProto.isCompanionObject()) {
            return findAndDeserializeClassViaParent(classId)
        }

        val symbol = FirRegularClassSymbol(classId)
        deserializeClassToSymbol(
            classId, classProto, symbol, nameResolver, session,
            JvmBinaryAnnotationDeserializer(session, kotlinJvmBinaryClass, byteContent),
            kotlinScopeProvider,
            parentContext, KotlinJvmBinarySourceElement(kotlinJvmBinaryClass),
            deserializeNestedClass = ::getCachedClassOrDeserialize
        )
        return symbol
    }

    private fun FirRegularClass.convertAnnotations(
        kotlinJvmBinaryClass: KotlinJvmBinaryClass,
        byteContent: ByteArray?
    ) {
        val annotations = loadAnnotations(kotlinJvmBinaryClass, byteContent)
        (this.annotations as MutableList<FirAnnotationCall>) += annotations
    }

    @OptIn(ExperimentalStdlibApi::class)
    private fun loadAnnotations(
        kotlinJvmBinaryClass: KotlinJvmBinaryClass,
        byteContent: ByteArray?
    ): List<FirAnnotationCall> = buildList {
        kotlinJvmBinaryClass.loadClassAnnotations(
            visitor = object : KotlinJvmBinaryClass.AnnotationVisitor {
                override fun visitAnnotation(classId: ClassId, source: SourceElement): KotlinJvmBinaryClass.AnnotationArgumentVisitor? {
                    return annotationsLoader.loadAnnotationIfNotSpecial(classId, this@buildList)
                }

                override fun visitEnd() {}
            },
            cachedContents = byteContent,
        )
    }

    private fun ProtoBuf.Class.isCompanionObject() =
        Flags.CLASS_KIND.get(flags) == ProtoBuf.Class.Kind.COMPANION_OBJECT

    private fun KotlinJvmBinaryClass.isClass() =
        classHeader.kind == KotlinClassHeader.Kind.CLASS

    private fun KotlinJvmBinaryClass.readClassDataFrom(): Pair<JvmNameResolver, ProtoBuf.Class>? {
        val data = classHeader.data ?: return null
        val strings = classHeader.strings ?: return null
        return JvmProtoBufUtil.readClassDataFrom(data, strings)
    }

    private fun findAndDeserializeClassViaParent(classId: ClassId): FirRegularClassSymbol? {
        val outerClassId = classId.outerClassId ?: return null
        cache.getValue(outerClassId, context = null)
        return cache.getValueIfComputed(classId)
    }
}