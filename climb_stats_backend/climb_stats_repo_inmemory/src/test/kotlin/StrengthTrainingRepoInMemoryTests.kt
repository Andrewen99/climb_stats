import model.StrengthTraining
import repo.StrengthTrainingRepo
import ru.andrewbrody.StrengthTrainingRepoInMemory
import stubs.StrengthTrainingStubs
import stubs.StrengthTrainingStubs.STRENGTH_TRAINING1
import stubs.StrengthTrainingStubs.STRENGTH_TRAINING2
import stubs.StrengthTrainingStubs.STRENGTH_TRAINING3

class StrengthTrainingRepoInMemoryTests : StrengthTrainingRepoTests() {

    override val initObjects: List<StrengthTraining> = listOf(STRENGTH_TRAINING1, STRENGTH_TRAINING2, STRENGTH_TRAINING3)
    override var repo: StrengthTrainingRepo = StrengthTrainingRepoInMemory(
        initObjects = initObjects
    )


    override fun emptyRepo() {
        // implemented in fillRepo
    }

    override fun fillRepo() {
        repo = StrengthTrainingRepoInMemory(
            initObjects = initObjects
        )
    }
}