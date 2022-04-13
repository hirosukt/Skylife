package love.chihuyu.skylife.database

import org.jetbrains.exposed.sql.Table

object User: Table() {

    val uuid = uuid("uuid")
    val coin = integer("coin").default(0)
    val foodConsumed = integer("foodConsumed").default(0)
    val blockPlaced = integer("blockPlaced").default(0)
    val toolBroken = integer("toolBroken").default(0)
    val gacha = reference("gacha", GachaTable.uuid)

    override val primaryKey = PrimaryKey(uuid)
}
