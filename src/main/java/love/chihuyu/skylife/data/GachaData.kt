package love.chihuyu.skylife.data

import love.chihuyu.skylife.util.Randomizer
import org.bukkit.ChatColor
import org.bukkit.Material
import kotlin.reflect.full.declaredMemberProperties

object GachaData {
    val kinrokanshaGacha = Gacha(
        Material.PAPER,
        "${ChatColor.AQUA}-=+ 勤労感謝ガチャ +=-",
        listOf(
            "いつも働いてくれてありがとう！",
            "",
            "右クリックでランダムなツールが出てくるよ！"
        ),
        5010,
        null,
        null,
        Randomizer(
            Material.WOODEN_SWORD to 3,
            Material.WOODEN_SHOVEL to 3,
            Material.WOODEN_PICKAXE to 3,
            Material.WOODEN_HOE to 3,
            Material.WOODEN_AXE to 3,

            Material.STONE_SWORD to 10,
            Material.STONE_SHOVEL to 10,
            Material.STONE_PICKAXE to 10,
            Material.STONE_HOE to 10,
            Material.STONE_AXE to 10,

            Material.IRON_SWORD to 15,
            Material.IRON_SHOVEL to 15,
            Material.IRON_PICKAXE to 15,
            Material.IRON_HOE to 15,
            Material.IRON_AXE to 15,

            Material.GOLDEN_SWORD to 10,
            Material.GOLDEN_SHOVEL to 10,
            Material.GOLDEN_PICKAXE to 10,
            Material.GOLDEN_HOE to 10,
            Material.GOLDEN_AXE to 10,

            Material.DIAMOND_SWORD to 5,
            Material.DIAMOND_SHOVEL to 5,
            Material.DIAMOND_PICKAXE to 5,
            Material.DIAMOND_HOE to 5,
            Material.DIAMOND_AXE to 5,

            Material.NETHERITE_SWORD to 1,
            Material.NETHERITE_SHOVEL to 1,
            Material.NETHERITE_PICKAXE to 1,
            Material.NETHERITE_HOE to 1,
            Material.NETHERITE_AXE to 1
        )
    )

    val kosekiGacha = Gacha(
        Material.PAPER,
        "${ChatColor.BLUE}>=+ 鉱石ガチャ +=<",
        listOf(
            "お疲れ様！",
            "",
            "右クリックでランダムな鉱石が出てくるよ！"
        ),
        5020,
        13,
        Material.IRON_INGOT to 1,
        Randomizer(
            // 外れ10％
            Material.COAL_ORE to 5,
            Material.COPPER_ORE to 5,
            // 鉄50％
            Material.IRON_ORE to 50,
            // その他40％
            Material.GOLD_ORE to 9,
            Material.LAPIS_ORE to 8,
            Material.REDSTONE_ORE to 7,
            Material.EMERALD_ORE to 7,
            Material.DIAMOND_ORE to 3,
            Material.ANCIENT_DEBRIS to 1,
            Material.AMETHYST_SHARD to 5
        )
    )

    val shokuryoGacha = Gacha(
        Material.PAPER,
        "${ChatColor.GOLD}={+ 食料ガチャ +}=",
        listOf(
            "ご馳走の匂いがする...",
            "",
            "右クリックでランダムな食料が出てくるよ！"
        ),
        5030,
        null,
        null,
        Randomizer(
            // ハズレ 9%
            Material.PUFFERFISH to 3,
            Material.ROTTEN_FLESH to 3,
            Material.POISONOUS_POTATO to 3,
            // 生肉 20%
            Material.PORKCHOP to 4,
            Material.MUTTON to 4,
            Material.RABBIT to 4,
            Material.BEEF to 3,
            Material.CHICKEN to 3,
            Material.RABBIT_STEW to 2,
            // 果物 16%
            Material.APPLE to 4,
            Material.MELON_SLICE to 4,
            Material.SWEET_BERRIES to 4,
            Material.GLOW_BERRIES to 4,
            // 菓子 10%
            Material.CAKE to 4,
            Material.COOKIE to 4,
            Material.PUMPKIN_PIE to 4,
            Material.HONEY_BOTTLE to 4,
            // 野菜 28%
            Material.CARROT to 4,
            Material.POTATO to 4,
            Material.BEETROOT to 4,
            Material.BREAD to 4,
            Material.DRIED_KELP to 4,
            Material.MUSHROOM_STEW to 2,
            Material.BEETROOT_SOUP to 2,
            // 魚 12%
            Material.COD to 4,
            Material.SALMON to 4,
            Material.COOKED_COD to 2,
            Material.COOKED_SALMON to 2,
            // 当たり 4%
            Material.GOLDEN_CARROT to 2,
            Material.GOLDEN_APPLE to 2,
            // 大当たり 1%
            Material.ENCHANTED_GOLDEN_APPLE to 1
        )
    )

