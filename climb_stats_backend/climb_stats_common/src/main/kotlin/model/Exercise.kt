package model

import kotlinx.serialization.Serializable

/**
 * Strength exercise like pull-ups, push-ups...
 */
@Serializable
data class Exercise (
    val id: String?,
    val exerciseType: ExerciseType,
    val reps: Int,
    val rest: Int, // in seconds
    val addedWeight: Double,
    val lock: String?,
    val sets: Int = 1
)
