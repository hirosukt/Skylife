package love.chihuyu.skylife.base.command

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter

open class SubCommandDSL(val name: String, build: SubCommandDSL.() -> Unit) : CommandExecutor, TabCompleter {
    private val builder = SubCommandBuilder(name)

    operator fun String.invoke(scope: SubCommandDSL.() -> Unit) {
        builder.startSubCommand(this)
        scope(this@SubCommandDSL)
        builder.endSubCommand()
    }

    interface CompletionSetter {
        open fun completions(vararg completers: (String) -> List<String>): Unit
    }
    operator fun String.invoke(range: IntRange, onCommand: SubCommandDSL.(CommandSender, List<String>) -> Boolean): CompletionSetter {
        builder.registerCommand { sender, args ->
            if (args.size !in range) false
            onCommand(this@SubCommandDSL, sender, args)
        }
        return object : CompletionSetter {

        }
    }

    private val command: SubCommandBuilder.Command by lazy {
        build(this)
        builder.build()
    }

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        return this.command.onCommand(sender, command, label, args)
    }

    override fun onTabComplete(sender: CommandSender, command: Command, label: String, args: Array<out String>): MutableList<String>? {
        return this.command.onTabComplete(sender, command, label, args)
    }
}
