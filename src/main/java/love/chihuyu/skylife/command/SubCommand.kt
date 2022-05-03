package love.chihuyu.skylife.command

import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class SubCommand(
    val path: List<Set<String>>,
    val name: Set<String>,
    private val argsRange: IntRange?,
    private val completions: List<(String) -> List<String>>?,
    run: OnCommand,
) {
    val run: (CommandSender, List<String?>) -> Boolean = { sender, args ->
        if (argsRange != null && args.size !in argsRange) false
        else try {
            run(CommandUtil, sender, args)
            true
        } catch (e: Throwable) {
            if (e is IllegalArgumentException) {
                if (sender is Player) sender.sendRawMessage(e.message ?: TODO("Fallback string"))
                false
            } else throw e
        }
    }
}
