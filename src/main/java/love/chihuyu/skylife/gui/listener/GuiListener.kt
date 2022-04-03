package love.chihuyu.skylife.gui.listener

import org.bukkit.event.inventory.InventoryEvent

typealias Match = (InventoryEvent) -> Boolean
typealias Run = (InventoryEvent) -> Unit

class GuiListener {
    val match: Match
    val run: Run

    constructor(match: Match, run: Run) {
        this.match = match
        this.run = run
    }
}