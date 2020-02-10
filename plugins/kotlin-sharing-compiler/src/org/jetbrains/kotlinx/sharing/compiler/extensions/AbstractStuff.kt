/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlinx.sharing.compiler.extensions

import org.jetbrains.kotlin.backend.common.ClassLoweringPass
import org.jetbrains.kotlin.backend.common.extensions.IrGenerationExtension
import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.descriptors.ClassDescriptor
import org.jetbrains.kotlin.descriptors.DeclarationDescriptor
import org.jetbrains.kotlin.descriptors.PropertyDescriptor
import org.jetbrains.kotlin.ir.IrElement
import org.jetbrains.kotlin.ir.declarations.IrClass
import org.jetbrains.kotlin.ir.declarations.IrModuleFragment
import org.jetbrains.kotlin.ir.visitors.IrElementVisitorVoid
import org.jetbrains.kotlin.ir.visitors.acceptChildrenVoid
import org.jetbrains.kotlin.ir.visitors.acceptVoid
import org.jetbrains.kotlin.name.Name
import org.jetbrains.kotlin.resolve.BindingContext
import org.jetbrains.kotlin.resolve.extensions.SyntheticResolveExtension
import org.jetbrains.kotlinx.sharing.compiler.backend.ir.IrBuilderExtension
import java.util.*

abstract class CompilerPlugin : IrGenerationExtension, SyntheticResolveExtension {

    // todo: convert to WritableSlice from binding context?
    private val tracked: MutableMap<ClassDescriptor, MutableSet<DeclarationDescriptor>> = hashMapOf()

    final override fun generate(moduleFragment: IrModuleFragment, pluginContext: IrPluginContext) {
        val lowering = getIrTransformer(pluginContext)
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
        val added = getCreator().createPropertyForFrontend(thisDescriptor, bindingContext)
        tracked.getOrPut(thisDescriptor, ::hashSetOf).addAll(added)
        result.addAll(added)
    }

    abstract fun isApplied(toClass: ClassDescriptor): Boolean

    abstract fun getIrTransformer(context: IrPluginContext): IrTransformer

    abstract fun getCreator(): PluginDeclarationsCreator
}

abstract class IrTransformer : IrBuilderExtension, ClassLoweringPass {
    fun createMissingParts(irClass: IrClass, pluginDescriptors: Collection<DeclarationDescriptor>) {
        val irDecls = pluginDescriptors.map { desc ->
            if (desc !is PropertyDescriptor) TODO()
            createPropertyForBackend(irClass, desc)
        }
        irClass.declarations.addAll(irDecls)
    }
}

interface PluginDeclarationsCreator {
    // todo: add others
    fun createPropertyForFrontend(owner: ClassDescriptor, context: BindingContext): List<PropertyDescriptor>
}