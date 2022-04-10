package love.chihuyu.skylife

import love.chihuyu.skylife.data.ItemDataManager
import love.chihuyu.skylife.database.User
import love.chihuyu.skylife.gacha.GachaCommand
import love.chihuyu.skylife.gacha.GachaEvent
import love.chihuyu.skylife.gacha.GachaShopCommand
import love.chihuyu.skylife.gacha.GachaShopEvent
import love.chihuyu.skylife.gui.GuiBarterCommand
import love.chihuyu.skylife.gui.GuiBarterEvent
import love.chihuyu.skylife.scoreboard.ScoreboardStats
import net.md_5.bungee.api.ChatColor
import org.bukkit.GameMode
import org.bukkit.Location
import org.bukkit.Sound
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent
import org.bukkit.plugin.java.JavaPlugin
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import java.time.LocalDateTime

class Skylife : JavaPlugin(), Listener {

    companion object {
        lateinit var plugin: JavaPlugin
    }

    init {
        plugin = this
    }

    override fun onEnable() {
        // Plugin startup logic
        logger.info("plugin has loaded.")

        // config fix
        config.options().copyDefaults(true)
        saveResource("config.yml", false)

        listOf(GuiBarterCommand, GachaCommand, GachaShopCommand).forEach { it.register() }

        ItemDataManager.checkDuplicate()

        Database.connect("jdbc:sqlite:${plugin.dataFolder}/userstats.db", driver = "org.sqlite.JDBC")
        transaction {
            SchemaUtils.create(User)
            SchemaUtils.createMissingTablesAndColumns(User)
        }

        listOf(this, GuiBarterEvent, GachaEvent, GachaShopEvent).forEach { server.pluginManager.registerEvents(it, this) }
    }

    override fun onDisable() {
        // Plugin shutdown logic
        logger.info("plugin has unloaded.")
    }

    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {
        val player = event.player
        player.playSound(player.location, Sound.ENTITY_PLAYER_LEVELUP, 1f, 1f)

        event.joinMessage = ChatColor.YELLOW.toString() + "${player.name} joined the game."

        ScoreboardStats.update(player)

        transaction {
            val user = User.select { User.uuid eq player.uniqueId }

            if (user.count() <= 0) {
                User.insert {
                    it[uuid] = player.uniqueId
                    it[coin] = 0
                    it[firstLogin] = LocalDateTime.now()
                    it[lastLogin] = LocalDateTime.now()
                }

                event.joinMessage += ChatColor.LIGHT_PURPLE.toString() + " (First join)"
                player.gameMode = GameMode.SURVIVAL
                player.bedSpawnLocation = Location(player.world, 0.0, 64.0, 0.0)
                player.teleport(Location(player.world, 0.0, 64.0, 0.0))
                player.sendTitle(ChatColor.GOLD.toString() + "-= Welcome to Skylife =-", "", 10, 70, 20)
            }
        }
    }

    @EventHandler
    fun onQuit(event: PlayerQuitEvent) {
        val player = event.player

        event.quitMessage = ChatColor.YELLOW.toString() + "${player.name} left the game."

        transaction {
            User.update({ User.uuid eq player.uniqueId }) { it[lastLogin] = LocalDateTime.now() }
        }
    }
}
