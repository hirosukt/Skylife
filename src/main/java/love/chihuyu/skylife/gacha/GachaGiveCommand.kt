package love.chihuyu.skylife.gacha

import love.chihuyu.skylife.Command
import love.chihuyu.skylife.data.GachaData
import love.chihuyu.skylife.util.addOrDropItem
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object GachaGiveCommand : Command("gachagive") {
    override fun onCommand(sender: CommandSender, label: String, args: Array<out String>) {
        if (sender !is Player) return
        if (!sender.hasPermission("skylife.command.gachagive")) {
            sender.sendRawMessage("このコマンドを実行する権限がありません。")
            return
        }

        // TODO: Error Handling
        val target = sender.server.onlinePlayers.find { it.displayName == args[0] } ?: return
        val gacha = GachaData.pairString[args[1]] ?: return
        val amount = try {
            args[2].toIntOrNull() ?: 1
        } catch (e: IndexOutOfBoundsException) {
            1
        }

        target.inventory.addOrDropItem(gacha.getItem(amount))
    }

    override fun onTabComplete(
        sender: CommandSender,
        label: String,
        args: Array<out String>
    ): List<String> {
        val playerNames = sender.server.onlinePlayers.toTypedArray().map { it.displayName }
        val gachaNames = GachaData.pairString.keys.toList()
        val amounts = listOf("1", "16", "32", "64")

        return when (args.size) {
            1 -> playerNames
            2 -> gachaNames
            3 -> amounts
            else -> listOf()
        }.filter { it.contains(args[args.size - 1]) }
    }
}
