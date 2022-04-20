package love.chihuyu.skylife.base.command

import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter
import org.bukkit.command.Command as BukkitCommand

/**
 * @param sender [CommandSender]
 * @param args [List]<[String]>
 */
typealias OnCommand = (CommandSender, List<String>) -> Boolean

class SubCommandBuilder(val name: String) {
    private val commands = mutableMapOf<List<String>, OnCommand>()
    private var currentSubCommand = mutableListOf<String>()

    private fun <T> List<T>.startsWith(list: List<T>) =
        this.size >= list.size && list.withIndex().all { (i, v) -> this[i] == v }

    fun startSubCommand(subCommand: String) {
        currentSubCommand.add(subCommand)
    }

    fun endSubCommand() {
        check(currentSubCommand.isNotEmpty()) { "endSubCommand was called before startSubCommand" }
        currentSubCommand.removeLast()
    }

    fun registerCommand(onCommand: OnCommand) {
        commands[currentSubCommand.toList()] = onCommand
    }

    interface Command : CommandExecutor, TabCompleter
    fun build() = object : Command {
        override fun onCommand(sender: CommandSender, command: BukkitCommand, label: String, args: Array<String>): Boolean {
            val argList = args.toList()
            val (_, onCommand) = commands.entries
                .filter { (path) -> argList.startsWith(path) }
                .maxByOrNull { (path) -> path.size } ?: return false
            return onCommand(sender, argList)
        }

        override fun onTabComplete(sender: CommandSender, command: BukkitCommand, label: String, args: Array<String>): MutableList<String>? {
            val (rest, last) = args.withIndex().groupBy { (i) -> i <= args.size - 1 }.values.toList()

            val x = if (rest.isNotEmpty()) commands.entries.find { (x) -> x.startsWith(rest) } else null ?: return null

            TODO("at this point my brain stopped working")
            return mutableListOf()
        }
    }
}
