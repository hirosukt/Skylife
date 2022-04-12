package love.chihuyu.skylife

// import love.chihuyu.skylife.scoreboard.ScoreboardStats
import love.chihuyu.skylife.data.ItemDataManager
import love.chihuyu.skylife.database.User
import love.chihuyu.skylife.gacha.GachaEvent
import love.chihuyu.skylife.gacha.GachaGiveCommand
import love.chihuyu.skylife.gacha.GachaShopCommand
import love.chihuyu.skylife.gacha.GachaShopEvent
import love.chihuyu.skylife.gui.GuiBarterCommand
import love.chihuyu.skylife.gui.GuiBarterEvent
import love.chihuyu.skylife.util.MEOW
import net.md_5.bungee.api.ChatColor
import org.bukkit.GameMode
import org.bukkit.Location
import org.bukkit.Sound
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerQuitEvent
import org.bukkit.plugin.java.JavaPlugin
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.insertIgnore
import org.jetbrains.exposed.sql.transactions.transaction

class Skylife : JavaPlugin(), Listener {

    companion object {
        lateinit var plugin: JavaPlugin
    }

    init {
        plugin = this
    }

    override fun onEnable() {
        val enabledCommands = setOf(GuiBarterCommand, GachaGiveCommand, GachaShopCommand)
        val enabledEvents = setOf(this, GuiBarterEvent, GachaEvent, GachaShopEvent)
        enabledCommands.forEach { it.register() }
        enabledEvents.forEach { server.pluginManager.registerEvents(it, this) }
        logger.info("This Plug has Enabled")

        ItemDataManager.checkDuplicate()
        logger.info("Debug Code has Ran")

        if (!plugin.dataFolder.exists()) {
            config.options().copyDefaults(true)
            saveResource("config.yml", false)
        }
        Database.connect("jdbc:sqlite:${plugin.dataFolder}/userstats.db", "org.sqlite.JDBC")
        transaction {
            SchemaUtils.create(User)
            SchemaUtils.createMissingTablesAndColumns(User)
        }
        logger.info("This Plugin has Loaded")
    }

    override fun onDisable() {
        logger.info("This Plugin has Disabled")
    }

    @EventHandler
    fun onJoin(event: PlayerJoinEvent) {
        val player = event.player
        var joinMessage = "${ChatColor.YELLOW}${player.name} joined the game"

        if (!player.hasPlayedBefore()) {
            joinMessage += "${ChatColor.LIGHT_PURPLE} (First Join)"
            player.gameMode = GameMode.SURVIVAL
            player.bedSpawnLocation = Location(player.world, 0.0, 64.0, 0.0)

            player.teleport(Location(player.world, 0.0, 64.0, 0.0))
            player.sendTitle("${ChatColor.GOLD}-= Welcome to Skylife =-", "", 10, 70, 20)
        }

        transaction {
            User.insertIgnore { it[uuid] = player.uniqueId }
        }

        event.joinMessage = joinMessage
        // ScoreboardStats.update(player)
        player.playSound(player.location, Sound.ENTITY_PLAYER_LEVELUP, 1f, 1f)
    }

    @EventHandler
    fun onQuit(event: PlayerQuitEvent) {
        event.player.world.playSound(event.player.location, MEOW, 1f, 1f)
    }
}
