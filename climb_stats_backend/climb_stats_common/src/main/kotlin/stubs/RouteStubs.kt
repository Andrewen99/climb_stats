package stubs

import model.Route

object RouteStubs {
    val ROUTE
        get() = Route(
            id = "1",
            gym = "Skala city",
            name = "Easy-peasy",
            color = "Blue",
            ropeNum = 10,
            lock = "l-1",
            grade = "6b+",
        )
}