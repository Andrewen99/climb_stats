package stubs

import model.Exercise

object ExerciseStubs {
    val EXERCISE
        get() = Exercise(
            id = "1",
            lock = "lock-1",
            name = "pull-ups",
            reps = 10,
            rest = 0,
            addedWeight = 0.0,
            sets = 2
        )
}