val test get() = <fold text='{...}' expand='true'>listOf(1, 2, 3)
    .filter { it > 2 }
    .map { it > 2 }</fold>