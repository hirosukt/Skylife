package love.chihuyu.skylife.gui

import love.chihuyu.skylife.data.ItemDataManager
import love.chihuyu.skylife.gui.GuiBarter.page
import love.chihuyu.skylife.gui.constants.Areas
import love.chihuyu.skylife.gui.constants.Panels
import love.chihuyu.skylife.util.ItemUtil
import love.chihuyu.skylife.util.MEOW
import love.chihuyu.skylife.util.addOrDropItem
import love.chihuyu.skylife.util.removeAsPossible
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.ClickType
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent
import org.bukkit.event.inventory.InventoryOpenEvent
import org.bukkit.inventory.PlayerInventory

object GuiBarterEvent : Listener {

    private val tradableLore = listOf(
        "左クリック ->  1個",
        "シフト＋左 -> 64個",
        "右クリック -> 32個",
        "シフト＋右 -> 16個"
    )

    @EventHandler
    fun onClick(event: InventoryClickEvent) {
        if (event.view.title != "Barter") return

        event.isCancelled = true

        val clickedItem = event.currentItem ?: return
        val player = event.whoClicked as Player
        val clickedInvType = event.clickedInventory?.type
        val playerInv = event.view.bottomInventory as PlayerInventory
        val barterInv = event.view.topInventory

        if (clickedInvType == playerInv.type) {
            val clone = clickedItem.clone()

            playerInv.setItem(event.slot, ItemUtil.create(Material.AIR))
            barterInv.addItem(clone).forEach { playerInv.addItem(it.value) }
        } else if (clickedInvType == barterInv.type) {
            val slot = event.slot

            if (slot in Areas.trading) {
                val clone = clickedItem.clone()

                barterInv.setItem(event.slot, ItemUtil.create(Material.AIR))
                player.inventory.addOrDropItem(clone)
            }

            if (slot in Areas.tradable) {
                if (!ItemDataManager.isTradable(clickedItem.type)) return

                val tradableItems = ItemDataManager.getTradableItems(clickedItem.type)
                val amount = when (event.click) {
                    ClickType.SHIFT_LEFT -> 64
                    ClickType.SHIFT_RIGHT -> 16
                    ClickType.LEFT -> 1
                    ClickType.RIGHT -> 32
                    else -> 0
                }
                val removedCount = barterInv.removeAsPossible(amount) { position, item ->
                    position in Areas.trading && tradableItems.contains(item.type)
                }

                playerInv.addOrDropItem(ItemUtil.create(clickedItem.type, count = removedCount))
                player.playSound(player.location, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.8f, 1f)
            }

            if (slot in listOf(38, 47)) {
                var sound = MEOW
                val required = if (slot == 38) page[player]!!.dec() else page[player]!!.inc()

                if (if (slot == 38) required >= 0 else barterInv.getItem(53) != Panels.fill) {
                    page[player] = required
                    sound = Sound.UI_BUTTON_CLICK
                }

                player.playSound(player.location, sound, 0.8f, 1f)
            }
        }

        fun updateGui() {
            val tradingItems = Areas.trading.mapNotNull(barterInv::getItem).map { it.type }
            val tradableItems = ItemDataManager.getTradableItems(*tradingItems.toTypedArray())

            val currentPage = page[player]!!
            val chunkedTradableItems = tradableItems.chunked(36).getOrNull(currentPage) ?: listOf()

            for ((i, slot) in Areas.tradable.withIndex()) {
                val item = chunkedTradableItems.getOrNull(i)
                    ?.let { ItemUtil.create(it, lore = tradableLore) }
                    ?: Panels.fill
                barterInv.setItem(slot, item)
            }

            player.updateInventory()
        }
        repeat(page[player]!! + 1) {
            updateGui()
            if (barterInv.getItem(3) != Panels.fill) return@repeat
            page[player] = it
        }
    }

    @EventHandler
    fun onClose(event: InventoryCloseEvent) {
        if (event.view.title != "Barter") return

        val playerInv = event.view.bottomInventory as PlayerInventory
        val barterInv = event.view.topInventory
        val leftoverItems = Areas.trading.mapNotNull(barterInv::getItem)
        val player = event.player as Player

        playerInv.addOrDropItem(*leftoverItems.toTypedArray())
        player.playSound(player.location, Sound.BLOCK_ENDER_CHEST_CLOSE, 0.8f, 1f)
    }

    @EventHandler
    fun onOpen(event: InventoryOpenEvent) {
        if (event.view.title != "Barter") return

        val player = event.player as Player

        player.playSound(player.location, Sound.BLOCK_ENDER_CHEST_OPEN, 0.8f, 1f)
    }
}
