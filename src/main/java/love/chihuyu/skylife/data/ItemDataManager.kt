package love.chihuyu.skylife.data

import love.chihuyu.skylife.Skylife.Companion.plugin
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

object ItemDataManager {
    fun getTradableItems(vararg materials: Material) =
        ItemData.tradableList.filter { it.any { item -> item in materials } }.flatten()

    fun isTradable(item: ItemStack) = isTradable(item.type)
    fun isTradable(material: Material) = ItemData.tradableList.any { it.contains(material) }

    fun checkDuplicate() {
        val dupItems = mutableListOf<Material>()
        val flattenData = ItemData.tradableList.flatten()

        flattenData.forEach {
            if (flattenData.minus(it).contains(it)) {
                dupItems.add(it)
            }
        }

        if (dupItems.isNotEmpty()) {
            plugin.logger.info("${ChatColor.RED}these items are duplicated. \n$dupItems")
        }
    }
}
