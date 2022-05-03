package love.chihuyu.skylife.command

import org.bukkit.command.CommandSender

typealias OnCommand = CommandUtil.(CommandSender, List<String?>) -> Unit

class CommandDSL(name: String, block: CommandDSL.() -> Unit) : CommandBuilder(name) {
    private val names = mutableListOf<String>()

    object Or
    infix fun String.or(other: String): Or {
        names.add(this)
        names.add(other)
        return Or
    }
    infix fun String.or(other: Or): Or {
        names.add(this)
        return Or
    }
    infix fun Or.or(other: String): Or {
        names.add(other)
        return Or
    }
    infix fun Or.or(dummy: Or) = names.clear()

    fun String.invoke(block: CommandDSL.() -> Unit) {
        names.add(this)
        startSubCommand(names.toSet())
        block(this@CommandDSL)
        endSubCommand()
    }

    // "give" { sender, args -> }
    fun String.invoke(run: OnCommand): Or {
        names.add(this)
        registerSubCommand(names.toSet(), run)
        return Or
    }

    // "give" ({ listOf("a") }, { listOf("b") }) { sender, args -> }
    operator fun String.invoke(vararg completions: (String) -> List<String>, run: OnCommand): Or {
        names.add(this)
        registerSubCommand(names.toSet(), completions = completions.toList(), run = run)
        return Or
    }

    // "give" (1..2) { sender, args -> }
    operator fun String.invoke(argsRange: IntRange, run: OnCommand): Or {
        names.add(this)
        registerSubCommand(names.toSet(), argsRange, run = run)
        return Or
    }

    // "give" (1..2) ({ listOf("a") }, { listOf("b") }) { sender, args -> }
    interface MarkZuckerberg {
        operator fun invoke(vararg completions: (String) -> List<String>, run: OnCommand): Or
    }
    operator fun String.invoke(argsRange: IntRange): MarkZuckerberg {
        names.add(this)

        return object : MarkZuckerberg {
            override operator fun invoke(vararg completions: (String) -> List<String>, run: OnCommand): Or {
                registerSubCommand(names.toSet(), argsRange, completions.toList(), run)
                return Or
            }
        }
    }

    fun <String> (() -> List<String>).asConstant(): (String) -> List<String> {
        val value = this()
        return { value }
    }

    init {
        this.block()
    }
}
