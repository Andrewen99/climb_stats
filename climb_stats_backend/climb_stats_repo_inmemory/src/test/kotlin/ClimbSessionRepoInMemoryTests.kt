import model.ClimbSession
import repo.ClimbSessionsRepo
import ru.andrewbrody.ClimbSessionRepoInMemory
import stubs.ClimbSessionStubs

class ClimbSessionRepoInMemoryTests : ClimbSessionRepoTests() {
    override val initObjects: List<ClimbSession> = listOf(
        ClimbSessionStubs.CLIMB_SESSION1,
        ClimbSessionStubs.CLIMB_SESSION2
    )
    override var repo: ClimbSessionsRepo = ClimbSessionRepoInMemory(
        initObjects = initObjects,
        randomUuid = { TEST_UUID }
    )

    override fun emptyRepo() {
        //used in fillRepo
    }

    override fun fillRepo() {
        repo = ClimbSessionRepoInMemory(
            initObjects = initObjects,
            randomUuid = { TEST_UUID }
        )
    }
}