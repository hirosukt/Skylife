package love.chihuyu.skylife.data

import love.chihuyu.skylife.base.CustomItem
import love.chihuyu.skylife.util.ItemUtil
import love.chihuyu.skylife.util.setFlags
import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import kotlin.random.Random.Default.nextInt

data class GachaRecord(
    override val material: Material,
    override val name: String,
    val lore: List<String>,
    val customModelData: Int,
    val slot: Int?,
    val price: Pair<Material, Int>?,
    val chanceMap: Map<Material, Int>
) : CustomItem {
    val shopData = if (price == null || slot == null) null
    else Shop(material, name, slot, customModelData + 5000, price)

    private val size = chanceMap.entries.sumOf { it.value }

    override fun getItem(amount: Int): ItemStack {
        val item = ItemUtil.create(material, name, amount, true, lore, customModelData)
        item.addUnsafeEnchantment(Enchantment.MENDING, 0)
        item.setFlags(ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_UNBREAKABLE)
        return item
    }

    fun chooseMaterial(): Material {
        var tmp = 0
        val rnd = nextInt(size)
        return chanceMap.entries.first { tmp += it.value; rnd < tmp }.key
    }
}
