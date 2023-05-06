package error

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

/**
 * Simple error data class for better API feedback
 */
@Serializable
data class ClimbStatsError(
    val code: String = "",
    val group: String = "",
    val field: String = "",
    val message: String = "",
    @Transient
    val error: Throwable? = null,
)
