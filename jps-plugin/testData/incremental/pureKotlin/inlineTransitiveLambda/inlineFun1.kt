inline fun inlineFun1(crossinline fn:() -> Int): Int {
    return { fn() }()
}