package love.chihuyu.skylife.commands

import love.chihuyu.skylife.util.padLast
import love.chihuyu.skylife.util.require

class SubCommand(
    val path: List<List<String>>,
    val name: List<String>,
    val sizeRange: IntRange?,
    val completions: List<(String) -> List<String>> = listOf(),
    onCommand: OnCommand<Boolean>,
) {
    val onCommand: OnCommand<Boolean> = { sender, args ->
        if (sizeRange != null) args.size
            .require({ sizeRange.first <= args.size }) { "Too few arguments" }
            .require({ args.size <= sizeRange.last }) { "Too many arguments" }
        onCommand(sender, args.drop(path.size + 1).padLast(sizeRange.last, null))
    }
    init {
        require(completions.isEmpty() || sizeRange == null || completions.size == sizeRange.last) { "Not enough completions" }
    }
}
