package love.chihuyu.skylife.commands

import org.bukkit.ChatColor
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.entity.Player
import org.bukkit.command.Command as BukkitCommand

typealias OnCommand<T> = CommandUtil.(CommandSender, List<String?>) -> T

open class CommandBuilder(val name: String) : CommandExecutor, TabCompleter {
    val commands = mutableListOf<SubCommand>()
    val current = mutableListOf<MutableList<String>>()

    fun push(name: String) = current.add(mutableListOf(name))
    fun pop() {
        require(current.isNotEmpty()) { "pop() was called more times than push()" }
        current.removeLast()
    }

    fun add(name: String, sizeRange: IntRange, completions: List<(String) -> List<String>>, block: OnCommand<Unit>) {
        commands.add(
            SubCommand(
                current.toList(), name, sizeRange, completions,
                fun CommandUtil.(sender, args): Boolean = try {
                    block(this, sender, args)
                    true
                } catch (e: Throwable) {
                    when (e) {
                        is IllegalArgumentException,
                        is IllegalStateException ->
                            if (sender is Player) sender.sendRawMessage(e.message ?: e::class.simpleName ?: "${ChatColor.RED}some error asadfsdfsfadsf")
                        else -> throw e
                    }
                    false
                }
            )
        )
    }

    private fun <T> List<T>.containsHead(to: List<T>) = to.size <= this.size && to.withIndex().all { (i, v1) -> v1 == this[i] }

    override fun onCommand(sender: CommandSender, command: BukkitCommand, label: String, argArr: Array<out String>): Boolean {
        val args = argArr.toList()
        val subCommand = commands.find { args.containsHead(it.path) } ?: return false
        return subCommand.onCommand(CommandUtil, sender, args.toList())
    }

    override fun onTabComplete(sender: CommandSender, command: BukkitCommand, label: String, argArr: Array<out String>): MutableList<String> {
        val args = argArr.toList()
        val subCommand = commands.find { args.toList().containsHead(it.path) }

        TODO("Find the right subcommand or asdf parameters, and return completion")
    }
}
