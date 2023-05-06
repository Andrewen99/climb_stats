package stubs

import model.StrengthTraining

object StrengthTrainingStubs {
    val STRENGTH_TRAINING
        get() = StrengthTraining(
            id = "1",
            exercises = listOf(ExerciseStubs.EXERCISE),
            user = UserStubs.USER,
            date = "01.01.2021",
            rest = 0,
            lock = "l1"
        )
}