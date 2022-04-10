package love.chihuyu.skylife.gui.constants
/* ktlint-disable no-multi-spaces dot-spacing */
/**
 * @property trading the area in which player can put items.
 * @property tradable the area in which tradable items are displayed.
 */
object Areas {
    val trading = (0..53).filter { it % 9 <= 1 }
    val separator = (0..53).filter { it % 9 == 2 }
    val tradable = (0..53).filter { it % 9 >= 3 }
}
