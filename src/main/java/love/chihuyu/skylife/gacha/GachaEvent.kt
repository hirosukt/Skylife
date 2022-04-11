package love.chihuyu.skylife.gacha

import love.chihuyu.skylife.data.GachaData
import love.chihuyu.skylife.database.UserEntity
import love.chihuyu.skylife.scoreboard.ScoreboardStats
import love.chihuyu.skylife.util.ItemUtil
import org.bukkit.ChatColor
import org.bukkit.Sound
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.block.BlockPlaceEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.player.PlayerItemBreakEvent
import org.bukkit.event.player.PlayerItemConsumeEvent

object GachaEvent : Listener {

    private fun lore(userName: String) = listOf(
        "所有者: $userName"
    )

    fun nyamazon(gachaName: String) =
        "${ChatColor.LIGHT_PURPLE}[Nyamazon]${ChatColor.RESET} ${gachaName}ガチャをお届けしました。"

    @EventHandler
    fun onItemConsume(event: PlayerItemConsumeEvent) {
        val player = event.player

        UserEntity.findOrNew(player).foodConsumed += 1

        ScoreboardStats.update(player)
    }

    @EventHandler
    fun onBlockPlace(event: BlockPlaceEvent) {
        val player = event.player

        UserEntity.findOrNew(player).blockPlaced += 1

        ScoreboardStats.update(player)
    }

    @EventHandler
    fun onToolBroken(event: PlayerItemBreakEvent) {
        val player = event.player

        UserEntity.findOrNew(player).toolBroken += 1

//        player.inventory.addOrDropItem(GachaData.KinroKanshaGacha.getItem(1))
        (UserEntity.findOrNew(player).gachas.limit(1).firstOrNull() ?: return).KinroKansha += 1
        player.playSound(player.location, Sound.ENTITY_CAT_AMBIENT, 1f, 1f)
        player.sendRawMessage(nyamazon("勤労感謝"))
    }

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

        // これも GachaRecord に入れておくほうが良い
        val chooseTimes = when (gacha.customModelData) {
            5040 -> 4
            else -> 1
        }

        val dropCount = when (gacha.customModelData) {
            5040 -> 16
            else -> 1
        }

        repeat(if (player.isSneaking) usedItem.amount else 1) {
            repeat(chooseTimes) {
                val item = ItemUtil.create(
                    gacha.chooseMaterial(),
                    count = dropCount,
                    lore = lore(player.displayName)
                )
                player.inventory.addOrDropItem(item)
            }
            usedItem.amount -= 1
        }
        player.playSound(player.location, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f)
    }
}
