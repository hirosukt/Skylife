package love.chihuyu.skylife.data

import org.bukkit.Material

object ItemDataManager {

    val parents = mutableListOf<MutableList<Material>>()
    val data = mutableListOf(
        mutableListOf(Material.STONE, Material.DIRT, Material.COBBLESTONE),
        mutableListOf(Material.END_STONE, Material.END_STONE_BRICKS)
    )

    fun tradable(material: MutableList<Material>) = data.filter { it.any { item -> item in material } }

    fun getMaterialNameList(): List<String> {
        return Material.values().map { it.name }
    }
}