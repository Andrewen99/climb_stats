package stubs

import model.User

object UserStubs {
    val USER1
        get() = User(
        id = "1",
        name = "user-name-1",
        surname = "user-surname-1",
        weight = null,
        lock = "l1",
        registrationDate = "01.01.2023"
    )
    val USER2
        get() = User(
            id = "2",
            name = "user-name-2",
            surname = "user-surname-2",
            weight = null,
            lock = "l2",
            registrationDate = "01.01.2023"
        )
}