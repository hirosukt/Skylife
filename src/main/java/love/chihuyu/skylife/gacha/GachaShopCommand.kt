package love.chihuyu.skylife.gacha

import love.chihuyu.skylife.base.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object GachaShopCommand : Command("gachashop") {
    override fun onCommand(sender: CommandSender, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) return false
        if (!sender.hasPermission("skylife.command.gachashop")) return false
        GachaShopGui.open(sender)
        return true
    }

    override fun onTabComplete(sender: CommandSender, label: String, args: Array<out String>) =
        listOf<String>()
}
