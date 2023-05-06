package repo

import model.Exercise

interface ExerciseRepo {
    suspend fun get(id: String) : DbRepoResponse<Exercise>
    suspend fun getAll() : DbRepoResponse<List<Exercise>>
    suspend fun save(ex: Exercise) : DbRepoResponse<Exercise>
    suspend fun update(ex: Exercise) : DbRepoResponse<Exercise>
    suspend fun delete(ex: Exercise) : DbRepoResponse<Exercise>
}