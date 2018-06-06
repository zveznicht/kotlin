// !WITH_NEW_INFERENCE
// !DIAGNOSTICS: -UNUSED_PARAMETER,-CONFLICTING_JVM_DECLARATIONS, -UNUSED_VARIABLE
// NI_EXPECTED_FILE

suspend fun foo(i: Int) = "$i"
suspend fun foo(s: String) = s

suspend fun bar(s: String) = s

suspend fun qux(i: Int, j: Int, k: Int): Int = i + j + k
suspend fun qux(a: String, b: String, c: String, d: String) {}

suspend fun fn1(x: Int, f1: suspend (Int) -> String, f2: suspend (String) -> String) = f2(f1(x))

suspend fun fn2(f1: suspend (Int) -> String,    f2: suspend (String) -> String  ) = f2(f1(0))
suspend fun fn2(f1: suspend (Int) -> Int,       f2: suspend (Int) -> String     ) = f2(f1(0))
suspend fun fn2(f1: suspend (String) -> String, f2: suspend (String) -> String  ) = f2(f1(""))

suspend fun fn3(i: Int, f: suspend (Int, Int, Int) -> Int): Int = f(i, i, i)

suspend fun test() {
    val x1 = fn1(1, ::foo, ::foo)
    val x2 = fn1(1, ::foo, ::bar)

    val x3 = <!NI;OVERLOAD_RESOLUTION_AMBIGUITY!>fn2<!>(::<!NI;DEBUG_INFO_MISSING_UNRESOLVED!>bar<!>, ::<!NI;DEBUG_INFO_MISSING_UNRESOLVED!>foo<!>)
    val x4 = <!OVERLOAD_RESOLUTION_AMBIGUITY!>fn2<!>(::<!NI;DEBUG_INFO_MISSING_UNRESOLVED, OI;OVERLOAD_RESOLUTION_AMBIGUITY!>foo<!>, ::<!NI;DEBUG_INFO_MISSING_UNRESOLVED!>bar<!>)
    val x5 = <!OVERLOAD_RESOLUTION_AMBIGUITY!>fn2<!>(::<!NI;DEBUG_INFO_MISSING_UNRESOLVED, OI;OVERLOAD_RESOLUTION_AMBIGUITY!>foo<!>, ::<!NI;DEBUG_INFO_MISSING_UNRESOLVED, OI;OVERLOAD_RESOLUTION_AMBIGUITY!>foo<!>)

    val x6 = fn3(1, ::qux)
}
