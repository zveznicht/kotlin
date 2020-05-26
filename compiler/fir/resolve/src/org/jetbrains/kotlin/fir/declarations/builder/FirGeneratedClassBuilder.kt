/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.fir.declarations.builder

import org.jetbrains.kotlin.fir.FirImplementationDetail
import org.jetbrains.kotlin.fir.FirSourceElement
import org.jetbrains.kotlin.fir.builder.FirBuilderDsl
import org.jetbrains.kotlin.fir.declarations.*
import org.jetbrains.kotlin.fir.declarations.impl.FirGeneratedClass
import org.jetbrains.kotlin.fir.scopes.FirScopeProvider
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

@FirBuilderDsl
class FirGeneratedClassBuilder : FirRegularClassBuilder() {
    lateinit var pluginKey: FirPluginKey

    @Deprecated("Modification of 'source' has no impact for FirGeneratedClassBuilder", level = DeprecationLevel.HIDDEN)
    override var source: FirSourceElement?
        get() = throw IllegalStateException()
        set(_) {
            throw IllegalStateException()
        }

    @Deprecated("Modification of 'origin' has no impact for FirGeneratedClassBuilder", level = DeprecationLevel.HIDDEN)
    override var origin: FirDeclarationOrigin
        get() = throw IllegalStateException()
        set(_) {
            throw IllegalStateException()
        }

    @Deprecated("Modification of 'declarations' has no impact for FirGeneratedClassBuilder", level = DeprecationLevel.HIDDEN)
    override val declarations: MutableList<FirDeclaration>
        get() = throw IllegalStateException()

    @Deprecated("Modification of 'scopeProvider' has no impact for FirGeneratedClassBuilder", level = DeprecationLevel.HIDDEN)
    override var scopeProvider: FirScopeProvider
        get() = throw IllegalStateException()
        set(_) {
            throw IllegalStateException()
        }

    @Deprecated("Modification of 'resolvePhase' has no impact for FirGeneratedClassBuilder", level = DeprecationLevel.HIDDEN)
    override var resolvePhase: FirResolvePhase
        get() = throw IllegalStateException()
        set(_) {
            throw IllegalStateException()
        }

    @OptIn(FirImplementationDetail::class)
    override fun build(): FirGeneratedClass {
        return FirGeneratedClass(
            session,
            pluginKey,
            annotations,
            typeParameters,
            status,
            classKind,
            name,
            symbol,
            companionObject,
            superTypeRefs,
        ).also {
            it.validate()
        }
    }
}

@OptIn(ExperimentalContracts::class)
inline fun buildGeneratedClass(init: FirGeneratedClassBuilder.() -> Unit): FirGeneratedClass {
    contract {
        callsInPlace(init, InvocationKind.EXACTLY_ONCE)
    }
    return FirGeneratedClassBuilder().apply(init).build()
}
