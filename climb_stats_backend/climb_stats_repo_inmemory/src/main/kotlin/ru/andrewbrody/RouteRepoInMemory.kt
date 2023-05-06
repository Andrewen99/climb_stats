package ru.andrewbrody

import io.github.reactivecircus.cache4k.Cache
import model.Route
import repo.DbRepoResponse
import repo.RouteRepo
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import java.util.UUID
import kotlin.time.Duration
import kotlin.time.Duration.Companion.hours

class RouteRepoInMemory(
    initObjects: List<Route> = emptyList(),
    ttl: Duration = 24.hours,
    val randomUuid: () -> String = { UUID.randomUUID().toString() }
) : RouteRepo {
    private val cache = Cache.Builder<String, Route>()
        .expireAfterWrite(ttl)
        .build()
    private val mutex: Mutex = Mutex()

    init {
        initObjects.forEach {
            val id = it.id
            if (id != null) cache.put(id, it)
        }
    }
    override suspend fun get(id: String): DbRepoResponse<Route> {
        val route = cache.get(id)
        return if (route == null) {
            DbRepoResponse.notFoundResponse("Route with id $id wasn't found in db")
        } else {
            DbRepoResponse(route)
        }
    }

    override suspend fun getAll(): DbRepoResponse<List<Route>> {
        val routes = cache.asMap().values.toList()
        return DbRepoResponse(routes)
    }

    override suspend fun save(route: Route): DbRepoResponse<Route> {
        val routeId = randomUuid()
        val lock = randomUuid()
        val newRoute = route.copy(id = routeId, lock = lock)
        cache.put(routeId, newRoute)
        return DbRepoResponse(newRoute)
    }

    override suspend fun update(route: Route): DbRepoResponse<Route> {
        val routeId = route.id ?: return DbRepoResponse.emptyIdResponse("route")
        val routeLock = route.lock ?: return DbRepoResponse.emptyLockResponse("route")
        val newLock = randomUuid()
        return mutex.withLock {
            val routeFromDb = cache.get(routeId)
            when {
                routeFromDb == null -> DbRepoResponse.notFoundResponse("Route with id $routeId wasn't found in db")
                routeFromDb.lock != routeLock -> DbRepoResponse.concurrencyErrorResponse(routeFromDb, routeLock, routeFromDb.lock)
                else -> {
                    val updatedRoute = route.copy(id = routeId, lock = newLock)
                    cache.put(routeId, updatedRoute)
                    DbRepoResponse(updatedRoute)
                }

            }
        }
    }

    override suspend fun delete(route: Route): DbRepoResponse<Route> {
        val routeId = route.id
        val routeLock = route.lock
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
                    DbRepoResponse(route)
                }

            }
        }
    }
}