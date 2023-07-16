package dto

import kotlinx.serialization.Serializable
import model.RopeType

@Serializable
data class ClimbDto(
    val id: String?,
    val route: ClimbRouteDto,
    val date: String, //date format dd.mm.yyyy
    val sent: Boolean,
    val ropeType: RopeType = RopeType.TOP_ROPE,
    val fallsCount: Int,
    val climbsCount: Int = 1
)
