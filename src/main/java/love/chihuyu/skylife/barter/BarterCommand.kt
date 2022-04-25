package love.chihuyu.skylife.barter

import love.chihuyu.skylife.base.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object BarterCommand : Command("barter") {
    override fun onCommand(sender: CommandSender, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) return false
        if (!sender.hasPermission("skylife.command.barter")) return false
        BarterGui.open(sender)
        return true
    }

    override fun onTabComplete(sender: CommandSender, label: String, args: Array<out String>) =
        listOf<String>()
}
