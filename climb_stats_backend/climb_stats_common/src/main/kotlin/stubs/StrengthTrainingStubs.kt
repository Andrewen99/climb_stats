package stubs

import model.StrengthTraining

object StrengthTrainingStubs {
    val STRENGTH_TRAINING1
        get() = StrengthTraining(
            id = "1",
            exercises = listOf(ExerciseStubs.EXERCISE1),
            user = UserStubs.USER1,
            date = "01.01.2021",
            rest = 0
        )

    val STRENGTH_TRAINING2
        get() = StrengthTraining(
            id = "2",
            exercises = listOf(ExerciseStubs.EXERCISE2),
            user = UserStubs.USER1,
            date = "01.01.2022",
            rest = 0
        )

    val STRENGTH_TRAINING3
        get() = StrengthTraining(
            id = "3",
            exercises = listOf(ExerciseStubs.EXERCISE2),
            user = UserStubs.USER2,
            date = "01.01.2022",
            rest = 0
        )

    val STRENGTH_TRAINING_FOR_SAVING
        get() = STRENGTH_TRAINING1.copy(id = null)
}