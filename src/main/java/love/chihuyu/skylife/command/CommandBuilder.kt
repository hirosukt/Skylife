package love.chihuyu.skylife.command

import love.chihuyu.skylife.Skylife.Companion.plugin
import love.chihuyu.skylife.util.combinations
import love.chihuyu.skylife.util.matchHead
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter

open class CommandBuilder(val name: String) : CommandExecutor, TabCompleter {
    fun register() {
        val command = plugin.getCommand(name) ?: throw IllegalStateException()
        command.setExecutor(this)
        command.tabCompleter = this
    }

    private val commands = mutableListOf<SubCommand>()
    private val currentPath = mutableListOf<Set<String>>()

    fun startSubCommand(names: Set<String>) {
        require(names.isNotEmpty()) { "At least one name is required" }
        currentPath.add(names)
    }

    fun endSubCommand() {
        require(currentPath.isNotEmpty()) { "Start subcommand first" }
        currentPath.removeLast()
    }

    fun registerSubCommand(names: Set<String>, run: OnCommand) =
        registerSubCommand(names, null, null, run)

    fun registerSubCommand(
        names: Set<String>,
        argsRange: IntRange? = null,
        completions: List<(String) -> List<String>>? = null,
        run: OnCommand,
    ) {
        require(names.isNotEmpty()) { "At least one name is required" }
        commands.add(SubCommand(currentPath.toList(), names, argsRange, completions, run))
    }

    override fun onCommand(sender: CommandSender, command: Command, label: String, argsArray: Array<out String>): Boolean {
        val args = argsArray.toList()
        val subCommand = commands.find { command ->
            command.path
                .combinations()
                .any(args::matchHead)
        } ?: return false

        return subCommand.run(sender, args)
    }

    val maxPathLength by lazy { commands.maxOf { it.path.size } }
    override fun onTabComplete(sender: CommandSender, command: Command, label: String, argsArray: Array<out String>): MutableList<String>? {
        val args = argsArray.toList()
        val argsExceptLast = args.dropLast(1)
        val subCommand = commands.filter {
            it.path
                .combinations()
                .any(args::matchHead)
        }
        return mutableListOf()
        TODO("Find the right subcommand or parameters, and return completion")
    }

    val yululi = listOf(
        SubCommand(path = listOf(setOf("a", "b"), setOf("c", "d")), name = setOf("e", "f"), null, null) { _, _ -> },
        SubCommand(path = listOf(setOf("a", "b"), setOf("c", "d")), name = setOf("g", "h"), null, null) { _, _ -> },
        SubCommand(path = listOf(setOf("a", "b"), setOf("c", "d")), name = setOf("i", "j"), null, null) { _, _ -> },
        SubCommand(path = listOf(setOf("a", "b"), setOf("k", "l")), name = setOf("m", "n"), null, null) { _, _ -> },
        SubCommand(path = listOf(setOf("a", "b"), setOf("k", "l")), name = setOf("o", "p"), null, null) { _, _ -> },
    )
}
