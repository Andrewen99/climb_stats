package dto

import kotlinx.serialization.Serializable

enum class EntityStatus(
    val status: String
) {
    NEW("new"), UPDATED("updated"), NONE("none");

    companion object {
        fun fromStr(status: String) : EntityStatus {
            return EntityStatus.values().firstOrNull { it.status  == status} ?: NONE
        }
    }
}
