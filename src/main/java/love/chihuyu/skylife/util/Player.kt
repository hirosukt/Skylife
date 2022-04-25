package love.chihuyu.skylife.util

import love.chihuyu.skylife.Skylife.Companion.plugin
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

fun Player.dropItemThere(vararg items: ItemStack) =
    items.forEach { if (it.amount != 0) this.world.dropItemNaturally(this.location, it) }

fun String.resolvePlayer() = plugin.server.getPlayer(this)
