import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldContain
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import model.Exercise
import model.ExerciseType
import org.junit.jupiter.api.Test
import repo.ExerciseRepo
import stubs.ExerciseStubs.EXERCISE1
import stubs.ExerciseStubs.EXERCISE2
import stubs.ExerciseStubs.EXERCISE_FOR_SAVING

abstract class ExerciseRepoTests : EmptyFillRepo {
    abstract var repo: ExerciseRepo
    abstract val initObjects: List<Exercise>

    @Test
    fun getSuccess() = runRepoTest {
        emptyAndRefill()

        val id = EXERCISE1.id!!
        val repoResponse = repo.get(id)
        repoResponse.success.shouldBeTrue()
        repoResponse.errors.shouldBeEmpty()
        repoResponse.data.shouldNotBeNull()
            .shouldBe(EXERCISE1)
    }

    @Test
    fun getNotFound() = runRepoTest {
        emptyAndRefill()

        val id = EXERCISE1.id!! + "-wrong"
        val repoResponse = repo.get(id)
        repoResponse.shouldBeNotFoundResponse()
    }

    @Test
    fun getAll() = runRepoTest {
        emptyAndRefill()

        val response = repo.getAll()
        response.shouldBeSuccessResponse(initObjects)
    }

    @Test
    fun save() = runRepoTest {
        emptyAndRefill()

        val repoResponse = repo.save(EXERCISE_FOR_SAVING)
        repoResponse.shouldBeSuccessResponse()
        repoResponse.data.shouldBe(EXERCISE_FOR_SAVING.copy(id=TEST_UUID, lock = TEST_UUID))
    }

    @Test
    fun updateSuccess() = runRepoTest {
        emptyAndRefill()

        val updateObj = EXERCISE1.copy(exerciseType = ExerciseType("one-arm pull-ups"))
        val repoResponse = repo.update(updateObj)
        repoResponse.shouldBeSuccessResponse(updateObj.copy(lock = TEST_UUID))
    }

    @Test
    fun updateNotFound() = runRepoTest {
        emptyAndRefill()

        val updateObj = EXERCISE1.copy(exerciseType = ExerciseType("one-arm pull-ups"), id = "id-wrong")
        val repoResponse = repo.update(updateObj)
        repoResponse.shouldBeNotFoundResponse()
    }

    @Test
    fun updateConcurrency() = runRepoTest {
        emptyAndRefill()

        val updateObj = EXERCISE1.copy(exerciseType = ExerciseType("one-arm pull-ups"), lock = "lock-wrong")
        val repoResponse = repo.update(updateObj)
        repoResponse.shouldBeConcurrencyResponse(EXERCISE1)
    }

    @Test
    fun delete() = runRepoTest {
        emptyAndRefill()

        val deleteObj = EXERCISE1
        val repoResponse = repo.delete(deleteObj)
        repoResponse.shouldBeSuccessResponse(EXERCISE1)
    }

    @Test
    fun deleteNotFound() = runRepoTest {
        emptyAndRefill()

        val deleteObj = EXERCISE1.copy(id = "wrong-id")
        val repoResponse = repo.delete(deleteObj)
        repoResponse.shouldBeNotFoundResponse()
    }

}