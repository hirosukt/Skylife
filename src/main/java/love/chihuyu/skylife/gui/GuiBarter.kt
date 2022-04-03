package love.chihuyu.skylife.gui

import love.chihuyu.skylife.util.ItemUtil
import net.md_5.bungee.api.ChatColor
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.entity.HumanEntity
import org.bukkit.entity.Player

object GuiBarter {

    private val fillPanel = ItemUtil.create(Material.LIGHT_GRAY_STAINED_GLASS_PANE, " ")
    private val separatorPanel = ItemUtil.create(Material.WHITE_STAINED_GLASS_PANE, " ")
    private val nextPagePanel = ItemUtil.create(Material.LIME_STAINED_GLASS_PANE, ChatColor.GREEN.toString() + "Next Page")
    private val previousPagePanel = ItemUtil.create(Material.RED_STAINED_GLASS_PANE, ChatColor.RED.toString() + "Previous Page")

    fun open(player: HumanEntity) {
        val inventory = Bukkit.createInventory(null, 54, "Barter")

        (0..53).forEach {
            if (it % 9 >= 3) inventory.setItem(it, fillPanel)
            if (it % 9 == 2) inventory.setItem(it, when (it) {
                38 -> nextPagePanel
                47 -> previousPagePanel
                else -> separatorPanel
            })
        }

        GuiBarterEvent.pageTemp[player as Player] = 0

        player.openInventory(inventory)
    }
}