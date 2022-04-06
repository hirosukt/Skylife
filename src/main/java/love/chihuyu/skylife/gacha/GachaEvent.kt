package love.chihuyu.skylife.gacha

import love.chihuyu.skylife.data.GachaData
import love.chihuyu.skylife.util.ItemUtil
import love.chihuyu.skylife.util.addOrDropItem
import org.bukkit.ChatColor
import org.bukkit.Sound
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.player.PlayerItemBreakEvent

object GachaEvent : Listener {
    @EventHandler
    fun onToolBroken(event: PlayerItemBreakEvent) {
        val player = event.player
        player.inventory.addOrDropItem(GachaData.KinroKanshaGacha.getGachaItem())
        player.playSound(player.location, Sound.ENTITY_CAT_AMBIENT, 1f, 1f)
        player.sendRawMessage("${ChatColor.LIGHT_PURPLE}[Nyamazon]${ChatColor.RESET} 勤労感謝ガチャをお届けしました。")
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
        // java.lang.IllegalStateException: We don't have CustomModelData! Check hasCustomModelData first!
        if (usedItem.itemMeta?.hasCustomModelData() == false) return
        val gacha = GachaData.pairCustomModelData[usedItem.itemMeta!!.customModelData] ?: return

        event.isCancelled = true

        val player = event.player
        repeat(if (player.isSneaking) usedItem.amount else 1) {
            val resultItem = ItemUtil.create(
                gacha.chooseMaterial(),
                lore = lore(player.displayName)
            )
            usedItem.amount -= 1
            player.inventory.addOrDropItem(resultItem)
        }
        player.playSound(player.location, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f)
    }
}
