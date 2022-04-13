package love.chihuyu.skylife.barter

import love.chihuyu.skylife.barter.constants.Areas
import love.chihuyu.skylife.barter.constants.Panels
import org.bukkit.Bukkit
import org.bukkit.entity.HumanEntity
import org.bukkit.entity.Player

object BarterGui {

    val page = hashMapOf<Player, Int>()

    fun open(player: HumanEntity) {
        val barterGui = Bukkit.createInventory(null, 54, "Barter")

        Areas.tradable.forEach { barterGui.setItem(it, Panels.fill) }
        Areas.separator.forEach {
            barterGui.setItem(
                it, when (it) {
                    38 -> Panels.prevPage
                    47 -> Panels.nextPage
                    else -> Panels.separate
                }
            )
        }

        this.page[player as Player] = 0
        player.openInventory(barterGui)
    }
}