package love.chihuyu.skylife.gacha

import love.chihuyu.skylife.Command
import love.chihuyu.skylife.data.GachaData
import love.chihuyu.skylife.data.GachaDataRecord
import love.chihuyu.skylife.util.addOrDropItem
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import kotlin.reflect.full.memberProperties

object GachaCommand : Command("gacha") {

    // そのうち when だろうけどネストが深くなりすぎるので他の方法を考えたい by てんじゅ
    override fun onCommand(sender: CommandSender, label: String, args: Array<out String>) {
        if (!(sender is Player && sender.hasPermission("skylife.command.gacha") && args.size <= 4)) return
        if (args[0] != "勤労感謝ガチャ") return

        val target = sender.server.onlinePlayers.find { it.displayName == args[1] } ?: return
        val gacha = GachaData::class.memberProperties.find { it.name == args[2] }?.get(GachaData) as GachaDataRecord
        val amount = args[3].toIntOrNull() ?: 1
        repeat(amount) {
            target.inventory.addOrDropItem(gacha.getGachaItem(amount))
        }
    }

    override fun onTabComplete(sender: CommandSender, label: String, args: Array<out String>): List<String>? {
        val players = sender.server.onlinePlayers.toTypedArray()
        // val gachaNames = GachaData.keys.toList()

        return when (args.size) {
            1 -> players.map { it.displayName }
            // 2 -> gachaNames
            else -> null
        }
    }
}
