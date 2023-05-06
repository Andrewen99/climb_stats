package repo

import model.Route

interface RouteRepo {
    suspend fun get(id: String) : DbRepoResponse<Route>
    suspend fun getAll() : DbRepoResponse<List<Route>>
    suspend fun save(route: Route) : DbRepoResponse<Route>
    suspend fun update(route: Route) : DbRepoResponse<Route>
    suspend fun delete(route: Route) : DbRepoResponse<Route>
}