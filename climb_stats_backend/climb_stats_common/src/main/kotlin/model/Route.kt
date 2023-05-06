package model

import kotlinx.serialization.Serializable

/**
 * Climbing route
 */
@Serializable
data class Route(
    val id: String? = null,
    val gym: String, // climbing gym name
    val name: String?, // route name
    val color: String?, // color of climbing holds
    val ropeNum: Int?, // rope number of the route
    val location: String? = null, // where the route located (if no rope number found)
    val grade: String, //european grades like 5a, 5b, 5c e.t.c.
    val lock: String? = null
)