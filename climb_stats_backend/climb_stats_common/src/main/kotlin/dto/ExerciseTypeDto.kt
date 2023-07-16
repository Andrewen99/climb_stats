package dto

import kotlinx.serialization.Serializable

@Serializable
data class ExerciseTypeDto(
    private val name: String,
    private val complexity: Double = 1.0,
    val entityStatus: EntityStatus = EntityStatus.NONE
)
