inline fun inlineFun2(crossinline fn: () -> Int): Int {
    return inlineFun1(fn)
}