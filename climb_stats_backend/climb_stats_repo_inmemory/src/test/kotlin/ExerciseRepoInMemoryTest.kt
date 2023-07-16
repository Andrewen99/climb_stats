import model.Exercise
import repo.ExerciseRepo
import ru.andrewbrody.ExerciseRepoInMemory
import stubs.ExerciseStubs

class ExerciseRepoInMemoryTest : ExerciseRepoTests() {
    override val initObjects: List<Exercise> = listOf(ExerciseStubs.EXERCISE1, ExerciseStubs.EXERCISE2)
    override var repo: ExerciseRepo = ExerciseRepoInMemory(
        initObjects = initObjects,
        randomUuid = { TEST_UUID }
    )

    override fun emptyRepo() {
        // implemented in fillRepo
    }

    override fun fillRepo() {
        repo = ExerciseRepoInMemory(
            initObjects = initObjects,
            randomUuid = { TEST_UUID }
        )
    }

}