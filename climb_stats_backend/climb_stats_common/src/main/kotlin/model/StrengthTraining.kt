package model

import kotlinx.serialization.Serializable

/**
 * Full Strength training workout consisting of 1 or more exercises
 */
@Serializable
data class StrengthTraining(
    val id: String?,
    val exercises: List<Exercise>,
    val user: User,
    val date: String, //date format dd.mm.yyyy
    val circles: Int = 1,
    val lock: String?,
    val rest: Int
)
