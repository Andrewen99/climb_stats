package dto

import error.ClimbStatsError
import kotlinx.serialization.Serializable
import model.Grade
import model.ClimbRoute

@Serializable
data class ClimbRouteDto(
    val id: String? = null,
    val gym: String, // climbing gym name
    val name: String?, // route name
    val color: String?, // color of climbing holds
    val ropeNum: Int?, // rope number of the route
    val location: String? = null, // where the route located (if no rope number found)
    val grade: String, //european grades like 5a, 5b, 5c e.t.c.
    val lock: String? = null,
    val entityStatus: String = EntityStatus.NONE.status
) {
//    val ex = Exception();
    fun toRoute() = ClimbRoute(
        id = id,
        gym = gym,
        name = name,
        color = color,
        ropeNum = ropeNum,
        location = location,
        grade = Grade.fromStr(grade),
        lock = lock,
        entityStatus = EntityStatus.fromStr(entityStatus)
    )
}
