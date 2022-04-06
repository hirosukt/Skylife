package love.chihuyu.skylife.gacha

import love.chihuyu.skylife.data.GachaData
import love.chihuyu.skylife.gui.constants.Panels
import org.bukkit.Bukkit
import org.bukkit.entity.HumanEntity

object GachaShopGui {
    fun open(player: HumanEntity) {
        val inventory = Bukkit.createInventory(null, 27, "GachaShop")

        (0 until inventory.size).forEach { inventory.setItem(it, Panels.fill) }
        GachaData.buyables.values.mapNotNull { it.shopData }.forEach {
            inventory.setItem(it.slot, it.getItem(1))
        }

        player.openInventory(inventory)
    }
}
