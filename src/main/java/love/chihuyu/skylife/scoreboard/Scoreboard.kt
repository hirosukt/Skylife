package love.chihuyu.skylife.scoreboard

import love.chihuyu.skylife.database.Stats
import love.chihuyu.skylife.database.Users
import love.chihuyu.skylife.util.background
import org.bukkit.Bukkit.getScoreboardManager
import org.bukkit.entity.Player
import org.bukkit.scoreboard.DisplaySlot
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction

object Scoreboard {
    fun update(player: Player) {
        val name = "skylife_stats_${player.uniqueId}"
        val displayName = "§l§bSKYLIFE§7 | §astats  "
        val manager = getScoreboardManager() ?: return
        val scoreboard = manager.newScoreboard
        val objective = scoreboard.getObjective(name)
            ?: scoreboard.registerNewObjective(name, "", displayName)

        val perSyokuryoGacha = 32
        val perKenzaiGacha = 64
        background {
            newSuspendedTransaction {
                val foodConsumed = (Users innerJoin Stats).slice(Stats.foodConsumed)
                    .select { Users.uuid eq player.uniqueId }
                    .map { it[Stats.foodConsumed] }.first()

                val blockPlaced = (Users innerJoin Stats).slice(Stats.blockPlaced)
                    .select { Users.uuid eq player.uniqueId }
                    .map { it[Stats.blockPlaced] }.first()
                val nextSyokuryoGacha = perSyokuryoGacha - (foodConsumed % perSyokuryoGacha)
                val nextKenzaiGacha = perKenzaiGacha - (blockPlaced % perKenzaiGacha)
                objective.apply {
                    getScore("§6§l╋━━━━━━━━━━━━━━━━━╋§r").score = 8
                    getScore("       ").score = 7
                    getScore("      ").score = 6
                    getScore("     ").score = 5
                    getScore("    ").score = 4
                    getScore("食材ガチャまで $nextSyokuryoGacha").score = 3
                    getScore("建材ガチャまで $nextKenzaiGacha").score = 2
                    getScore(" ").score = 1
                    getScore("§6§l╋━━━━━━━━━━━━━━━━━╋").score = 0
                    displaySlot = DisplaySlot.SIDEBAR
                }
            }
            player.scoreboard = scoreboard
        }
    }
}
