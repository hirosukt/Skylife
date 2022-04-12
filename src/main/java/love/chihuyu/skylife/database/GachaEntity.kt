// package love.chihuyu.skylife.database
//
// import org.jetbrains.exposed.dao.IntEntity
// import org.jetbrains.exposed.dao.IntEntityClass
// import org.jetbrains.exposed.dao.id.EntityID
//
// class GachaEntity(id: EntityID<Int>) : IntEntity(id) {
//    companion object : IntEntityClass<GachaEntity>(GachaStorage)
//
//    val user by UserEntity referrersOn UsersTable.uuid
//
//    var KinroKansha by GachaStorage.KinroKansha
//
//    var KosekiGacha by GachaStorage.KosekiGacha
//
//    var SyokuryoGacha by GachaStorage.SyokuryoGacha
//
//    var KenzaiGacha by GachaStorage.KenzaiGacha
// }