package dto

import kotlinx.serialization.Serializable
import model.Climb
import model.StrengthTraining
import model.User

@Serializable
data class ClimbSessionDto(
    val id: String?,
    val climbs: List<ClimbDto>,
    val strengthTraining: StrengthTrainingDto?,
    val lock: String?,
    val date: String
)
