package love.chihuyu.skylife.commands

class CommandDSL(name: String, block: CommandDSL.() -> Unit) : CommandBuilder(name) {
    operator fun String.invoke(block: CommandDSL.() -> Unit) {
        push(this)
        this@CommandDSL.block()
        pop()
    }

//    interface ElonMusc {
//        operator fun invoke(vararg completions: (String) -> List<String>, onCommand: OnCommand<Unit>)
//    }
//    operator fun String.invoke(sizeRange: IntRange): ElonMusc {
//        val commandName = this
//        return object : ElonMusc {
//            override operator fun invoke(
//                vararg completions: (String) -> List<String>,
//                onCommand: OnCommand<Unit>,
//            ) = add(commandName, sizeRange, completions.toList(), onCommand)
//        }
//    }

    val tmpName = mutableListOf<String>()
    infix fun String.or(other: String): String {
        tmpName.add(this)
        return other
    }
    infix fun String.or(subCommand: SubCommand) {
        subCommand.name
    }

    object ElonMusk {
        operator fun invoke(vararg completions: (String) -> List<String>, onCommand: OnCommand<Unit>) {

        }
    }
    operator fun String.invoke(range: IntRange): ElonMusk
    operator fun String.invoke(range: IntRange, onCommand: OnCommand<Unit>)
    operator fun String.invoke(onCommand: OnCommand<Unit>)

    init {
        this.block()
    }
}
