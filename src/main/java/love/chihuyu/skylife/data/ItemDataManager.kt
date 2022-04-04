package love.chihuyu.skylife.data

import love.chihuyu.skylife.Skylife.Companion.plugin
import org.bukkit.ChatColor
import org.bukkit.Material

object ItemDataManager {

    private val data = mutableListOf(
        mutableListOf(Material.STONE, Material.DIRT, Material.COBBLESTONE),
        mutableListOf(Material.OAK_PLANKS, Material.ACACIA_PLANKS, Material.BIRCH_PLANKS, Material.DARK_OAK_PLANKS, Material.JUNGLE_PLANKS, Material.SPRUCE_PLANKS),
        mutableListOf(Material.GLASS, Material.TINTED_GLASS, Material.WHITE_STAINED_GLASS, Material.ORANGE_STAINED_GLASS, Material.MAGENTA_STAINED_GLASS, Material.LIGHT_BLUE_STAINED_GLASS, Material.YELLOW_STAINED_GLASS, Material.LIME_STAINED_GLASS, Material.PINK_STAINED_GLASS, Material.GRAY_STAINED_GLASS, Material.LIGHT_GRAY_STAINED_GLASS, Material.CYAN_STAINED_GLASS, Material.PURPLE_STAINED_GLASS, Material.BLUE_STAINED_GLASS, Material.BROWN_STAINED_GLASS, Material.GREEN_STAINED_GLASS, Material.RED_STAINED_GLASS, Material.BLACK_STAINED_GLASS),
        mutableListOf(Material.WHITE_WOOL, Material.ORANGE_WOOL, Material.MAGENTA_WOOL, Material.CYAN_WOOL, Material.YELLOW_WOOL, Material.LIME_WOOL, Material.PINK_WOOL, Material.GRAY_WOOL, Material.LIGHT_GRAY_WOOL, Material.LIGHT_BLUE_WOOL, Material.PURPLE_WOOL, Material.BLUE_WOOL, Material.BROWN_WOOL, Material.GREEN_WOOL, Material.RED_WOOL, Material.BLACK_WOOL),
        mutableListOf(Material.ACACIA_LEAVES, Material.AZALEA_LEAVES, Material.BIRCH_LEAVES, Material.DARK_OAK_LEAVES, Material.JUNGLE_LEAVES, Material.FLOWERING_AZALEA_LEAVES, Material.OAK_LEAVES, Material.SPRUCE_LEAVES),
        mutableListOf(Material.ACACIA_SAPLING, Material.BIRCH_SAPLING, Material.OAK_SAPLING, Material.DARK_OAK_SAPLING, Material.JUNGLE_SAPLING, Material.SPRUCE_SAPLING)
    )

    fun checkDuplicate() {
        val dupItems = mutableListOf<Material>()
        val flattenData = data.flatten()

        flattenData.forEach {
            if (flattenData.minus(it).contains(it)) {
                dupItems.add(it)
            }
        }
        if (dupItems.isNotEmpty()) {
            plugin.logger.info("${ChatColor.RED}these items are duplicated. \n$dupItems")
        }
    }

    fun tradable(material: Material) = data.find { it.contains(material) }
    fun tradable(material: MutableList<Material>) = data.filter { it.any { item -> item in material } }
}