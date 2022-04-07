package love.chihuyu.skylife.data

import love.chihuyu.skylife.base.CustomItem
import love.chihuyu.skylife.util.ItemUtil
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

data class GachaRecord(
    override val material: Material,
    override val name: String,
    val lore: List<String>,
    val customModelData: Int,
    val slot: Int?,
    val price: Pair<Material, Int>?,
    val chanceMap: Map<Material, Int>,
) : CustomItem {
    private val size = chanceMap.entries.sumOf { it.value }
    val shopData = if (price != null && slot != null) Shop(material, name, slot, customModelData + 5000, price) else null

    override fun getItem(amount: Int): ItemStack = ItemUtil.create(material, name, amount, true, lore, customModelData)

    fun chooseMaterial(): Material {
        val rand = (0 until size).random()
        var tmp = 0
        return chanceMap.entries.find {
            tmp += it.value
            rand < tmp
        }!!.key
    }
}
