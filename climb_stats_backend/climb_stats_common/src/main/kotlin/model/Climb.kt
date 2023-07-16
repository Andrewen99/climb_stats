package model

import kotlinx.serialization.Serializable

/**
 * Single climb of some route
 */
@Serializable
data class Climb(
    val id: String?,
    val climbRoute: ClimbRoute,
    val user: User,
    val date: String, //date format dd.mm.yyyy
    val sent: Boolean,
    val ropeType: RopeType = RopeType.TOP_ROPE,
    val fallsCount: Int,
    val climbsCount: Int = 1
)

enum class RopeType {
    TOP_ROPE, BOTTOM_ROPE
}
