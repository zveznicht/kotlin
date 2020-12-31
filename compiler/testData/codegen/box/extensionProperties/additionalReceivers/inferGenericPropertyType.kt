class Result<T>(val x: String)

with<Result<T>>
val <T> result: Result<T> get() = this@Result

fun <T> Result<T>.x(): T {
    with(result) {
        return x
    }
}

fun box(): String {
    with(Result<String>("OK")) {
        return x()
    }
}