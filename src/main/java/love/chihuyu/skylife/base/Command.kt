package love.chihuyu.skylife.base

import love.chihuyu.skylife.Skylife.Companion.plugin
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter

abstract class Command(private val name: String) : CommandExecutor, TabCompleter {
    fun register() {
        val command = plugin.getCommand(name) ?: throw IllegalStateException()
        command.setExecutor(this)
        command.tabCompleter = this
    }

    override fun onCommand(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): Boolean {
        onCommand(sender, label, args)
        return true
    }

    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): List<String>? {
        return onTabComplete(sender, label, args)
    }

    abstract fun onCommand(sender: CommandSender, label: String, args: Array<out String>)

    abstract fun onTabComplete(
        sender: CommandSender,
        label: String,
        args: Array<out String>
    ): List<String>?
}