// KJS_WITH_FULL_RUNTIME
// KT-40126
@file:Suppress("INTERFACE_WITH_SUPERCLASS", "OVERRIDING_FINAL_MEMBER", "RETURN_TYPE_MISMATCH_ON_OVERRIDE", "CONFLICTING_OVERLOADS", "EXTERNAL_DELEGATION")

@Suppress("NESTED_CLASS_IN_EXTERNAL_INTERFACE")
external interface MySymbol {
    companion object : MySymbolConstructor by definedExternally
}
external interface MySymbolConstructor {
    @nativeInvoke
    operator fun invoke(description: String = definedExternally): Any
}

fun box() = "OK"