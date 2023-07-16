package repo

import model.Climb

interface ClimbRepo {
    suspend fun getAll(userId: String): DbRepoResponse<List<Climb>>
}