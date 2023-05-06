package stubs

import model.User

object UserStubs {
    val USER
        get() = User(
        id = "1",
        name = "TestName",
        surname = "TestSurname",
        weight = null,
        lock = "l1",
        registrationDate = "01.01.2023"
    )
}