package love.chihuyu.skylife.command

import love.chihuyu.skylife.Skylife.Companion.plugin

object CommandUtil {
    fun badArg(message: String): Nothing =
        throw IllegalArgumentException(message)

    fun String.resolvePlayer() =
        plugin.server.onlinePlayers.find { it.name == this }
}
