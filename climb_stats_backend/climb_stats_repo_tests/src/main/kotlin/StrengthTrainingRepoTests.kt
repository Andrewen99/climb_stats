import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.collections.shouldNotBeEmpty
import io.kotest.matchers.nulls.shouldNotBeNull
import model.Climb
import model.StrengthTraining
import org.junit.jupiter.api.Test
import repo.ClimbRepo
import repo.StrengthTrainingRepo
import stubs.UserStubs

abstract class StrengthTrainingRepoTests : EmptyFillRepo {
    abstract var repo: StrengthTrainingRepo
    abstract val initObjects: List<StrengthTraining>

    @Test
    open fun getAllSuccess() = runRepoTest {
        emptyRepo()
        fillRepo()
        val userId = UserStubs.USER1.id!!
        val result = repo.getAll(userId)
        result.shouldBeSuccessResponse(initObjects.filter { it.user.id == userId })
    }
}