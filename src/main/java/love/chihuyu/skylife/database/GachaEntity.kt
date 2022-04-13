 package love.chihuyu.skylife.database

 import org.jetbrains.exposed.dao.IntEntity
 import org.jetbrains.exposed.dao.IntEntityClass
 import org.jetbrains.exposed.dao.id.EntityID

 class GachaEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<GachaEntity>(GachaTable)

    val user by UserEntity referrersOn User.id

    var kinrokansha by GachaTable.KinroKansha
    var koseki by GachaTable.KosekiGacha
    var syokuryo by GachaTable.SyokuryoGacha
    var kenzai by GachaTable.KenzaiGacha
 }