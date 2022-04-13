package love.chihuyu.skylife.gacha

import love.chihuyu.skylife.barter.constants.Panels
import love.chihuyu.skylife.data.GachaData
import org.bukkit.Bukkit
import org.bukkit.entity.HumanEntity

object GachaShopGui {
    // REVIEW: Static な Gui として扱えているかどうか (複垢で同時に確認する必要がある)
    private val gachaShopGui = Bukkit.createInventory(null, 27, "GachaShop")
        .also { gui ->
            repeat(gui.size) { gui.setItem(it, Panels.fill) }
            GachaData.buyables.values.mapNotNull { it.shopData }
                .forEach { gui.setItem(it.slot, it.getItem(1)) }
        }

    fun open(player: HumanEntity) {
        player.openInventory(gachaShopGui)
    }
}
