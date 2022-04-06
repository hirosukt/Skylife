package love.chihuyu.skylife.data

import love.chihuyu.skylife.util.ItemUtil
import org.bukkit.Material
import org.bukkit.inventory.ItemStack

class GachaDataRecord(
    private val material: Material,
    private val name: String,
    private val lore: List<String>,
    private vararg val chanceMap: Pair<Material, Int>,
) {
    private val size = chanceMap.sumOf { it.second }

    fun getGachaItem(amount: Int = 1): ItemStack = ItemUtil.create(material, name, amount, true, lore, 5)

    fun chooseMaterial(): Material {
        val rand = (0 until size).random()
        var tmp = 0
        return chanceMap.find {
            tmp += it.second
            rand < tmp
        }!!.first
    }
}
