package stubs

import model.Exercise
import model.ExerciseType

object ExerciseStubs {
    val EXERCISE1
        get() = Exercise(
            id = "1",
            lock = "lock-1",
            exerciseType = ExerciseType("pull-ups"),
            reps = 10,
            rest = 0,
            addedWeight = 0.0,
            sets = 2
        )

    val EXERCISE2
        get() = Exercise(
            id = "2",
            lock = "lock-2",
            exerciseType = ExerciseType("push-ups"),
            reps = 20,
            rest = 30,
            addedWeight = 0.0,
            sets = 5
        )

    val EXERCISE_FOR_SAVING
        get() = Exercise(
            id = null,
            lock = null,
            exerciseType = ExerciseType("new-ex"),
            reps = 5,
            rest = 30,
            addedWeight = 10.0,
            sets = 5
        )
}