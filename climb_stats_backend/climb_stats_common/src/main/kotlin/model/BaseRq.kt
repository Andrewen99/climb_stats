package model

import kotlinx.serialization.Serializable

/**
 * Base request dto
 */
@Serializable
data class BaseRq<T>(
    val requestId: String,
    val data: T
)
