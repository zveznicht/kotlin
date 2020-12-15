typealias R = <!OTHER_ERROR!>R<!>

typealias L = List<L>

typealias A = <!OTHER_ERROR!>B<!>
typealias B = <!OTHER_ERROR!>A<!>

typealias F1 = (Int) -> F2
typealias F2 = (F1) -> Int

val x: <!OTHER_ERROR!>A<!> = TODO()
