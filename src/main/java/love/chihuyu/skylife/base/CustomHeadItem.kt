package love.chihuyu.skylife.base

import org.bukkit.Material
import org.bukkit.inventory.ItemStack

interface CustomHeadItem {
    val material: Material
    val name: String
    fun getItem(amount: Int): ItemStack
}
