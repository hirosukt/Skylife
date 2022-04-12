package love.chihuyu.skylife.database

import org.jetbrains.exposed.sql.Table

object GachaStorage : Table() {

    private val uuid = reference("uuid", User.uuid)

    val KinroKansha = integer("KinroKansha").default(0)
    val KosekiGacha = integer("KosekiGacha").default(0)
    val SyokuryoGacha = integer("SyokuryoGacha").default(0)
    val KenzaiGacha = integer("KenzaiGacha").default(0)

    override val primaryKey = PrimaryKey(uuid)
}