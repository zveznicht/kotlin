/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.descriptors.commonizer.utils

import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.types.AbbreviatedType
import org.jetbrains.kotlin.types.KotlinType

private const val OBJECTIVE_C_SIGNED_INTEGER = "NSInteger"
private const val OBJECTIVE_C_UNSIGNED_INTEGER = "NSUInteger"

internal fun FqName.isObjCIntegerType(): Boolean {
    if (!isUnderDarwinPackage)
        return false

    val name = shortName().asString()
    return name == OBJECTIVE_C_SIGNED_INTEGER || name == OBJECTIVE_C_UNSIGNED_INTEGER
}

internal tailrec fun KotlinType.narrowType(): KotlinType =
    if (this is AbbreviatedType) {
        if (abbreviation.fqName.isObjCIntegerType()) abbreviation else expandedType.narrowType()
    } else this
