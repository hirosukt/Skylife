package love.chihuyu.skylife.scoreboard

import love.chihuyu.skylife.Skylife.Companion.plugin
import love.chihuyu.skylife.data.GachaData
import love.chihuyu.skylife.database.User
import love.chihuyu.skylife.gacha.GachaEvent
import love.chihuyu.skylife.util.addOrDropItem
import org.bukkit.ChatColor
import org.bukkit.Sound
import org.bukkit.entity.Player
import org.bukkit.event.Listener
import org.bukkit.scoreboard.DisplaySlot
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.transaction

object ScoreboardStats : Listener {

    fun update(player: Player) {
        val manager = plugin.server.scoreboardManager ?: return
        val scoreboard = manager.newScoreboard
        val objective = scoreboard.getObjective("-+ Skylife Stats +-")
            ?: scoreboard.registerNewObjective(
                "skylife_stats_${player.uniqueId}",
                "",
                "${ChatColor.GOLD}=━┫${ChatColor.WHITE} Skylife Stats ${ChatColor.GOLD}┣━="
            )

        val blockGachaPoint = 64
        val foodGachaPoint = 32

        val nextFoodGacha =
            31 - (transaction {
                User.select { User.uuid eq player.uniqueId }.single()[User.foodConsumed]
            } % foodGachaPoint)
        val nextKenzaiGacha =
            63 - (transaction {
                User.select { User.uuid eq player.uniqueId }.single()[User.blockPlaced]
            } % blockGachaPoint)

        transaction {
            if (nextKenzaiGacha == 0) {
                player.inventory.addOrDropItem(GachaData.KenzaiGacha.getItem(1))
                player.playSound(player.location, Sound.ENTITY_CAT_AMBIENT, 1f, 1f)
                player.sendRawMessage(GachaEvent.nyamazon("建材"))
            }

            if (nextFoodGacha == 0) {
                player.inventory.addOrDropItem(GachaData.SyokuryoGacha.getItem(1))
                player.playSound(player.location, Sound.ENTITY_CAT_AMBIENT, 1f, 1f)
                player.sendRawMessage(GachaEvent.nyamazon("食料"))
            }
        }

        objective.getScore(" ").score = 0
        objective.getScore("You: ${player.name}").score = -1
        objective.getScore("  ").score = -2
        objective.getScore("Next 食材ガチャ: $nextFoodGacha").score = -3
        objective.getScore("Next 建材ガチャ: $nextKenzaiGacha").score = -4
        objective.getScore("   ").score = -5

        objective.displaySlot = DisplaySlot.SIDEBAR

        player.scoreboard = scoreboard
    }
}