import model.ClimbRoute
import org.junit.jupiter.api.Test
import repo.RouteRepo
import stubs.*

abstract class ClimbRouteRepoTests : EmptyFillRepo {
    abstract var repo: RouteRepo
    abstract val initObjects: List<ClimbRoute>

    @Test
    open fun getAllSuccess() = runRepoTest {
        emptyAndRefill()

        val result = repo.getAll()
        result.shouldBeSuccessResponse(result.data)
    }

    @Test
    fun getSuccess() = runRepoTest {
        emptyAndRefill()

        val result = repo.get(ClimbRouteStubs.CLIMB_ROUTE1.id!!)
        result.shouldBeSuccessResponse(ClimbRouteStubs.CLIMB_ROUTE1)

    }

    @Test
    fun getNotFound() = runRepoTest {
        emptyAndRefill()

        val wrongId = ClimbRouteStubs.CLIMB_ROUTE1.id!! + "-wrong"
        val result = repo.get(wrongId)
        result.shouldBeNotFoundResponse()
    }

    fun save() = runRepoTest {
        emptyAndRefill()

        val saveObject = ClimbRouteStubs.CLIMB_ROUTE_FOR_SAVING
        val result = repo.save(saveObject)
        result.shouldBeSuccessResponse(result.data)
    }

    @Test
    fun updateSuccess() = runRepoTest {
        emptyAndRefill()

        val updateObj = ClimbRouteStubs.CLIMB_ROUTE1.copy(
            name = "easy-peasy-lemon-squeezy"
        )
        val dbResponse = repo.update(updateObj)
        dbResponse.shouldBeSuccessResponse(dbResponse.data)
    }

    @Test
    fun updateNotFound() = runRepoTest {
        emptyAndRefill()

        val updateObj = ClimbRouteStubs.CLIMB_ROUTE1.copy(
            id = "wrong-id", name = "easy-peasy-lemon-squeezy"
        )
        val dbResponse = repo.update(updateObj)
        dbResponse.shouldBeNotFoundResponse()
    }

    @Test
    fun updateConcurrencyError() = runRepoTest {
        emptyAndRefill()

        val updateObj = ClimbRouteStubs.CLIMB_ROUTE1.copy(
            lock = "wrong-lock", name = "easy-peasy-lemon-squeezy"
        )
        val dbResponse = repo.update(updateObj)
        dbResponse.shouldBeConcurrencyResponse(ClimbRouteStubs.CLIMB_ROUTE1)
    }

    @Test
    fun deleteSuccess() = runRepoTest {
        emptyAndRefill()

        val deleteObj = ClimbRouteStubs.CLIMB_ROUTE1
        val dbResponse = repo.delete(deleteObj)
        dbResponse.shouldBeSuccessResponse()
        val getResponse = repo.get(ClimbRouteStubs.CLIMB_ROUTE1.id!!)
        getResponse.shouldBeNotFoundResponse()
    }

    @Test
    fun deleteNotFound() = runRepoTest {
        emptyAndRefill()

        val updateObj = ClimbRouteStubs.CLIMB_ROUTE1.copy(
            id = "wrong-id"
        )
        val dbResponse = repo.update(updateObj)
        dbResponse.shouldBeNotFoundResponse()
    }

    @Test
    fun deleteConcurrencyError() = runRepoTest {
        emptyAndRefill()

        val updateObj = ClimbRouteStubs.CLIMB_ROUTE1.copy(
            lock = "wrong-lock"
        )
        val dbResponse = repo.update(updateObj)
        dbResponse.shouldBeConcurrencyResponse(ClimbRouteStubs.CLIMB_ROUTE1)
    }
}