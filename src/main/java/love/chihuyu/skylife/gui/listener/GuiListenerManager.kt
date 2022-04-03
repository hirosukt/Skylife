package love.chihuyu.skylife.gui.listener

import org.bukkit.event.Listener
import org.bukkit.event.EventHandler
import org.bukkit.event.inventory.InventoryEvent

object GuiListenerManager : Listener {

//    fun addListener(GuiListener)

    val matchViewTitle = { title: String -> { event: InventoryEvent -> event.view.title == title } }
    var listeners = mutableListOf<GuiListener>()

    @EventHandler
    fun onClick(event: InventoryEvent) {
        listeners.filter { it.match(event) }.forEach { it.run(event) }
    }
}