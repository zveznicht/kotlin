/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea.stubindex

import com.intellij.psi.stubs.StringStubIndexExtension
import com.intellij.psi.stubs.StubIndexKey
import org.jetbrains.kotlin.psi.KtNamedDeclaration

internal class KotlinObjectMembersShortNameIndex private constructor() : StringStubIndexExtension<KtNamedDeclaration>() {
    companion object {
        private val KEY = KotlinIndexUtil.createIndexKey(KotlinObjectMembersShortNameIndex::class.java)

        @JvmField
        val INSTANCE: KotlinObjectMembersShortNameIndex = KotlinObjectMembersShortNameIndex()

        @JvmStatic
        fun getInstance(): KotlinObjectMembersShortNameIndex = INSTANCE
    }

    override fun getKey(): StubIndexKey<String, KtNamedDeclaration> = KEY
}