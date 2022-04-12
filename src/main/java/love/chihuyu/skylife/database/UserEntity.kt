package love.chihuyu.skylife.database

import org.bukkit.OfflinePlayer
import org.jetbrains.exposed.dao.UUIDEntity
import org.jetbrains.exposed.dao.UUIDEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

class UserEntity(id: EntityID<UUID>) : UUIDEntity(id) {
    companion object : UUIDEntityClass<UserEntity>(User) {

        inline fun findOrNew(uuid: UUID, crossinline init: UserEntity.() -> Unit = {}) = find(uuid) ?: new {
            this.uuid = id
            transaction {
                init()
            }
        }

        inline fun findOrNew(player: OfflinePlayer, crossinline init: UserEntity.() -> Unit = {}) =
            findOrNew(player.uniqueId, init)

        fun find(uuid: UUID) = transaction { find { User.id eq uuid }.limit(1).firstOrNull() }
    }

    var uuid by User.id
    var coin by User.coin
    var foodConsumed by User.foodConsumed
    var blockPlaced by User.blockPlaced
    var toolBroken by User.toolBroken
    val gachas by GachaEntity referrersOn User.id
}
