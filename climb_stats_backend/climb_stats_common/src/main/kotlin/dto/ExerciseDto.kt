package dto

import kotlinx.serialization.Serializable
import model.ExerciseType

@Serializable
data class ExerciseDto(
    val id: String?,
    val exerciseType: ExerciseType,
    val reps: Int,
    val rest: Int, // in seconds
    val addedWeight: Double,
    val lock: String?,
    val sets: Int = 1
)