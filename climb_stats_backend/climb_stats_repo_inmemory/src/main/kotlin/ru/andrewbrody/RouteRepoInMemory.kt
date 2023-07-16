package ru.andrewbrody

import io.github.reactivecircus.cache4k.Cache
import model.ClimbRoute
import repo.DbRepoResponse
import repo.RouteRepo
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import repo.collapse
import java.util.UUID
import kotlin.time.Duration
import kotlin.time.Duration.Companion.hours

class RouteRepoInMemory(
    initObjects: List<ClimbRoute> = emptyList(),
    ttl: Duration = 24.hours,
    val randomUuid: () -> String = { UUID.randomUUID().toString() }
) : RouteRepo {
    private val cache = Cache.Builder<String, ClimbRoute>()
        .expireAfterWrite(ttl)
        .build()
    private val mutex: Mutex = Mutex()

    init {
        initObjects.forEach {
            val id = it.id
            if (id != null) cache.put(id, it)
        }
    }
    override suspend fun get(id: String): DbRepoResponse<ClimbRoute> {
        val route = cache.get(id)
        return if (route == null) {
            DbRepoResponse.notFoundResponse("Route with id $id wasn't found in db")
        } else {
            DbRepoResponse(route)
        }
    }

    override suspend fun getAll(): DbRepoResponse<List<ClimbRoute>> {
        val routes = cache.asMap().values.toList()
        return DbRepoResponse(routes)
    }

    override suspend fun save(climbRoute: ClimbRoute): DbRepoResponse<ClimbRoute> {
        val routeId = randomUuid()
        val lock = randomUuid()
        val newRoute = climbRoute.copy(id = routeId, lock = lock)
        cache.put(routeId, newRoute)
        return DbRepoResponse(newRoute)
    }

    override suspend fun saveAll(climbRoutes: List<ClimbRoute>): DbRepoResponse<List<ClimbRoute?>> {
        return climbRoutes.map { save(it) }.collapse()
    }

    override suspend fun update(climbRoute: ClimbRoute): DbRepoResponse<ClimbRoute> {
        val routeId = climbRoute.id ?: return DbRepoResponse.emptyIdResponse("route")
        val routeLock = climbRoute.lock ?: return DbRepoResponse.emptyLockResponse("route")
        val newLock = randomUuid()
        return mutex.withLock {
            val routeFromDb = cache.get(routeId)
            when {
                routeFromDb == null -> DbRepoResponse.notFoundResponse("Route with id $routeId wasn't found in db")
                routeFromDb.lock != routeLock -> DbRepoResponse.concurrencyErrorResponse(routeFromDb, routeLock, routeFromDb.lock)
                else -> {
                    val updatedRoute = climbRoute.copy(id = routeId, lock = newLock)
                    cache.put(routeId, updatedRoute)
                    DbRepoResponse(updatedRoute)
                }

            }
        }
    }

    override suspend fun updateAll(climbRoutes: List<ClimbRoute>): DbRepoResponse<List<ClimbRoute?>> {
        return climbRoutes.map { update(it) }.collapse()
    }

    override suspend fun delete(climbRoute: ClimbRoute): DbRepoResponse<ClimbRoute> {
        val routeId = climbRoute.id
        val routeLock = climbRoute.lock
        if (routeId == null) {
            return DbRepoResponse.errorResponse("empty-id", "id", "Route id is empty. Cannot delete route.")
        }
        if (routeLock == null) {
            return DbRepoResponse.errorResponse("empty-lock", "lock", "Route lock is empty. Cannot delete route.")
        }
        return mutex.withLock {
            val routeFromDb = cache.get(routeId)
            when {
                routeFromDb == null -> DbRepoResponse.notFoundResponse("Route with id $routeId wasn't found in db")
                routeFromDb.lock != routeLock -> DbRepoResponse.concurrencyErrorResponse(routeFromDb, routeLock, routeFromDb.lock)
                else -> {
                    cache.invalidate(routeId)
                    DbRepoResponse(routeFromDb)
                }

            }
        }
    }
}