
fun main() {
    println(findFirst(arrayOf("a", "b", "c"), { it == "b" }))

    println(isSorted(listOf(1, 2, 3, 4), { current, next -> next >= current }))
}

fun <T> findFirst(ss: Array<T>, p: (T) -> Boolean): Int {
    tailrec fun go(n: Int): Int =
        when {
            n >= ss.size -> -1
            p(ss[n]) -> n
            else -> go(n + 1)
        }
    return go(0)
}

val <T> List<T>.tail: List<T>
    get() = drop(1)

val <T> List<T>.head: T
    get() = first()

fun <A> isSorted(aa: List<A>, order: (A, A) -> Boolean): Boolean {
    tailrec fun go(at: List<A>): Boolean =
        when {
            at.tail.isEmpty() -> true
            order(at.head, at.tail.head) -> go(at.tail)
            else -> false
        }
    return go(aa)
}

fun <A, B, C> uncurry(f: (A) -> (B) -> C): (A, B) -> C =
    { a, b ->  f(a)(b)  }

fun <A, B, C> curry(f: (A, B) -> C): (A) -> (B) -> C =
    { a -> { b -> f(a, b) } }

fun <A, B, C> partial1(a: A, f: (A, B) -> C): (B) -> C =
    { f(a, it) }