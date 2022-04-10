package love.chihuyu.skylife.database

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime
import java.util.UUID

object User : Table() {
    val uuid = uuid("uuid").default(UUID.randomUUID())
    val coin = integer("coin").default(0)
    val foodConsumed = integer("foodConsumed").default(0)
    val blockPlaced = integer("blockPlaced").default(0)
    val firstLogin = datetime("firstLogin").default(LocalDateTime.now())
    val lastLogin = datetime("lastLogin").default(LocalDateTime.now())
}