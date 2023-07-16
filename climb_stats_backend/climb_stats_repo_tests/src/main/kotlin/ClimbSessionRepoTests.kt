import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.collections.shouldNotBeEmpty
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import model.ClimbSession
import org.junit.jupiter.api.Test
import repo.ClimbSessionsRepo
import stubs.ClimbSessionStubs.CLIMB_SESSION1
import stubs.ClimbStubs.CLIMB_FOR_SAVING
import stubs.StrengthTrainingStubs
import stubs.UserStubs

abstract class ClimbSessionRepoTests : EmptyFillRepo {
    abstract var repo: ClimbSessionsRepo
    abstract val initObjects: List<ClimbSession>

    @Test
    open fun getAllSuccess() = runRepoTest {
        emptyAndRefill()

        val userId = UserStubs.USER1.id!!
        val result = repo.getAll(userId)
        result.shouldBeSuccessResponse(result.data)
    }

    @Test
    fun getSuccess() = runRepoTest {
        emptyAndRefill()

        val result = repo.get(CLIMB_SESSION1.id!!)
        result.success.shouldBeTrue()
        result.data.shouldNotBeNull()
            .shouldBe(CLIMB_SESSION1)

    }

    @Test
    fun getNotFound() = runRepoTest {
        emptyAndRefill()

        val wrongId = CLIMB_SESSION1.id!! + "-wrong"
        val result = repo.get(wrongId)
        result.shouldBeNotFoundResponse()
    }

    fun save() = runRepoTest {
        emptyAndRefill()

        val saveObject = ClimbSession(
            id = null,
            climbs = listOf(CLIMB_FOR_SAVING),
            strengthTraining = StrengthTrainingStubs.STRENGTH_TRAINING_FOR_SAVING,
            user = UserStubs.USER1,
            lock = null,
            date = "01.01.2023"
        )
        val result = repo.save(saveObject)
        result.shouldBeSuccessResponse(
            saveObject.copy(id = TEST_UUID, lock = TEST_UUID)
        )
    }

    @Test
    fun updateSuccess() = runRepoTest {
        emptyAndRefill()

        val updateObj = CLIMB_SESSION1.copy(
            date = "12.12.2023"
        )
        val dbResponse = repo.update(updateObj)
        dbResponse.shouldBeSuccessResponse(updateObj.copy(lock = TEST_UUID))
    }

    @Test
    fun updateNotFound() = runRepoTest {
        emptyAndRefill()

        val updateObj = CLIMB_SESSION1.copy(
            id = "wrong-id", date = "12.12.2023"
        )
        val dbResponse = repo.update(updateObj)
        dbResponse.shouldBeNotFoundResponse()
    }

    @Test
    fun updateConcurrencyError() = runRepoTest {
        emptyAndRefill()

        val updateObj = CLIMB_SESSION1.copy(
            lock = "wrong-lock", date = "12.12.2023"
        )
        val dbResponse = repo.update(updateObj)
        dbResponse.shouldBeConcurrencyResponse(CLIMB_SESSION1)
    }

    @Test
    fun deleteSuccess() = runRepoTest {
        emptyAndRefill()

        val deleteObj = CLIMB_SESSION1
        val dbResponse = repo.delete(deleteObj)
        dbResponse.shouldBeSuccessResponse(CLIMB_SESSION1)
        val getResponse = repo.get(CLIMB_SESSION1.id!!)
        getResponse.shouldBeNotFoundResponse()
    }

    @Test
    fun deleteNotFound() = runRepoTest {
        emptyAndRefill()

        val updateObj = CLIMB_SESSION1.copy(
            id = "wrong-id", date = "12.12.2023"
        )
        val dbResponse = repo.update(updateObj)
        dbResponse.shouldBeNotFoundResponse()
    }

    @Test
    fun deleteConcurrencyError() = runRepoTest {
        emptyAndRefill()

        val updateObj = CLIMB_SESSION1.copy(
            lock = "wrong-lock"
        )
        val dbResponse = repo.update(updateObj)
        dbResponse.shouldBeConcurrencyResponse(CLIMB_SESSION1)
    }
}