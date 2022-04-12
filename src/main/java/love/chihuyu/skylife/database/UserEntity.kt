// package love.chihuyu.skylife.database
//
// import org.bukkit.OfflinePlayer
// import org.jetbrains.exposed.dao.IntEntity
// import org.jetbrains.exposed.dao.IntEntityClass
// import org.jetbrains.exposed.dao.id.EntityID
// import org.jetbrains.exposed.sql.transactions.transaction
// import java.util.*
//
// class UserEntity(id: EntityID<Int>) : IntEntity(id) {
//    companion object : IntEntityClass<UserEntity>(User) {
//
//        inline fun findOrNew(uuid: UUID, crossinline init: UserEntity.() -> Unit = {}) = find(uuid) ?: new {
//            this.uuid = uuid
//            transaction {
//                init()
//            }
//        }
//
//        inline fun findOrNew(player: OfflinePlayer, crossinline init: UserEntity.() -> Unit = {}) = findOrNew(player.uniqueId, init)
//
//        fun find(uuid: UUID) = transaction { find { User.uuid eq uuid }.limit(1).firstOrNull() }
//    }
//
//    var uuid by User.uuid
//
//    val gachas by GachaEntity referrersOn User.uuid
//
//    var coin by User.coin
//
//    var foodConsumed by User.foodConsumed
//
//    var blockPlaced by User.blockPlaced
//
//    var toolBroken by User.toolBroken
// }