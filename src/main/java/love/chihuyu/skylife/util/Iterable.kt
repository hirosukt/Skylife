package love.chihuyu.skylife.util

infix fun <T> T.appendBefore(i: Iterable<T>) = sequence {
    yield(this@appendBefore)
    yieldAll(i)
}

fun <T> Iterable<T>.matchHead(other: Iterable<T>): Boolean {
    val thisIterator = this.iterator()
    val otherIterator = other.iterator()

    while (true)
        if (thisIterator.hasNext()) when {
            otherIterator.hasNext() ->
                if (thisIterator.next() != otherIterator.next())
                    return false
            else -> return true
        } else return when {
            otherIterator.hasNext() -> false
            else -> true
        }
}

fun <T> Iterable<Iterable<T>>.combinations(): Sequence<Iterable<T>> =
    if (this.count() <= 1) this.asSequence()
    else sequence {
        for (a in this@combinations.elementAt(0))
            for (b in this@combinations.drop(1).combinations())
                yield((a appendBefore b).asIterable())
    }
