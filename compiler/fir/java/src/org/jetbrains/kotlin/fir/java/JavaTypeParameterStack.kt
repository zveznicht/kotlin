/*
 * Copyright 2010-2019 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.fir.java

import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol
import org.jetbrains.kotlin.load.java.structure.JavaTypeParameter

internal class JavaTypeParameterStack private constructor(
    private val typeParameterMap: MutableMap<JavaTypeParameter, FirTypeParameterSymbol>
) {

    fun addParameter(javaTypeParameter: JavaTypeParameter, symbol: FirTypeParameterSymbol) {
        typeParameterMap[javaTypeParameter] = symbol
    }

    operator fun get(javaTypeParameter: JavaTypeParameter): FirTypeParameterSymbol {
        return safeGet(javaTypeParameter)
            ?: throw IllegalArgumentException("Cannot find Java type parameter $javaTypeParameter in stack")
    }

    fun safeGet(javaTypeParameter: JavaTypeParameter) = typeParameterMap[javaTypeParameter]

    companion object {
        val EMPTY: JavaTypeParameterStack = JavaTypeParameterStack(mutableMapOf())

        fun create(parentStack: JavaTypeParameterStack?): JavaTypeParameterStack {
            val map = hashMapOf<JavaTypeParameter, FirTypeParameterSymbol>()
            parentStack?.let { map.putAll(it.typeParameterMap) }
            return JavaTypeParameterStack(map)
        }
    }
}
