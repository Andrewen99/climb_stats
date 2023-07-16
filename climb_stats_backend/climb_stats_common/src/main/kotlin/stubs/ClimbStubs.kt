package stubs

import model.Climb

object ClimbStubs {
    val CLIMB1
        get() = Climb(
            id = "1",
            climbRoute = ClimbRouteStubs.CLIMB_ROUTE1,
            user = UserStubs.USER1,
            date = "01.01.2023",
            sent = false,
            fallsCount = 1
        )

    val CLIMB2
        get() = Climb(
            id = "2",
            climbRoute = ClimbRouteStubs.CLIMB_ROUTE2,
            user = UserStubs.USER1,
            date = "01.01.2023",
            sent = false,
            fallsCount = 1
        )

    val CLIMB3
        get() = Climb(
            id = "3",
            climbRoute = ClimbRouteStubs.CLIMB_ROUTE1,
            user = UserStubs.USER2,
            date = "01.01.2023",
            sent = false,
            fallsCount = 1
        )

    val CLIMB_FOR_SAVING
        get() = CLIMB1.copy(id = null)
}