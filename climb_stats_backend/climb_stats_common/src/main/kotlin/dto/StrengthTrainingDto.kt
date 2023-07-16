package dto

import kotlinx.serialization.Serializable
import model.Exercise
import model.User

@Serializable
data class StrengthTrainingDto(
    val id: String?,
    val exercises: List<ExerciseDto>,
    val date: String, //date format dd.mm.yyyy
    val circles: Int = 1,
    val rest: Int
)
