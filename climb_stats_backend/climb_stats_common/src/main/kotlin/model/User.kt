package model

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val id: String?,
    val name: String,
    val surname: String,
    val weight: Double?,
    val lock: String?,
    val registrationDate: String
)
