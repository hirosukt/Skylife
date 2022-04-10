package love.chihuyu.skylife.data

import love.chihuyu.skylife.base.CustomHeadItem
import love.chihuyu.skylife.util.ItemUtil
import org.bukkit.Material

data class Shop(
    override val material: Material,
    override val name: String,
    val slot: Int,
    val customModelData: Int,
    val price: Pair<Material, Int>,
    val additionalLore: List<String> = listOf()
) : CustomHeadItem {

    private val lore = listOf(
        "${price.first} x${price.second} で交換できます。",
        * if (additionalLore.isNotEmpty()) arrayOf("") + additionalLore.toTypedArray() else arrayOf()
    )

    override fun getItem(amount: Int) = ItemUtil.create(material, name, amount, true, lore, customModelData)
}
