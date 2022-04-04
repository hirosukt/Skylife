package love.chihuyu.skylife.gui

import love.chihuyu.skylife.Command
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

object GuiBarterCommand : Command("barter") {
    override fun onCommand(sender: CommandSender, label: String, args: Array<out String>) {
        if (sender !is Player || !sender.hasPermission("skylife.command.barter")) return

        GuiBarter.open(sender)
    }

    override fun onTabComplete(sender: CommandSender, label: String, args: Array<out String>): List<String>? {
        TODO("Not yet implemented")
    }
}