    val kenzaiGacha = Gacha(
        Material.PAPER,
        "${ChatColor.GREEN}<+- 建材ガチャ -+>",
        listOf(
            "匠の業が詰まった一品",
            "",
            "右クリックでランダムな建材が出てくるよ！"
        ),
        5040,
        null,
        null,
        Randomizer(
            Material.STONE to 1,
            Material.GRANITE to 1,
            Material.POLISHED_GRANITE to 1,
            Material.DIORITE to 1,
            Material.POLISHED_DIORITE to 1,
            Material.ANDESITE to 1,
            Material.POLISHED_ANDESITE to 1,
            Material.DEEPSLATE to 1,
            Material.COBBLED_DEEPSLATE to 1,
            Material.POLISHED_DEEPSLATE to 1,
            Material.CALCITE to 1,
            Material.TUFF to 1,
            Material.DRIPSTONE_BLOCK to 1,
            Material.GRASS_BLOCK to 1,
            Material.DIRT to 1,
            Material.COARSE_DIRT to 1,
            Material.PODZOL to 1,
            Material.ROOTED_DIRT to 1,
            Material.CRIMSON_NYLIUM to 1,
            Material.WARPED_NYLIUM to 1,
            Material.COBBLESTONE to 1,
            Material.OAK_PLANKS to 1,
            Material.SPRUCE_PLANKS to 1,
            Material.BIRCH_PLANKS to 1,
            Material.JUNGLE_PLANKS to 1,
            Material.ACACIA_PLANKS to 1,
            Material.DARK_OAK_PLANKS to 1,
            Material.CRIMSON_PLANKS to 1,
            Material.WARPED_PLANKS to 1,
            // Material.BEDROCK to 1,
            Material.SAND to 1,
            Material.RED_SAND to 1,
            Material.GRAVEL to 1,
            // Material.COAL_ORE to 1,
            // Material.DEEPSLATE_COAL_ORE to 1,
            // Material.IRON_ORE to 1,
            // Material.DEEPSLATE_IRON_ORE to 1,
            // Material.COPPER_ORE to 1,
            // Material.DEEPSLATE_COPPER_ORE to 1,
            // Material.GOLD_ORE to 1,
            // Material.DEEPSLATE_GOLD_ORE to 1,
            // Material.REDSTONE_ORE to 1,
            // Material.DEEPSLATE_REDSTONE_ORE to 1,
            // Material.EMERALD_ORE to 1,
            // Material.DEEPSLATE_EMERALD_ORE to 1,
            // Material.LAPIS_ORE to 1,
            // Material.DEEPSLATE_LAPIS_ORE to 1,
            // Material.DIAMOND_ORE to 1,
            // Material.DEEPSLATE_DIAMOND_ORE to 1,
            // Material.NETHER_GOLD_ORE to 1,
            // Material.NETHER_QUARTZ_ORE to 1,
            // Material.ANCIENT_DEBRIS to 1,
            // Material.COAL_BLOCK to 1,
            // Material.RAW_IRON_BLOCK to 1,
            // Material.RAW_COPPER_BLOCK to 1,
            // Material.RAW_GOLD_BLOCK to 1,
            // Material.AMETHYST_BLOCK to 1,
            // Material.BUDDING_AMETHYST to 1,
            // Material.IRON_BLOCK to 1,
            // Material.COPPER_BLOCK to 1,
            // Material.GOLD_BLOCK to 1,
            // Material.DIAMOND_BLOCK to 1,
            // Material.NETHERITE_BLOCK to 1,
            // Material.EXPOSED_COPPER to 1,
            // Material.WEATHERED_COPPER to 1,
            // Material.OXIDIZED_COPPER to 1,
            // Material.CUT_COPPER to 1,
            // Material.EXPOSED_CUT_COPPER to 1,
            // Material.WEATHERED_CUT_COPPER to 1,
            // Material.OXIDIZED_CUT_COPPER to 1,
            // Material.CUT_COPPER_STAIRS to 1,
            // Material.EXPOSED_CUT_COPPER_STAIRS to 1,
            // Material.WEATHERED_CUT_COPPER_STAIRS to 1,
            // Material.OXIDIZED_CUT_COPPER_STAIRS to 1,
            // Material.CUT_COPPER_SLAB to 1,
            // Material.EXPOSED_CUT_COPPER_SLAB to 1,
            // Material.WEATHERED_CUT_COPPER_SLAB to 1,
            // Material.OXIDIZED_CUT_COPPER_SLAB to 1,
            // Material.WAXED_COPPER_BLOCK to 1,
            // Material.WAXED_EXPOSED_COPPER to 1,
            // Material.WAXED_WEATHERED_COPPER to 1,
            // Material.WAXED_OXIDIZED_COPPER to 1,
            // Material.WAXED_CUT_COPPER to 1,
            // Material.WAXED_EXPOSED_CUT_COPPER to 1,
            // Material.WAXED_WEATHERED_CUT_COPPER to 1,
            // Material.WAXED_OXIDIZED_CUT_COPPER to 1,
            // Material.WAXED_CUT_COPPER_STAIRS to 1,
            // Material.WAXED_EXPOSED_CUT_COPPER_STAIRS to 1,
            // Material.WAXED_WEATHERED_CUT_COPPER_STAIRS to 1,
            // Material.WAXED_OXIDIZED_CUT_COPPER_STAIRS to 1,
            // Material.WAXED_CUT_COPPER_SLAB to 1,
            // Material.WAXED_EXPOSED_CUT_COPPER_SLAB to 1,
            // Material.WAXED_WEATHERED_CUT_COPPER_SLAB to 1,
            // Material.WAXED_OXIDIZED_CUT_COPPER_SLAB to 1,
            Material.OAK_LOG to 1,
            Material.SPRUCE_LOG to 1,
            Material.BIRCH_LOG to 1,
            Material.JUNGLE_LOG to 1,
            Material.ACACIA_LOG to 1,
            Material.DARK_OAK_LOG to 1,
            Material.CRIMSON_STEM to 1,
            Material.WARPED_STEM to 1,
            // Material.STRIPPED_OAK_LOG to 1,
            // Material.STRIPPED_SPRUCE_LOG to 1,
            // Material.STRIPPED_BIRCH_LOG to 1,
            // Material.STRIPPED_JUNGLE_LOG to 1,
            // Material.STRIPPED_ACACIA_LOG to 1,
            // Material.STRIPPED_DARK_OAK_LOG to 1,
            // Material.STRIPPED_CRIMSON_STEM to 1,
            // Material.STRIPPED_WARPED_STEM to 1,
            // Material.STRIPPED_OAK_WOOD to 1,
            // Material.STRIPPED_SPRUCE_WOOD to 1,
            // Material.STRIPPED_BIRCH_WOOD to 1,
            // Material.STRIPPED_JUNGLE_WOOD to 1,
            // Material.STRIPPED_ACACIA_WOOD to 1,
            // Material.STRIPPED_DARK_OAK_WOOD to 1,
            // Material.STRIPPED_CRIMSON_HYPHAE to 1,
            // Material.STRIPPED_WARPED_HYPHAE to 1,
            Material.OAK_WOOD to 1,
            Material.SPRUCE_WOOD to 1,
            Material.BIRCH_WOOD to 1,
            Material.JUNGLE_WOOD to 1,
            Material.ACACIA_WOOD to 1,
            Material.DARK_OAK_WOOD to 1,
            Material.CRIMSON_HYPHAE to 1,
            Material.WARPED_HYPHAE to 1,
            Material.SPONGE to 1,
            Material.WET_SPONGE to 1,
            Material.GLASS to 1,
            Material.TINTED_GLASS to 1,
            Material.LAPIS_BLOCK to 1,
            Material.SANDSTONE to 1,
            Material.CHISELED_SANDSTONE to 1,
            Material.CUT_SANDSTONE to 1,
            Material.WHITE_WOOL to 1,
            Material.ORANGE_WOOL to 1,
            Material.MAGENTA_WOOL to 1,
            Material.LIGHT_BLUE_WOOL to 1,
            Material.YELLOW_WOOL to 1,
            Material.LIME_WOOL to 1,
            Material.PINK_WOOL to 1,
            Material.GRAY_WOOL to 1,
            Material.LIGHT_GRAY_WOOL to 1,
            Material.CYAN_WOOL to 1,
            Material.PURPLE_WOOL to 1,
            Material.BLUE_WOOL to 1,
            Material.BROWN_WOOL to 1,
            Material.GREEN_WOOL to 1,
            Material.RED_WOOL to 1,
            Material.BLACK_WOOL to 1,
            Material.OAK_SLAB to 1,
            Material.SPRUCE_SLAB to 1,
            Material.BIRCH_SLAB to 1,
            Material.JUNGLE_SLAB to 1,
            Material.ACACIA_SLAB to 1,
            Material.DARK_OAK_SLAB to 1,
            Material.CRIMSON_SLAB to 1,
            Material.WARPED_SLAB to 1,
            Material.STONE_SLAB to 1,
            Material.SMOOTH_STONE_SLAB to 1,
            Material.SANDSTONE_SLAB to 1,
            Material.CUT_SANDSTONE_SLAB to 1,
            Material.PETRIFIED_OAK_SLAB to 1,
            Material.COBBLESTONE_SLAB to 1,
            Material.BRICK_SLAB to 1,
            Material.STONE_BRICK_SLAB to 1,
            Material.NETHER_BRICK_SLAB to 1,
            Material.QUARTZ_SLAB to 1,
            Material.RED_SANDSTONE_SLAB to 1,
            Material.CUT_RED_SANDSTONE_SLAB to 1,
            Material.PURPUR_SLAB to 1,
            Material.PRISMARINE_SLAB to 1,
            Material.PRISMARINE_BRICK_SLAB to 1,
            Material.DARK_PRISMARINE_SLAB to 1,
            Material.SMOOTH_QUARTZ to 1,
            Material.SMOOTH_RED_SANDSTONE to 1,
            Material.SMOOTH_SANDSTONE to 1,
            Material.SMOOTH_STONE to 1,
            Material.BRICKS to 1,
            Material.BOOKSHELF to 1,
            Material.MOSSY_COBBLESTONE to 1,
            Material.OBSIDIAN to 1,
            Material.PURPUR_BLOCK to 1,
            Material.PURPUR_PILLAR to 1,
            Material.PURPUR_STAIRS to 1,
            Material.OAK_STAIRS to 1,
            Material.COBBLESTONE_STAIRS to 1,
            Material.ICE to 1,
            Material.SNOW_BLOCK to 1,
            Material.CLAY to 1,
            Material.PUMPKIN to 1,
            Material.CARVED_PUMPKIN to 1,
            Material.JACK_O_LANTERN to 1,
            Material.NETHERRACK to 1,
            Material.SOUL_SAND to 1,
            Material.SOUL_SOIL to 1,
            Material.BASALT to 1,
            Material.POLISHED_BASALT to 1,
            Material.SMOOTH_BASALT to 1,
            Material.GLOWSTONE to 1,
            Material.STONE_BRICKS to 1,
            Material.MOSSY_STONE_BRICKS to 1,
            Material.CRACKED_STONE_BRICKS to 1,
            Material.CHISELED_STONE_BRICKS to 1,
            Material.DEEPSLATE_BRICKS to 1,
            Material.CRACKED_DEEPSLATE_BRICKS to 1,
            Material.DEEPSLATE_TILES to 1,
            Material.CRACKED_DEEPSLATE_TILES to 1,
            Material.CHISELED_DEEPSLATE to 1,
            Material.MELON to 1,
            Material.BRICK_STAIRS to 1,
            Material.STONE_BRICK_STAIRS to 1,
            Material.MYCELIUM to 1,
            Material.NETHER_BRICKS to 1,
            Material.CRACKED_NETHER_BRICKS to 1,
            Material.CHISELED_NETHER_BRICKS to 1,
            Material.NETHER_BRICK_STAIRS to 1,
            Material.END_STONE to 1,
            Material.END_STONE_BRICKS to 1,
            Material.SANDSTONE_STAIRS to 1,
            // Material.EMERALD_BLOCK to 1,
            Material.SPRUCE_STAIRS to 1,
            Material.BIRCH_STAIRS to 1,
            Material.JUNGLE_STAIRS to 1,
            Material.CRIMSON_STAIRS to 1,
            Material.WARPED_STAIRS to 1,
            Material.CHISELED_QUARTZ_BLOCK to 1,
            Material.QUARTZ_BLOCK to 1,
            Material.QUARTZ_BRICKS to 1,
            Material.QUARTZ_PILLAR to 1,
            Material.QUARTZ_STAIRS to 1,
            Material.WHITE_TERRACOTTA to 1,
            Material.ORANGE_TERRACOTTA to 1,
            Material.MAGENTA_TERRACOTTA to 1,
            Material.LIGHT_BLUE_TERRACOTTA to 1,
            Material.YELLOW_TERRACOTTA to 1,
            Material.LIME_TERRACOTTA to 1,
            Material.PINK_TERRACOTTA to 1,
            Material.GRAY_TERRACOTTA to 1,
            Material.LIGHT_GRAY_TERRACOTTA to 1,
            Material.CYAN_TERRACOTTA to 1,
            Material.PURPLE_TERRACOTTA to 1,
            Material.BLUE_TERRACOTTA to 1,
            Material.BROWN_TERRACOTTA to 1,
            Material.GREEN_TERRACOTTA to 1,
            Material.RED_TERRACOTTA to 1,
            Material.BLACK_TERRACOTTA to 1,
            Material.HAY_BLOCK to 1,
            Material.TERRACOTTA to 1,
            Material.PACKED_ICE to 1,
            Material.ACACIA_STAIRS to 1,
            Material.DARK_OAK_STAIRS to 1,
            Material.WHITE_STAINED_GLASS to 1,
            Material.ORANGE_STAINED_GLASS to 1,
            Material.MAGENTA_STAINED_GLASS to 1,
            Material.LIGHT_BLUE_STAINED_GLASS to 1,
            Material.YELLOW_STAINED_GLASS to 1,
            Material.LIME_STAINED_GLASS to 1,
            Material.PINK_STAINED_GLASS to 1,
            Material.GRAY_STAINED_GLASS to 1,
            Material.LIGHT_GRAY_STAINED_GLASS to 1,
            Material.CYAN_STAINED_GLASS to 1,
            Material.PURPLE_STAINED_GLASS to 1,
            Material.BLUE_STAINED_GLASS to 1,
            Material.BROWN_STAINED_GLASS to 1,
            Material.GREEN_STAINED_GLASS to 1,
            Material.RED_STAINED_GLASS to 1,
            Material.BLACK_STAINED_GLASS to 1,
            Material.PRISMARINE to 1,
            Material.PRISMARINE_BRICKS to 1,
            Material.DARK_PRISMARINE to 1,
            Material.PRISMARINE_STAIRS to 1,
            Material.PRISMARINE_BRICK_STAIRS to 1,
            Material.DARK_PRISMARINE_STAIRS to 1,
            Material.SEA_LANTERN to 1,
            Material.RED_SANDSTONE to 1,
            Material.CHISELED_RED_SANDSTONE to 1,
            Material.CUT_RED_SANDSTONE to 1,
            Material.RED_SANDSTONE_STAIRS to 1,
            Material.MAGMA_BLOCK to 1,
            Material.NETHER_WART_BLOCK to 1,
            Material.WARPED_WART_BLOCK to 1,
            Material.RED_NETHER_BRICKS to 1,
            Material.BONE_BLOCK to 1,
            Material.WHITE_CONCRETE to 1,
            Material.ORANGE_CONCRETE to 1,
            Material.MAGENTA_CONCRETE to 1,
            Material.LIGHT_BLUE_CONCRETE to 1,
            Material.YELLOW_CONCRETE to 1,
            Material.LIME_CONCRETE to 1,
            Material.PINK_CONCRETE to 1,
            Material.GRAY_CONCRETE to 1,
            Material.LIGHT_GRAY_CONCRETE to 1,
            Material.CYAN_CONCRETE to 1,
            Material.PURPLE_CONCRETE to 1,
            Material.BLUE_CONCRETE to 1,
            Material.BROWN_CONCRETE to 1,
            Material.GREEN_CONCRETE to 1,
            Material.RED_CONCRETE to 1,
            Material.BLACK_CONCRETE to 1,
            Material.WHITE_CONCRETE_POWDER to 1,
            Material.ORANGE_CONCRETE_POWDER to 1,
            Material.MAGENTA_CONCRETE_POWDER to 1,
            Material.LIGHT_BLUE_CONCRETE_POWDER to 1,
            Material.YELLOW_CONCRETE_POWDER to 1,
            Material.LIME_CONCRETE_POWDER to 1,
            Material.PINK_CONCRETE_POWDER to 1,
            Material.GRAY_CONCRETE_POWDER to 1,
            Material.LIGHT_GRAY_CONCRETE_POWDER to 1,
            Material.CYAN_CONCRETE_POWDER to 1,
            Material.PURPLE_CONCRETE_POWDER to 1,
            Material.BLUE_CONCRETE_POWDER to 1,
            Material.BROWN_CONCRETE_POWDER to 1,
            Material.GREEN_CONCRETE_POWDER to 1,
            Material.RED_CONCRETE_POWDER to 1,
            Material.BLACK_CONCRETE_POWDER to 1,
            Material.DEAD_TUBE_CORAL_BLOCK to 1,
            Material.DEAD_BRAIN_CORAL_BLOCK to 1,
            Material.DEAD_BUBBLE_CORAL_BLOCK to 1,
            Material.DEAD_FIRE_CORAL_BLOCK to 1,
            Material.DEAD_HORN_CORAL_BLOCK to 1,
            Material.TUBE_CORAL_BLOCK to 1,
            Material.BRAIN_CORAL_BLOCK to 1,
            Material.BUBBLE_CORAL_BLOCK to 1,
            Material.FIRE_CORAL_BLOCK to 1,
            Material.HORN_CORAL_BLOCK to 1,
            Material.BLUE_ICE to 1,
            Material.POLISHED_GRANITE_STAIRS to 1,
            Material.SMOOTH_RED_SANDSTONE_STAIRS to 1,
            Material.MOSSY_STONE_BRICK_STAIRS to 1,
            Material.POLISHED_DIORITE_STAIRS to 1,
            Material.MOSSY_COBBLESTONE_STAIRS to 1,
            Material.END_STONE_BRICK_STAIRS to 1,
            Material.STONE_STAIRS to 1,
            Material.SMOOTH_SANDSTONE_STAIRS to 1,
            Material.SMOOTH_QUARTZ_STAIRS to 1,
            Material.GRANITE_STAIRS to 1,
            Material.ANDESITE_STAIRS to 1,
            Material.RED_NETHER_BRICK_STAIRS to 1,
            Material.POLISHED_ANDESITE_STAIRS to 1,
            Material.DIORITE_STAIRS to 1,
            Material.COBBLED_DEEPSLATE_STAIRS to 1,
            Material.POLISHED_DEEPSLATE_STAIRS to 1,
            Material.DEEPSLATE_BRICK_STAIRS to 1,
            Material.DEEPSLATE_TILE_STAIRS to 1,
            Material.POLISHED_GRANITE_SLAB to 1,
            Material.SMOOTH_RED_SANDSTONE_SLAB to 1,
            Material.MOSSY_STONE_BRICK_SLAB to 1,
            Material.POLISHED_DIORITE_SLAB to 1,
            Material.MOSSY_COBBLESTONE_SLAB to 1,
            Material.END_STONE_BRICK_SLAB to 1,
            Material.SMOOTH_SANDSTONE_SLAB to 1,
            Material.SMOOTH_QUARTZ_SLAB to 1,
            Material.GRANITE_SLAB to 1,
            Material.ANDESITE_SLAB to 1,
            Material.RED_NETHER_BRICK_SLAB to 1,
            Material.POLISHED_ANDESITE_SLAB to 1,
            Material.DIORITE_SLAB to 1,
            Material.COBBLED_DEEPSLATE_SLAB to 1,
            Material.POLISHED_DEEPSLATE_SLAB to 1,
            Material.DEEPSLATE_BRICK_SLAB to 1,
            Material.DEEPSLATE_TILE_SLAB to 1,
            Material.DRIED_KELP_BLOCK to 1,
            Material.CRYING_OBSIDIAN to 1,
            Material.BLACKSTONE to 1,
            Material.BLACKSTONE_SLAB to 1,
            Material.BLACKSTONE_STAIRS to 1,
            Material.GILDED_BLACKSTONE to 1,
            Material.POLISHED_BLACKSTONE to 1,
            Material.POLISHED_BLACKSTONE_SLAB to 1,
            Material.POLISHED_BLACKSTONE_STAIRS to 1,
            Material.CHISELED_POLISHED_BLACKSTONE to 1,
            Material.POLISHED_BLACKSTONE_BRICKS to 1,
            Material.POLISHED_BLACKSTONE_BRICK_SLAB to 1,
            Material.POLISHED_BLACKSTONE_BRICK_STAIRS to 1,
            Material.CRACKED_POLISHED_BLACKSTONE_BRICKS to 1
        )
    )

    val data: Map<String, Gacha> = GachaData::class.declaredMemberProperties
        .map { it.get(this) }
        .filterIsInstance<Gacha>()
        .associateBy { it.name }

    val buyables = data.values
        .filter { it.shopData?.customModelData is Int }
        .associateBy { it.customModelData }
}
