package stubs

import model.ClimbSession

object ClimbSessionStubs {
    val CLIMB_SESSION1
        get() = ClimbSession(
            id = "1",
            climbs = listOf(ClimbStubs.CLIMB1),
            strengthTraining = StrengthTrainingStubs.STRENGTH_TRAINING1,
            user = UserStubs.USER1,
            date = "01.01.2023",
            lock = "lock-1"
        )

    val CLIMB_SESSION2
        get() = ClimbSession(
            id = "2",
            climbs = listOf(ClimbStubs.CLIMB2),
            strengthTraining = StrengthTrainingStubs.STRENGTH_TRAINING1,
            user = UserStubs.USER1,
            date = "01.01.2023",
            lock = "lock-2"
        )
}