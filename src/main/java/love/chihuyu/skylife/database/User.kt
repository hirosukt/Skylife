package love.chihuyu.skylife.database

import org.jetbrains.exposed.sql.Table
import java.util.*

object User : Table() {
    val uuid = uuid("uuid").default(UUID.randomUUID())
    val coin = integer("coin").default(0)
    val foodConsumed = integer("foodConsumed").default(0)
    val blockPlaced = integer("blockPlaced").default(0)
}
