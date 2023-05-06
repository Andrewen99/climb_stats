package stubs

import model.Climb

object ClimbStubs {
    val CLIMB
        get() = Climb(
            id = "1",
            route = RouteStubs.ROUTE,
            user = UserStubs.USER,
            date = "01.01.2023",
            sent = false,
            fallsCount = 1
        )
}