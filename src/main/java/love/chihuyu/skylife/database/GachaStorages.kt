package love.chihuyu.skylife.database

import org.jetbrains.exposed.sql.Table

object GachaStorages : Table() {
    val id = integer("id").autoIncrement()

    // NOTE: 開発側から全員に配る可能性もあるので、全てのガチャ券がここにあったほうが良さそう
    val kinroKanshaGacha = integer("KinroKansha").default(0)
    val kosekiGacha = integer("KosekiGacha").default(0)
    val syokuryoGacha = integer("SyokuryoGacha").default(0)
    val kenzaiGacha = integer("KenzaiGacha").default(0)

    override val primaryKey = PrimaryKey(id)
}
