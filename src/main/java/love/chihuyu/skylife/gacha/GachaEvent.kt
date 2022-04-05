package love.chihuyu.skylife.gacha

import love.chihuyu.skylife.util.ItemUtil
import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent

object GachaEvent : Listener {

    @EventHandler
    fun onInteract(event: PlayerInteractEvent) {
        if (event.action !in listOf(Action.RIGHT_CLICK_AIR, Action.RIGHT_CLICK_BLOCK) && !event.hasItem()) return

        val player = event.player
        val clickedItem = event.item

        if (event.item?.itemMeta?.displayName?.contains("勤労感謝ガチャ") == true) {
            event.isCancelled = true

            if (event.item?.itemMeta?.displayName?.contains("勤労感謝ガチャ") == true) {
                // 確率が必要
                player.world.dropItemNaturally(
                    player.location,
                    ItemUtil.create(
                        listOf(
                            Material.WOODEN_SHOVEL,
                            Material.WOODEN_PICKAXE,
                            Material.WOODEN_AXE,
                            Material.WOODEN_HOE,
                            Material.STONE_SHOVEL,
                            Material.STONE_AXE,
                            Material.STONE_HOE,
                            Material.STONE_PICKAXE,
                            Material.IRON_PICKAXE,
                            Material.IRON_SHOVEL,
                            Material.IRON_AXE,
                            Material.IRON_HOE,
                            Material.GOLDEN_PICKAXE,
                            Material.GOLDEN_AXE,
                            Material.GOLDEN_SHOVEL,
                            Material.GOLDEN_HOE,
                            Material.DIAMOND_AXE,
                            Material.DIAMOND_HOE,
                            Material.DIAMOND_PICKAXE,
                            Material.DIAMOND_SHOVEL
                        ).random()
                    )
                )
                player.playSound(player.location, Sound.ENTITY_PLAYER_BURP, 0.8f, 1f)
                clickedItem?.amount = clickedItem?.amount?.dec() ?: 0
            }
        }
    }
}
