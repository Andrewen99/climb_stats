package repo

import model.Exercise
import model.ExerciseType

interface ExerciseTypeRepo {
    suspend fun get(id: String) : DbRepoResponse<ExerciseType>
    suspend fun getAll() : DbRepoResponse<List<ExerciseType>>
    suspend fun save(ex: ExerciseType) : DbRepoResponse<ExerciseType>
    suspend fun update(ex: ExerciseType) : DbRepoResponse<ExerciseType>
    suspend fun delete(ex: ExerciseType) : DbRepoResponse<ExerciseType>
}