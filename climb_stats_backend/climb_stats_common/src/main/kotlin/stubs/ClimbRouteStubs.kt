package stubs

import model.ClimbRoute
import model.Grade

object ClimbRouteStubs {
    val CLIMB_ROUTE1
        get() = ClimbRoute(
            id = "1",
            gym = "Skala city",
            name = "Easy-peasy",
            color = "Blue",
            ropeNum = 10,
            lock = "l1",
            grade = Grade.fromStr("6a+")!!,
        )

    val CLIMB_ROUTE2
        get() = ClimbRoute(
            id = "2",
            gym = "Skala city",
            name = "Not that easy",
            color = "Black",
            ropeNum = 11,
            lock = "l2",
            grade = Grade.fromStr("6c+")!!,
        )

    val CLIMB_ROUTE_FOR_SAVING
        get() = ClimbRoute(
            id = null,
            gym = "Skala city",
            name = "New ungraded",
            color = "Orange",
            ropeNum = 11,
            lock = null,
            grade = Grade.fromStr("6b")!!,
        )
}