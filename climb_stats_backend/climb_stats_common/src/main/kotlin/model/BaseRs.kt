package model

import error.ClimbStatsError
import kotlinx.serialization.Serializable

/**
 * Base response dto
 */
@Serializable
data class BaseRs<T>(
    val requestId: String,
    val data: T?,
    val success: Boolean = true,
    val errors: List<ClimbStatsError>? = null
)
