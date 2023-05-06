package model

import kotlinx.serialization.Serializable


/**
 * Full climbing workout session, consisting of climbs and optional strength workout
 */
@Serializable
data class ClimbSession(
    val id: String?,
    val climbs: List<Climb>,
    val strengthTraining: StrengthTraining?,
    val user: User,
    val lock: String?,
    val date: String, //date format dd.mm.yyyy
)
