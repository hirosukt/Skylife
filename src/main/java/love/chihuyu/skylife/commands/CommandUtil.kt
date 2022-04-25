package love.chihuyu.skylife.commands

import love.chihuyu.skylife.Skylife.Companion.plugin
import love.chihuyu.skylife.util.require

object CommandUtil {
    class ExpectedExeption {
        val message: String
        constructor(message: String) {
            this.message = message
        }
    }

    fun String.resolvePlayer() = plugin.server.getPlayer(this)

    fun badArgument(message: String = ""): Nothing = throw IllegalArgumentException(message)

    fun <T> T?.requireNotNull(lazyMessage: () -> String): T {
        return this.require({ it !== null }, lazyMessage)!!
    }
}
