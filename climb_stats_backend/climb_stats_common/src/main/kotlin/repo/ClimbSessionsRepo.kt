package repo

import model.ClimbSession

interface ClimbSessionsRepo {

    suspend fun getAll(userId: String) : DbRepoResponse<List<ClimbSession>>

    suspend fun get(climbSessionId: String) : DbRepoResponse<ClimbSession>

    suspend fun save(climbSession: ClimbSession) : DbRepoResponse<ClimbSession>

    suspend fun update(climbSession: ClimbSession) : DbRepoResponse<ClimbSession> //check if prev userId == current userId

    suspend fun delete(climbSession: ClimbSession) : DbRepoResponse<ClimbSession>
}