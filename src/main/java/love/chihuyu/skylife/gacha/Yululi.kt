package love.chihuyu.skylife.gacha

import love.chihuyu.skylife.commands.OnCommand

object Yululi {
    val dad: Map<List<List<String>>, OnCommand<Unit>> = mapOf(
        listOf(listOf("gacha", "g"), listOf("give", "g")) to { sender, args -> println("gacha give sdfasdafsafasffa") }
    )

    infix fun <T> T.concatBefore(iterable: Iterable<T>) = sequence {
        yield(this@concatBefore)
        for (x in iterable) yield(x)
    }

    fun <T> Iterable<Iterable<T>>.combinations(): Sequence<Iterable<T>> =
        if (this.count() <= 1) this.asSequence()
        else sequence {
            for (a in this@combinations.elementAt(0))
                for (b in this@combinations.drop(1).combinations())
                    yield((a concatBefore b).asIterable())
        }

    fun findXCommand(comund: List<String>) {
        val mom = dad.entries.find { (path) -> path.combinations().any { comund. } }?.value ?: throw Throwable("nooooooooooooooooo")
    }
    fun findCCommand() {

    }
}
