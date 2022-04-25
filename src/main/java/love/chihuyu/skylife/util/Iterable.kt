package love.chihuyu.skylife.util

import love.chihuyu.skylife.gacha.Yululi.concatBefore

fun <T> Iterable<Iterable<T>>.combinations(): Sequence<Iterable<T>> =
    if (this.count() <= 1) this.asSequence()
    else sequence {
        for (a in this@combinations.elementAt(0))
            for (b in this@combinations.drop(1).combinations())
                yield((a concatBefore b).asIterable())
    }

fun <T> List<T>.padLast(targetSize: Int, value: T) =
    if (this.size < targetSize)
        this + List(targetSize - this.size) { value }
    else this
