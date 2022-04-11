package love.chihuyu.skylife.database

import org.jetbrains.exposed.dao.id.IntIdTable

object UsersTable : IntIdTable("users") {

    val uuid = uuid("uuid").uniqueIndex()

    val coin = integer("coin").default(0)

    val foodConsumed = integer("foodConsumed").default(0)

    val blockPlaced = integer("blockPlaced").default(0)

    val toolBroken = integer("toolBroken").default(0)
}