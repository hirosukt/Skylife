package love.chihuyu.skylife.database

import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID

class GachaEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<GachaEntity>(GachasTable)

    val user by UserEntity referrersOn UsersTable.uuid

    var KinroKansha by GachasTable.KinroKansha

    var KosekiGacha by GachasTable.KosekiGacha

    var SyokuryoGacha by GachasTable.SyokuryoGacha

    var KenzaiGacha by GachasTable.KenzaiGacha
}