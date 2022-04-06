package love.chihuyu.skylife.data

import org.bukkit.ChatColor
import org.bukkit.Material

object GachaData {
    private fun enabled() = listOf(
     // Pair(GachaDataRecord, コマンドで受け取る文字列)
        Pair(this.KinroKanshaGacha, "KinroKanshaGacha")
    )

    val KinroKanshaGacha = GachaDataRecord(
        Material.DRAGON_HEAD,
        "${ChatColor.AQUA}-=+ 勤労感謝ガチャ +=-",
        listOf(
            "いつも働いてくれてありがとう！",
            "",
            "右クリックでランダムなツールが出てくるよ！"
        ),
        5010,

        Pair(Material.WOODEN_SWORD, 3),
        Pair(Material.WOODEN_SHOVEL, 3),
        Pair(Material.WOODEN_PICKAXE, 3),
        Pair(Material.WOODEN_HOE, 3),
        Pair(Material.WOODEN_AXE, 3),

        Pair(Material.STONE_SWORD, 10),
        Pair(Material.STONE_SHOVEL, 10),
        Pair(Material.STONE_PICKAXE, 10),
        Pair(Material.STONE_HOE, 10),
        Pair(Material.STONE_AXE, 10),

        Pair(Material.IRON_SWORD, 15),
        Pair(Material.IRON_SHOVEL, 15),
        Pair(Material.IRON_PICKAXE, 15),
        Pair(Material.IRON_HOE, 15),
        Pair(Material.IRON_AXE, 15),

        Pair(Material.GOLDEN_SWORD, 10),
        Pair(Material.GOLDEN_SHOVEL, 10),
        Pair(Material.GOLDEN_PICKAXE, 10),
        Pair(Material.GOLDEN_HOE, 10),
        Pair(Material.GOLDEN_AXE, 10),

        Pair(Material.DIAMOND_SWORD, 5),
        Pair(Material.DIAMOND_SHOVEL, 5),
        Pair(Material.DIAMOND_PICKAXE, 5),
        Pair(Material.DIAMOND_HOE, 5),
        Pair(Material.DIAMOND_AXE, 5),

        Pair(Material.NETHERITE_SWORD, 1),
        Pair(Material.NETHERITE_SHOVEL, 1),
        Pair(Material.NETHERITE_PICKAXE, 1),
        Pair(Material.NETHERITE_HOE, 1),
        Pair(Material.NETHERITE_AXE, 1),
    )

    val pairString = enabled().associate { Pair(it.second, it.first) }
    val pairCustomModelData = enabled().associate { Pair(it.first.customModelData, it.first) }
}