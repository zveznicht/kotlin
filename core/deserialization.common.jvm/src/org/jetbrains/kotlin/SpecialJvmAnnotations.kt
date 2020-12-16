/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin

import org.jetbrains.kotlin.load.java.JvmAnnotationNames
import org.jetbrains.kotlin.name.ClassId
import org.jetbrains.kotlin.name.FqName

object SpecialJvmAnnotations {
    val METADATA_CLASS_ID = ClassId.topLevel(JvmAnnotationNames.METADATA_FQ_NAME)
    val JETBRAINS_NOT_NULL_CLASS_ID = ClassId.topLevel(JvmAnnotationNames.JETBRAINS_NOT_NULL_ANNOTATION)
    val JETBRAINS_NULLABLE_CLASS_ID = ClassId.topLevel(JvmAnnotationNames.JETBRAINS_NULLABLE_ANNOTATION)

    val SPECIAL_ANNOTATIONS: Set<ClassId> = mutableSetOf<ClassId>().apply {
        add(METADATA_CLASS_ID)
        add(JETBRAINS_NOT_NULL_CLASS_ID)
        add(JETBRAINS_NULLABLE_CLASS_ID)
        listOf(
            JvmAnnotationNames.TARGET_ANNOTATION,
            JvmAnnotationNames.RETENTION_ANNOTATION,
            JvmAnnotationNames.DOCUMENTED_ANNOTATION
        ).mapTo(this, ClassId::topLevel)
    }
}
