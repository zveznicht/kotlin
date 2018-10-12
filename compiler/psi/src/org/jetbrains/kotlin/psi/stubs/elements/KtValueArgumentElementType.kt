/*
 * Copyright 2010-2018 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.psi.stubs.elements

import com.intellij.lang.ASTNode
import org.jetbrains.annotations.NonNls
import org.jetbrains.kotlin.psi.KtAnnotationEntry
import org.jetbrains.kotlin.psi.KtValueArgument
import org.jetbrains.kotlin.psi.KtValueArgumentList

class KtValueArgumentElementType(@NonNls debugName: String) :
    KtPlaceHolderStubElementType<KtValueArgument>(debugName, KtValueArgument::class.java) {

    override fun shouldCreateStub(node: ASTNode): Boolean {
        val psi = node.psi
        return psi.parent is KtValueArgumentList && psi.parent.parent is KtAnnotationEntry
    }
}
