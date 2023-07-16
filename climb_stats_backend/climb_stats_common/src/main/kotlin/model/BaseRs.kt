package model

import error.ClimbStatsError
import kotlinx.serialization.Serializable

/**
 * Base response dto
 */
@Serializable
data class BaseRs<T>(
    var requestId: String?,
    var data: T?,
    val success: Boolean = true,
    val errors: List<ClimbStatsError>? = null
) {
    fun <E> copyWithData(data: E) = BaseRs(
        requestId = this.requestId,
        data = data,
        success = this.success,
        errors = this.errors
    )
}
