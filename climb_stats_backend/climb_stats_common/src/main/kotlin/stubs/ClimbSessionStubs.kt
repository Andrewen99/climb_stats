package stubs

import model.ClimbSession

object ClimbSessionStubs {
    val CLIMB_SESSION
        get() = ClimbSession(
            id = "1",
            climbs = listOf(ClimbStubs.CLIMB),
            strengthTraining = StrengthTrainingStubs.STRENGTH_TRAINING,
            user = UserStubs.USER,
            date = "01.01.2021",
            lock = "lock-1"
        )
}