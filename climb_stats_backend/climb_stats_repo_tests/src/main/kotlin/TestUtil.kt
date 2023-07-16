import error.ClimbStatsError
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import repo.DbRepoResponse

const val TEST_UUID = "test-id-lock"



fun <T> DbRepoResponse<T>.shouldBeSuccessResponse(expectedData: T? = null) {
    success.shouldBeTrue()
    data.shouldNotBeNull()
    when (expectedData) {
        null -> {}
        is List<*> -> (data as List<*>).shouldContainAll(expectedData)
        else -> data.shouldBe(expectedData)
    }
    if (expectedData != null)  {
        if (expectedData is Collection<*>)
        data.shouldBe(expectedData)
    }
    errors.shouldBeEmpty()
}
fun <T> DbRepoResponse<T>.shouldBeNotFoundResponse() {
    success.shouldBeFalse()
    data.shouldBeNull()
    errors[0].shouldBeNotFoundError()
}

fun <T> DbRepoResponse<T>.shouldBeConcurrencyResponse(dataFromDb: T) {
    success.shouldBeFalse()
    data.shouldBe(dataFromDb)
    errors[0].shouldBeConcurrencyError()
}

fun ClimbStatsError.shouldBeNotFoundError() {
    code.shouldBe("not-found")
    group.shouldBe("repo")
    field.shouldBe("id")
}



fun ClimbStatsError.shouldBeConcurrencyError() {
    code.shouldBe("concurrency")
    group.shouldBe("repo")
    field.shouldBe("lock")
}