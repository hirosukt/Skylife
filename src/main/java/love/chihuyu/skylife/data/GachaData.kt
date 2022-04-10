package love.chihuyu.skylife.data

import org.bukkit.ChatColor
import org.bukkit.Material

object GachaData {
    private fun enabled() = listOf(
        // Pair(GachaDataRecord, コマンドで受け取る文字列)
        KinroKanshaGacha to "KinroKanshaGacha",
        KosekiGacha to "KosekiGacha"
    )

    val KinroKanshaGacha = GachaRecord(
        Material.DRAGON_HEAD,
        "${ChatColor.AQUA}-=+ 勤労感謝ガチャ +=-",
        listOf(
            "いつも働いてくれてありがとう！",
            "",
            "右クリックでランダムなツールが出てくるよ！"
        ),
        5010,
        null,
        null,
        mapOf(
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
            Material.NETHERITE_AXE to 1,
        )
    )

    private val KosekiGacha = GachaRecord(
        Material.DRAGON_HEAD,
        "${ChatColor.BLUE}>=+ 鉱石ガチャ +=<",
        listOf(
            "お疲れ様！",
            "",
            "右クリックでランダムな鉱石が出てくるよ！"
        ),
        5020,
        13,
        Material.IRON_INGOT to 1,
        mapOf(
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
            Material.AMETHYST_SHARD to 5,
        )
    )

    val SyokuryoGacha = GachaRecord(
        Material.DRAGON_HEAD,
        "${ChatColor.GOLD}={+ 食料ガチャ +}=",
        listOf(
            "ご馳走の匂いがする...",
            "",
            "右クリックでランダムな食料が出てくるよ！"
        ),
        5030,
        null,
        null,
        mapOf(
            // ハズレ
            Material.PUFFERFISH to 2,
            Material.ROTTEN_FLESH to 2,
            Material.POISONOUS_POTATO to 2,
            // 生肉
            Material.PORKCHOP to 2,
            Material.MUTTON to 2,
            Material.RABBIT to 2,
            Material.BEEF to 2,
            Material.CHICKEN to 2,
            Material.RABBIT_STEW to 2,
            // 果物
            Material.APPLE to 6,
            Material.GOLDEN_APPLE to 2,
            Material.ENCHANTED_GOLDEN_APPLE to 1,
            Material.MELON_SLICE to 6,
            Material.SWEET_BERRIES to 6,
            Material.GLOW_BERRIES to 6,
            // 菓子
            Material.CAKE to 4,
            Material.COOKIE to 4,
            Material.PUMPKIN_PIE to 4,
            Material.HONEY_BOTTLE to 4,
            // 野菜
            Material.CARROT to 10,
            Material.POTATO to 10,
            Material.BEETROOT to 10,
            Material.BREAD to 8,
            Material.DRIED_KELP to 8,
            Material.MUSHROOM_STEW to 6,
            Material.BEETROOT_SOUP to 6,
            Material.GOLDEN_CARROT to 3,
            // 魚
            Material.COD to 7,
            Material.SALMON to 7,
            Material.COOKED_COD to 5,
            Material.COOKED_SALMON to 5,
        )
    )

    val KenzaiGacha = GachaRecord(
        Material.DRAGON_HEAD,
        "${ChatColor.GREEN}<+- 建材ガチャ -+>",
        listOf(
            "匠の業が詰まった一品",
            "",
            "右クリックでランダムな食料が出てくるよ！"
        ),
        5040,
        null,
        null,
        mapOf(

        )
    )

    val pairString = enabled().associate { it.second to it.first }
    val pairCustomModelData = enabled().associate { it.first.customModelData to it.first }
    val buyables = enabled()
        .filter { (gacha) -> gacha.shopData?.customModelData is Int }
        .associate { (gacha) -> gacha.shopData!!.customModelData to gacha }
}
