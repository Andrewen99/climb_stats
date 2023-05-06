package repo

import error.RepoConcurrencyException
import error.ClimbStatsError
import model.BaseRs

/**
 * Database response wrapper for better feedback
 */
data class DbRepoResponse<T>(
    val data: T?,
    val success: Boolean = true,
    val errors: List<ClimbStatsError> = emptyList()
) {

    fun toBaseRs(requestId: String) = BaseRs(
        requestId = requestId,
        data = data,
        success = success,
        errors = errors
    )
    companion object {
        fun <T> notFoundResponse(message: String) =
            DbRepoResponse<T>(
                null,
                false,
                listOf(
                    ClimbStatsError(
                        code = "not-found",
                        group = "repo",
                        field = "id",
                        message = message,
                        error = null

                    )
                )
            )


        fun <T> errorResponse(code: String, field: String, message: String, data: T? = null) =
            DbRepoResponse(
                data,
                false,
                listOf(
                    ClimbStatsError(
                        code = code,
                        group = "repo",
                        field = field,
                        message = message,
                        error = null

                    )
                )
            )

        fun <T> emptyIdResponse(entity: String) = DbRepoResponse<T>(
            null,
            false,
            listOf(
                ClimbStatsError(
                    code = "empty-id",
                    group = "repo",
                    field = "id",
                    message = "$entity id is empty"
                )
            )
        )

        fun <T> emptyLockResponse(entity: String) = DbRepoResponse<T>(
            null,
            false,
            listOf(
                ClimbStatsError(
                    code = "empty-lock",
                    group = "repo",
                    field = "lock",
                    message = "$entity lock is empty"
                )
            )
        )

        fun <T> concurrencyErrorResponse(data: T, submittedLock: String, actualLock: String?) = DbRepoResponse(
            data = data,
            success = false,
            errors = listOf(
                ClimbStatsError(
                    code = "concurrency",
                    group = "repo",
                    field = "lock",
                    message = "You're trying to update object with outdated information. " +
                            "Submitted object lock: $submittedLock, actual lock: $actualLock" +
                            "(The object has been changed concurrently by another user or process).",
                    error = RepoConcurrencyException(submittedLock, actualLock)
                )
            )
        )

    }
}