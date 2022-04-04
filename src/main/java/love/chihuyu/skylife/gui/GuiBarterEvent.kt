package love.chihuyu.skylife.gui

import love.chihuyu.skylife.data.ItemDataManager
import love.chihuyu.skylife.util.ItemUtil
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.ClickType
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.event.inventory.InventoryCloseEvent

object GuiBarterEvent : Listener {

    val pageTemp = hashMapOf<Player, Int>()

    @EventHandler
    fun onClick(event: InventoryClickEvent) {
        if (event.view.title != "Barter") return
        event.isCancelled = true

        val clickedItem = event.currentItem ?: return
        val player = event.whoClicked as Player
        val clickedInvType = event.clickedInventory?.type
        val playerInv = event.view.bottomInventory
        val barterInv = event.view.topInventory

        val tradeItems = mutableListOf<Material>()
        val tradableItems = mutableSetOf<Material>()

        fun loadTradeItemsFromInv() {
            (0..53).forEach {
                if (it % 9 <= 1) {
                    val item = barterInv.getItem(it)
                    if (item !== null) tradeItems.add(item.type)
                }
            }
        }

        fun loadTradableItemsFromInv() {
            ItemDataManager
                .tradable(tradeItems)
                .forEach { it.forEach { item -> tradableItems.add(item) } }
        }

        fun updateInventoryItems() {
            loadTradeItemsFromInv()
            loadTradableItemsFromInv()
            val chunkedTradableItems = tradableItems.chunked(36)
            var index = 0
            (0..53).forEach {
                if (it % 9 > 2) {
                    try {
                        val material = chunkedTradableItems[pageTemp[player] ?: 0][index++]
                        barterInv.setItem(it,
                            ItemUtil.create(material, lore = listOf("左クリックで1個交換できます", "右クリックで1st交換できます")))
                    } catch (e: IndexOutOfBoundsException) {
                        barterInv.setItem(it, GuiBarter.fillPanel)
                    }
                }
            }
            player.updateInventory()
        }

        if (clickedInvType == playerInv.type) {
            val clone = ItemUtil.create(clickedItem.type, count = clickedItem.amount)
            playerInv.setItem(event.slot, ItemUtil.create(Material.AIR))
            barterInv.addItem(clone).forEach { playerInv.addItem(it.value) }
            updateInventoryItems()
        } else if (clickedInvType == barterInv.type) {
            val slot = event.slot

            // paging
            if (slot == 38 && pageTemp[player] != 0) {
                pageTemp[player] = pageTemp[player]?.dec() ?: 0
            } else if (slot == 47 && barterInv.getItem(53)?.itemMeta?.displayName != " ") {
                pageTemp[player] = pageTemp[player]?.inc() ?: 0
            }

            if (slot % 9 <= 1) {
                val clone = ItemUtil.create(clickedItem.type, count = clickedItem.amount)
                barterInv.setItem(event.slot, ItemUtil.create(Material.AIR))
                playerInv.addItem(clone).forEach { player.world.dropItemNaturally(player.location, it.value) }
            } else if (slot % 9 >= 3) {

                fun isTradable(trade: Material): Boolean {
                    return ItemDataManager.tradable(clickedItem.type)?.contains(trade) ?: false
                }

                fun trade() {
                    for (i in 0..53) {
                        if (i % 9 <= 1) {
                            val item = barterInv.getItem(i)
                            if (item == null || !isTradable(item.type)) continue
                            item.amount -= 1
                            playerInv.addItem(ItemUtil.create(clickedItem.type))
                            break
                        }
                    }
                }

                when (event.click) {
                    ClickType.RIGHT -> repeat(64) { trade() }
                    ClickType.LEFT -> trade()
                    else -> return
                }
            }
            updateInventoryItems()
        }
    }

    @EventHandler
    fun onClose(event: InventoryCloseEvent) {
        if (event.view.title != "Barter") return

        val playerInv = event.view.bottomInventory
        val barterInv = event.view.topInventory
        val player = event.player

        (0..53).forEach {
            if (it % 9 <= 1) {
                val item = barterInv.getItem(it)
                if (item !== null) playerInv.addItem(item).forEach { drop ->
                    player.world.dropItemNaturally(player.location, drop.value)
                }
            }
        }
    }
}
