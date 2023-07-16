
import repo.ClimbRepo
import ru.andrewbrody.ClimbRepoInMemory
import stubs.ClimbStubs


class ClimbRepoInMemoryTests : ClimbRepoTests() {
    override var repo: ClimbRepo = ClimbRepoInMemory()

    override val initObjects = listOf(
        ClimbStubs.CLIMB1,
        ClimbStubs.CLIMB2,
        ClimbStubs.CLIMB3,
    )

    override fun emptyRepo() {
        //done in fillRepo
    }

    override fun fillRepo() {
        repo = ClimbRepoInMemory(initObjects)
    }

}