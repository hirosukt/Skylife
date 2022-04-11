package love.chihuyu.skylife.database

import org.bukkit.OfflinePlayer
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

class UserEntity(id: EntityID<Int>) : IntEntity(id) {
    companion object : IntEntityClass<UserEntity>(UsersTable) {

        inline fun findOrNew(uuid: UUID, crossinline init: UserEntity.() -> Unit = {}) = find(uuid) ?: new {
            this.uuid = uuid
            transaction {
                init()
            }
        }

        inline fun findOrNew(player: OfflinePlayer, crossinline init: UserEntity.() -> Unit = {}) = findOrNew(player.uniqueId, init)

        fun find(uuid: UUID) = transaction { find { UsersTable.uuid eq uuid }.limit(1).firstOrNull() }
    }

    var uuid by UsersTable.uuid

    val gachas by GachaEntity referrersOn UsersTable.uuid

    var coin by UsersTable.coin

    var foodConsumed by UsersTable.foodConsumed

    var blockPlaced by UsersTable.blockPlaced

    var toolBroken by UsersTable.toolBroken
}