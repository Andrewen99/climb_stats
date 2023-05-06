package repo

import model.StrengthTraining

interface StrengthTrainingRepo {
    fun getAll(userId: String): DbRepoResponse<List<StrengthTraining>>
}