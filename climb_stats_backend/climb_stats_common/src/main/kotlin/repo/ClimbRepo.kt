package repo

import model.Climb

interface ClimbRepo {
    fun getAll(userId: String): DbRepoResponse<List<Climb>>
}