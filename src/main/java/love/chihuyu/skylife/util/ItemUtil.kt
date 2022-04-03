package love.chihuyu.skylife.util

import org.bukkit.Material
import org.bukkit.inventory.ItemStack

object ItemUtil {

    fun create(material: Material, name: String? = null, count: Int = 1, unbreakable: Boolean = false): ItemStack {
        val item = ItemStack(material)
        val meta = item.itemMeta

        meta?.isUnbreakable = unbreakable
        meta?.setDisplayName(name)
        item.amount = count
        meta?.setCustomModelData(1)
        item.itemMeta = meta

        return item
    }
}