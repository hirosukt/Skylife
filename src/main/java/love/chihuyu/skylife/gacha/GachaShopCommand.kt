package love.chihuyu.skylife.gacha

import love.chihuyu.skylife.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object GachaShopCommand : Command("gacha") {
    override fun onCommand(sender: CommandSender, label: String, args: Array<out String>) {
        if (sender !is Player) return
        if (sender.hasPermission("skylife.command.gacha")) GachaShopGui.open(sender)
    }

    override fun onTabComplete(sender: CommandSender, label: String, args: Array<out String>) =
        listOf<String>()
}