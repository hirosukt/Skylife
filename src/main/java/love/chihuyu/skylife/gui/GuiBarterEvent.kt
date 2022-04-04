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

        fun setTradeItems() {
            (0..53).forEach {
                if (it % 9 >= 2) return
                val item = barterInv.getItem(it)
                if (item !== null) tradeItems.add(item.type)
            }
        }

        fun setTradableItems() {
            (0..53).forEach {
                if (it % 9 >= 3) barterInv.setItem(it, GuiBarter.fillPanel)
            }
            ItemDataManager
                .tradable(tradeItems)
                .forEach { it.forEach { item -> tradableItems.add(item) } }
        }

        fun updateInventoryItems() {
            setTradeItems()
            setTradableItems()
            val chunkedTradableItems = tradableItems.chunked(36)
            var index = 0
            (0..53).forEach {
                if (it % 9 > 2) {
                    val material = chunkedTradableItems[pageTemp[player] ?: 0][index++]
                    barterInv.setItem(it, ItemUtil.create(material))
                }
            }
            player.updateInventory()
        }

        if (clickedInvType == playerInv.type) {
            val clone = clickedItem.clone()
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
                val clone = clickedItem.clone()
                barterInv.setItem(event.slot, ItemUtil.create(Material.AIR))
                playerInv.addItem(clone).forEach { player.world.dropItemNaturally(player.location, it.value) }
            } else {
                when (event.click) {
                    ClickType.RIGHT -> {
                        // 一個取る処理
                        updateInventoryItems()
                    }
                    ClickType.LEFT -> {
                        // 最大1スタック取る処理
                    }
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
