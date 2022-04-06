package love.chihuyu.skylife.gui

import love.chihuyu.skylife.data.ItemDataManager
import love.chihuyu.skylife.gui.constants.Areas
import love.chihuyu.skylife.gui.constants.Panels
import love.chihuyu.skylife.util.ItemUtil
import love.chihuyu.skylife.util.addOrDropItem
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

    val pageTemp = hashMapOf<Player, Int>()

    @EventHandler
    fun onClick(event: InventoryClickEvent) {
        if (event.view.title != "Barter") return
        event.isCancelled = true

        val clickedItem = event.currentItem ?: return
        val player = event.whoClicked as Player
        val clickedInvType = event.clickedInventory?.type
        val playerInv = event.view.bottomInventory as PlayerInventory
        val barterInv = event.view.topInventory

        fun updateGui() {
            val tradingItems = Areas.trading.mapNotNull(barterInv::getItem).map { it.type }
            val tradableItems = ItemDataManager.getTradableItems(*tradingItems.toTypedArray()).toList()

            val page = pageTemp[player] ?: 0
            val chunkedTradableItems = tradableItems.chunked(36).getOrNull(page) ?: listOf()

            for ((i, slot) in Areas.tradable.withIndex()) {
                barterInv.setItem(
                    slot,
                    if (i < chunkedTradableItems.size) {
                        ItemUtil.create(chunkedTradableItems[i], lore = tradableLore)
                    } else Panels.fill
                )
            }

            player.updateInventory()
        }

        if (clickedInvType == playerInv.type) {
            val clone = clickedItem.clone()
            playerInv.setItem(event.slot, ItemUtil.create(Material.AIR))
            barterInv.addItem(clone).forEach { playerInv.addItem(it.value) }
            updateGui()
        } else if (clickedInvType == barterInv.type) {
            val slot = event.slot

            // paging
            if (slot == 38 && pageTemp[player] != 0) {
                pageTemp[player] = pageTemp[player]?.dec() ?: 0
                player.playSound(player.location, Sound.UI_BUTTON_CLICK, 0.8f, 1f)
            } else if (slot == 47 && barterInv.getItem(53)?.itemMeta?.displayName != " ") {
                pageTemp[player] = pageTemp[player]?.inc() ?: 0
                player.playSound(player.location, Sound.UI_BUTTON_CLICK, 0.8f, 1f)
            }

            if (slot in Areas.trading) {
                val clone = clickedItem.clone()
                barterInv.setItem(event.slot, ItemUtil.create(Material.AIR))
                player.inventory.addOrDropItem(clone)
            } else if (slot in Areas.tradable) {
                if (!ItemDataManager.isTradable(clickedItem.type)) return

                val tradableItems = ItemDataManager.getTradableItems(clickedItem.type)
                val items = Areas.trading.mapNotNull(barterInv::getItem).filter { tradableItems.contains(it.type) }
                val max = items.sumOf { it.amount }

                fun trade(amount: Int) {
                    var sum = amount
                    for (item in items) {
                        val tmp = item.amount
                        item.amount = if (tmp < sum) 0 else tmp.minus(sum)
                        sum -= tmp
                        if (sum <= 0) break
                    }
                    playerInv.addOrDropItem(ItemUtil.create(clickedItem.type, count = amount))
                }

                val amount = when (event.click) {
                    ClickType.SHIFT_LEFT -> 64
                    ClickType.SHIFT_RIGHT -> 16
                    ClickType.LEFT -> 1
                    ClickType.RIGHT -> 32
                    else -> 0
                }.coerceAtMost(max)

                trade(amount)
                player.playSound(player.location, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.8f, 1f)
            }
            updateGui()

            if (barterInv.getItem(Areas.tradable.first())?.itemMeta?.displayName == " " && (pageTemp[player] ?: 0) > 0) {
                // とりあえず０
                pageTemp[player] = 0
                updateGui()
            }
        }
    }

    @EventHandler
    fun onClose(event: InventoryCloseEvent) {
        if (event.view.title != "Barter") return

        val playerInv = event.view.bottomInventory as PlayerInventory
        val barterInv = event.view.topInventory
        val player = event.player as Player

        player.playSound(player.location, Sound.BLOCK_ENDER_CHEST_CLOSE, 0.8f, 1f)

        val leftoverItems = Areas.trading.mapNotNull(barterInv::getItem)
        playerInv.addOrDropItem(*leftoverItems.toTypedArray())
    }

    @EventHandler
    fun onOpen(event: InventoryOpenEvent) {
        if (event.view.title != "Barter") return

        val player = event.player as Player

        player.playSound(player.location, Sound.BLOCK_ENDER_CHEST_OPEN, 0.8f, 1f)
    }
}
