package love.chihuyu.skylife.gachastorage

import love.chihuyu.skylife.base.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object GachaStorageCommand : Command("gachastorage") {
    override fun onCommand(sender: CommandSender, label: String, args: Array<out String>): Boolean {
        if (sender !is Player) return false
        if (!sender.hasPermission("skylife.command.gachastorage")) return false
        GachaStorageGui.open(sender)
        return true
    }

    override fun onTabComplete(sender: CommandSender, label: String, args: Array<out String>) =
        listOf<String>()
}
