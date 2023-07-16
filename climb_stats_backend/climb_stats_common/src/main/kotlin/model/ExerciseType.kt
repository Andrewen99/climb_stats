package model

import kotlinx.serialization.Serializable

@Serializable
data class ExerciseType(
    private val name: String,
    private val complexity: Double = 1.0
)
