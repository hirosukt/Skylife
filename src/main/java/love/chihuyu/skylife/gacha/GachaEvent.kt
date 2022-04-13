package love.chihuyu.skylife.gacha

import love.chihuyu.skylife.Skylife.Companion.plugin
import love.chihuyu.skylife.data.GachaData
import love.chihuyu.skylife.database.UserEntity
import love.chihuyu.skylife.util.ItemUtil
import love.chihuyu.skylife.util.MEOW
import love.chihuyu.skylife.util.addOrDropItem
import love.chihuyu.skylife.util.getCustomModelDataOrNull
import org.bukkit.ChatColor
import org.bukkit.Sound
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.block.BlockPlaceEvent
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.event.player.PlayerItemBreakEvent
import org.bukkit.event.player.PlayerItemConsumeEvent
import org.bukkit.inventory.ItemStack

object GachaEvent : Listener {

    private fun lore(userName: String) = listOf(
        "所有者: $userName"
    )

    private fun nyamazon(gachaName: String) =
        "${ChatColor.LIGHT_PURPLE}[Nyamazon]${ChatColor.RESET} ${gachaName}ガチャをお届けしました。"

    // HACK:
    @EventHandler
    fun onItemConsume(event: PlayerItemConsumeEvent) {
        val player = event.player

        UserEntity.findOrNew(player).gachas.single().SyokuryoGacha += 1

        if (UserEntity.findOrNew(player).foodConsumed % 32 != 0) return
//        player.inventory.addOrDropItem(GachaData.ShokuryoGacha.getItem(1))
        UserEntity.findOrNew(player).foodConsumed += 1
        player.playSound(player.location, MEOW, 1f, 1f)
        player.sendRawMessage(nyamazon("食料"))
    }

    @EventHandler
    fun onBlockPlace(event: BlockPlaceEvent) {
        val player = event.player

        UserEntity.findOrNew(player).blockPlaced += 1

        if (UserEntity.findOrNew(player).blockPlaced % 64 != 0) return
//        player.inventory.addOrDropItem(GachaData.KenzaiGacha.getItem(1))
        UserEntity.findOrNew(player).gachas.single().KenzaiGacha += 1
        plugin.logger.info(UserEntity.findOrNew(player).gachas.single().KenzaiGacha.toString())
        player.playSound(player.location, MEOW, 1f, 1f)
        player.sendRawMessage(nyamazon("建材"))
    }

    @EventHandler
    fun onToolBroken(event: PlayerItemBreakEvent) {
        val player = event.player

        UserEntity.findOrNew(player).toolBroken += 1

//        player.inventory.addOrDropItem(GachaData.KinroKanshaGacha.getItem(1))
        UserEntity.findOrNew(player).gachas.single().KinroKansha += 1
        player.playSound(player.location, Sound.ENTITY_CAT_AMBIENT, 1f, 1f)
        player.sendRawMessage(nyamazon("勤労感謝"))
    }

    @EventHandler
    fun onInteract(event: PlayerInteractEvent) {
        if (event.action !in listOf(Action.RIGHT_CLICK_AIR, Action.RIGHT_CLICK_BLOCK)) return
        if (event.clickedBlock?.type?.isInteractable == true) return
        if (!event.hasItem()) return
        val usedItem = event.item ?: return
        val gacha = GachaData.pairCustomModelData[usedItem.getCustomModelDataOrNull()] ?: return

        event.isCancelled = true

        val player = event.player
        // TODO: GachaData に入れる
        val chooseTimes = when (gacha.customModelData) {
            5040 -> 4
            else -> 1
        }
        val dropCount = when (gacha.customModelData) {
            5040 -> 16
            else -> 1
        }
        // OPTIMIZE: なんとなく無駄がある気がする
        val gachaTimes = if (player.isSneaking) usedItem.amount else 1
        val dropItems = mutableListOf<ItemStack>()
        repeat(gachaTimes) {
            repeat(chooseTimes) {
                val dropItem = ItemUtil.create(
                    gacha.chooseMaterial(),
                    count = dropCount,
                    lore = lore(player.displayName)
                )
                dropItems.add(dropItem)
            }
        }
        player.inventory.addOrDropItem(*dropItems.toTypedArray())
        usedItem.amount -= gachaTimes
        player.playSound(player.location, Sound.ENTITY_EXPERIENCE_ORB_PICKUP, 1f, 1f)
    }
}
