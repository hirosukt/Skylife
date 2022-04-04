package love.chihuyu.skylife.data

import love.chihuyu.skylife.Skylife.Companion.plugin
import org.bukkit.ChatColor
import org.bukkit.Material

object ItemDataManager {

    private val tradableList = mutableListOf(
        /*石グループ*/mutableListOf(Material.COBBLESTONE, Material.STONE, Material.GRANITE, Material.ANDESITE, Material.DIORITE, Material.COBBLED_DEEPSLATE, Material.DEEPSLATE, Material.CALCITE, Material.TUFF, Material.DRIPSTONE_BLOCK),
        /*土グループ*/mutableListOf(Material.GRASS_BLOCK, Material.DIRT, Material.COARSE_DIRT, Material.ROOTED_DIRT, Material.PODZOL, Material.MYCELIUM, Material.NETHERRACK, Material.CRIMSON_NYLIUM, Material.WARPED_NYLIUM, Material.SOUL_SAND, Material.SOUL_SOIL, Material.SAND, Material.RED_SAND, Material.GRAVEL, Material.CLAY),
        /*板グループ*/mutableListOf(Material.OAK_PLANKS, Material.ACACIA_PLANKS, Material.BIRCH_PLANKS, Material.DARK_OAK_PLANKS, Material.JUNGLE_PLANKS, Material.SPRUCE_PLANKS, Material.CRIMSON_PLANKS, Material.WARPED_PLANKS),
        /*苗グループ*/mutableListOf(Material.OAK_SAPLING, Material.SPRUCE_SAPLING, Material.BIRCH_SAPLING, Material.JUNGLE_SAPLING, Material.ACACIA_SAPLING, Material.DARK_OAK_SAPLING),
        /*Ore <-> Deepslate Ore*/
        mutableListOf(Material.COAL_ORE, Material.DEEPSLATE_COAL_ORE),
        mutableListOf(Material.IRON_ORE, Material.DEEPSLATE_IRON_ORE),
        mutableListOf(Material.COPPER_ORE, Material.DEEPSLATE_COPPER_ORE),
        mutableListOf(Material.GOLD_ORE, Material.DEEPSLATE_GOLD_ORE),
        mutableListOf(Material.REDSTONE_ORE, Material.DEEPSLATE_REDSTONE_ORE),
        mutableListOf(Material.EMERALD_ORE, Material.DEEPSLATE_EMERALD_ORE),
        mutableListOf(Material.LAPIS_ORE, Material.DEEPSLATE_LAPIS_ORE),
        mutableListOf(Material.DIAMOND_ORE, Material.DEEPSLATE_DIAMOND_ORE),
        /*銅ブロックグループ*/
        mutableListOf(Material.COPPER_BLOCK, Material.EXPOSED_COPPER, Material.WEATHERED_COPPER, Material.OXIDIZED_COPPER),
        mutableListOf(Material.CUT_COPPER, Material.EXPOSED_CUT_COPPER, Material.WEATHERED_CUT_COPPER, Material.OXIDIZED_CUT_COPPER),
        mutableListOf(Material.CUT_COPPER_STAIRS, Material.EXPOSED_CUT_COPPER_STAIRS, Material.WEATHERED_CUT_COPPER_STAIRS, Material.OXIDIZED_CUT_COPPER_STAIRS),
        mutableListOf(Material.CUT_COPPER_SLAB, Material.EXPOSED_CUT_COPPER_SLAB, Material.WEATHERED_CUT_COPPER_SLAB, Material.OXIDIZED_CUT_COPPER_SLAB),
        mutableListOf(Material.WAXED_COPPER_BLOCK, Material.WAXED_EXPOSED_COPPER, Material.WAXED_WEATHERED_COPPER, Material.WAXED_OXIDIZED_COPPER),
        mutableListOf(Material.WAXED_CUT_COPPER, Material.WAXED_EXPOSED_CUT_COPPER, Material.WAXED_WEATHERED_CUT_COPPER, Material.WAXED_OXIDIZED_CUT_COPPER),
        mutableListOf(Material.WAXED_CUT_COPPER_STAIRS, Material.WAXED_EXPOSED_CUT_COPPER_STAIRS, Material.WAXED_WEATHERED_CUT_COPPER_STAIRS, Material.WAXED_OXIDIZED_CUT_COPPER_STAIRS),
        mutableListOf(Material.WAXED_CUT_COPPER_SLAB, Material.WAXED_EXPOSED_CUT_COPPER_SLAB, Material.WAXED_WEATHERED_CUT_COPPER_SLAB, Material.WAXED_OXIDIZED_CUT_COPPER_SLAB),
        /*木グループ*/mutableListOf(Material.OAK_LOG, Material.SPRUCE_LOG, Material.BIRCH_LOG, Material.JUNGLE_LOG, Material.ACACIA_LOG, Material.DARK_OAK_LOG, Material.CRIMSON_STEM, Material.WARPED_STEM),
        /*葉グループ*/mutableListOf(Material.OAK_LEAVES, Material.SPRUCE_LEAVES, Material.BIRCH_LEAVES, Material.JUNGLE_LEAVES, Material.ACACIA_LEAVES, Material.DARK_OAK_LEAVES, Material.AZALEA_LEAVES, Material.FLOWERING_AZALEA_LEAVES),
        /*窓グループ*/mutableListOf(Material.GLASS, Material.TINTED_GLASS, Material.WHITE_STAINED_GLASS, Material.ORANGE_STAINED_GLASS, Material.MAGENTA_STAINED_GLASS, Material.LIGHT_BLUE_STAINED_GLASS, Material.YELLOW_STAINED_GLASS, Material.LIME_STAINED_GLASS, Material.PINK_STAINED_GLASS, Material.GRAY_STAINED_GLASS, Material.LIGHT_GRAY_STAINED_GLASS, Material.CYAN_STAINED_GLASS, Material.PURPLE_STAINED_GLASS, Material.BLUE_STAINED_GLASS, Material.BROWN_STAINED_GLASS, Material.GREEN_STAINED_GLASS, Material.RED_STAINED_GLASS, Material.BLACK_STAINED_GLASS),
        /*羊グループ*/mutableListOf(Material.WHITE_WOOL, Material.ORANGE_WOOL, Material.MAGENTA_WOOL, Material.CYAN_WOOL, Material.YELLOW_WOOL, Material.LIME_WOOL, Material.PINK_WOOL, Material.GRAY_WOOL, Material.LIGHT_GRAY_WOOL, Material.LIGHT_BLUE_WOOL, Material.PURPLE_WOOL, Material.BLUE_WOOL, Material.BROWN_WOOL, Material.GREEN_WOOL, Material.RED_WOOL, Material.BLACK_WOOL),
        /*職業ブロック*/mutableListOf(Material.LOOM, Material.COMPOSTER, Material.BARREL, Material.SMOKER, Material.BLAST_FURNACE, Material.CARTOGRAPHY_TABLE, Material.FLETCHING_TABLE, Material.GRINDSTONE, Material.SMITHING_TABLE, Material.STONECUTTER),
        /*氷グループ*/mutableListOf(Material.ICE, Material.PACKED_ICE, Material.BLUE_ICE, Material.SNOW_BLOCK),
        /*炭グループ*/mutableListOf(Material.COAL, Material.CHARCOAL),
        /*鉄用グループ*/mutableListOf(Material.COAL_BLOCK, Material.IRON_NUGGET),
        /*草グループ*/mutableListOf(Material.GLASS, Material.FERN, Material.AZALEA, Material.FLOWERING_AZALEA, Material.DEAD_BUSH, Material.SEAGRASS, Material.CRIMSON_ROOTS, Material.WARPED_ROOTS, Material.NETHER_SPROUTS, Material.WEEPING_VINES, Material.TWISTING_VINES, Material.HANGING_ROOTS, Material.VINE, Material.GLOW_LICHEN, Material.TALL_GRASS, Material.LARGE_FERN),
        /*染料グループ */mutableListOf(Material.WHITE_DYE, Material.ORANGE_DYE, Material.MAGENTA_DYE, Material.LIGHT_BLUE_DYE, Material.YELLOW_DYE, Material.LIME_DYE, Material.PINK_DYE, Material.GRAY_DYE, Material.LIGHT_GRAY_DYE, Material.CYAN_DYE, Material.PURPLE_DYE, Material.BLUE_DYE, Material.BROWN_DYE, Material.GREEN_DYE, Material.RED_DYE, Material.BLACK_DYE),
        /*種グループ*/mutableListOf(Material.WHEAT_SEEDS, Material.PUMPKIN_SEEDS, Material.MELON_SEEDS, Material.BEETROOT_SEEDS),
    )

    fun checkDuplicate() {
        val dupItems = mutableListOf<Material>()
        val flattenData = tradableList.flatten()

        flattenData.forEach {
            if (flattenData.minus(it).contains(it)) {
                dupItems.add(it)
            }
        }
        if (dupItems.isNotEmpty()) {
            plugin.logger.info("${ChatColor.RED}these items are duplicated. \n$dupItems")
        }
    }

    fun getTradableItems(material: Material) = tradableList.find { it.contains(material) }
    fun getTradableItems(material: MutableList<Material>) = tradableList.filter { it.any { item -> item in material } }.flatten()
}
