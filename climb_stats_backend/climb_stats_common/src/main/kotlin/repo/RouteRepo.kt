package repo

import model.ClimbRoute

interface RouteRepo {
    suspend fun get(id: String) : DbRepoResponse<ClimbRoute>
    suspend fun getAll() : DbRepoResponse<List<ClimbRoute>>
    suspend fun save(climbRoute: ClimbRoute) : DbRepoResponse<ClimbRoute>
    suspend fun saveAll(climbRoutes: List<ClimbRoute>) : DbRepoResponse<List<ClimbRoute?>>
    suspend fun update(climbRoute: ClimbRoute) : DbRepoResponse<ClimbRoute>
    suspend fun updateAll(climbRoutes: List<ClimbRoute>) : DbRepoResponse<List<ClimbRoute?>>
    suspend fun delete(climbRoute: ClimbRoute) : DbRepoResponse<ClimbRoute>
}