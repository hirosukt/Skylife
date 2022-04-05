package love.chihuyu.skylife.util

import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.PlayerInventory

fun PlayerInventory.addOrDropItem(vararg items: ItemStack) = this.addItem(*items).values.forEach((this.holder as Player)::dropItemThere)
fun Player.dropItemThere(vararg items: ItemStack) = items.forEach { this.world.dropItemNaturally(this.location, it) }
