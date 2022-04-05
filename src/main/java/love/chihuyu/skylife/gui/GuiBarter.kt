package love.chihuyu.skylife.gui

import love.chihuyu.skylife.gui.constants.Areas
import love.chihuyu.skylife.gui.constants.Panels
import org.bukkit.Bukkit
import org.bukkit.entity.HumanEntity
import org.bukkit.entity.Player

object GuiBarter {

    fun open(player: HumanEntity) {
        GuiBarterEvent.pageTemp[player as Player] = 0

        val inventory = Bukkit.createInventory(null, 54, "Barter")
        Areas.tradable.forEach { inventory.setItem(it, Panels.fill) }
        Areas.separator.forEach {
            inventory.setItem(
                it,
                when (it) {
                    38 -> Panels.previousPage
                    47 -> Panels.nextPage
                    else -> Panels.separator
                }
            )
        }

        player.openInventory(inventory)
    }
}
