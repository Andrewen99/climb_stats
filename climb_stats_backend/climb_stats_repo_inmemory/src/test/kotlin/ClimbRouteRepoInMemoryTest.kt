import model.ClimbRoute
import repo.RouteRepo
import ru.andrewbrody.RouteRepoInMemory
import stubs.ClimbRouteStubs

class ClimbRouteRepoInMemoryTest : ClimbRouteRepoTests() {
    override val initObjects: List<ClimbRoute> = listOf(ClimbRouteStubs.CLIMB_ROUTE1, ClimbRouteStubs.CLIMB_ROUTE2)
    override var repo: RouteRepo = RouteRepoInMemory(
        initObjects = initObjects,
        randomUuid = { TEST_UUID }
    )

    override fun emptyRepo() {
        // implemented in fillRepo
    }

    override fun fillRepo() {
        repo = RouteRepoInMemory(
            initObjects = initObjects,
            randomUuid = { TEST_UUID }
        )
    }
}