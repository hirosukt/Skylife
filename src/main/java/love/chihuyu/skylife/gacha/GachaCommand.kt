package love.chihuyu.skylife.gacha

import love.chihuyu.skylife.Command
import love.chihuyu.skylife.util.ItemUtil
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object GachaCommand : Command("gacha") {
    override fun onCommand(sender: CommandSender, label: String, args: Array<out String>) {
        if (sender !is Player || !sender.hasPermission("skylife.command.gacha") || args.size > 2) return

        when (args[0]) {
            "give" -> {
                when (args[1]) {
                    "勤労感謝ガチャ" -> {
                        val item = ItemUtil.create(Material.DRAGON_HEAD,
                            ChatColor.AQUA.toString() + "-=+ 勤労感謝ガチャ +=-",
                            lore = listOf("勤勉な者のみが手にできるガチャ券"))

                        sender.inventory.addItem(item)
                    }
                }
            }
        }
    }

    override fun onTabComplete(sender: CommandSender, label: String, args: Array<out String>): List<String> {
        return emptyList()
    }
}