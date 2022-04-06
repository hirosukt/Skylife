package love.chihuyu.skylife.gacha

import love.chihuyu.skylife.data.GachaData
import love.chihuyu.skylife.util.ItemUtil
import love.chihuyu.skylife.util.addOrDropItem
import org.bukkit.Sound
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.player.PlayerItemBreakEvent

object GachaEvent : Listener {

    @EventHandler
    fun onToolBroken(event: PlayerItemBreakEvent) {
        event.player.inventory.addOrDropItem(gachaData.getGachaItem())
    }

    private fun lore(userName: String) = listOf(
        "所有者: $userName"
    )

    @EventHandler
    fun onInteract(event: PlayerInteractEvent) {
        if (event.action == Action.RIGHT_CLICK_BLOCK) {
            if (event.clickedBlock?.type?.isInteractable == true) return
        } else if (event.action != Action.RIGHT_CLICK_AIR && !event.hasItem()) return

        val usedItem = event.item ?: return
        if (usedItem.itemMeta?.hasCustomModelData() == false || usedItem.itemMeta!!.customModelData != 5) return

        val player = event.player
        event.isCancelled = true

        repeat(if (player.isSneaking) usedItem.amount else 1) {
            val resultItem = ItemUtil.create(
                gachaData.chooseMaterial(),
                lore = lore(player.displayName)
            )
            usedItem.amount -= 1
            player.inventory.addOrDropItem(resultItem)
        }

        player.playSound(player.location, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 0.8f, 1f)
    }
}
