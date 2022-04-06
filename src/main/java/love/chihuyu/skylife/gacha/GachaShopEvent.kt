package love.chihuyu.skylife.gacha

import love.chihuyu.skylife.data.GachaData
import love.chihuyu.skylife.util.Sounds
import love.chihuyu.skylife.util.addOrDropItem
import love.chihuyu.skylife.util.getCustomModelDataOrNull
import love.chihuyu.skylife.util.removeAsPossible
import org.bukkit.Sound
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.ClickType
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.inventory.InventoryOpenEvent
import org.bukkit.inventory.PlayerInventory

object GachaShopEvent : Listener {

    @EventHandler
    fun onClick(event: InventoryClickEvent) {
        if (event.view.title != "GachaShop") return
        event.isCancelled = true

        val clickedInvType = event.clickedInventory?.type ?: return
        val playerInv = event.view.bottomInventory as PlayerInventory
        if (clickedInvType == playerInv.type) return
        val clickedItem = event.clickedInventory?.getItem(event.slot) ?: return

        val boughtItemData = GachaData.buyables[clickedItem.getCustomModelDataOrNull()] ?: return
        val shopData = boughtItemData.shopData!!
        val price = shopData.price

        val amount = when (event.click) {
            ClickType.SHIFT_LEFT -> 64
            ClickType.SHIFT_RIGHT -> 16
            ClickType.LEFT -> 1
            ClickType.RIGHT -> 32
            else -> 0
        }

        val player = event.whoClicked as Player
        val removedCount = playerInv.removeAsPossible(amount * price.second, price.first)

        playerInv.addOrDropItem(boughtItemData.getItem(removedCount))
        player.playSound(player.location, if (removedCount != 0) Sound.ENTITY_EXPERIENCE_ORB_PICKUP else Sounds.MEOW, 0.8f, 1f)
    }

    @EventHandler
    fun onOpen(event: InventoryOpenEvent) {
        if (event.view.title != "GachaShop") return

        val player = event.player as Player
        player.playSound(player.location, Sound.BLOCK_ENDER_CHEST_OPEN, 0.8f, 1f)
    }

    @EventHandler
    fun onClose(event: InventoryCloseEvent) {
        if (event.view.title != "GachaShop") return

        val player = event.player as Player
        player.playSound(player.location, Sound.BLOCK_ENDER_CHEST_CLOSE, 0.8f, 1f)
    }
}
