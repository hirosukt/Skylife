package love.chihuyu.skylife.database

import org.jetbrains.exposed.sql.Table

object GachaStorages : Table() {
    val id = integer("id").autoIncrement()

    // NOTE: 開発側から全員に配る可能性もあるので、全てのガチャ券がここにあったほうが良さそう
    val kinrokanshaGacha = integer("kinrokansha").default(0)
    val kosekiGacha = integer("koseki").default(0)
    val shokuryoGacha = integer("shokuryo").default(0)
    val kenzaiGacha = integer("kenzai").default(0)

    override val primaryKey = PrimaryKey(id)
}
