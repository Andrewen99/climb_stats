package ru.andrewbrody

import io.github.reactivecircus.cache4k.Cache
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import model.Exercise
import repo.DbRepoResponse
import repo.ExerciseRepo
import java.util.*
import kotlin.time.Duration
import kotlin.time.Duration.Companion.hours

class ExerciseRepoInMemory(
    initObjects: List<Exercise> = emptyList(),
    ttl: Duration = 24.hours,
    val randomUuid: () -> String = { UUID.randomUUID().toString() }
) : ExerciseRepo {
    private val cache = Cache.Builder<String, Exercise>()
        .expireAfterWrite(ttl)
        .build()
    private val mutex: Mutex = Mutex()

    init {
        initObjects.forEach {
            val id = it.id
            if (id != null) cache.put(id, it)
        }
    }
    override suspend fun get(id: String): DbRepoResponse<Exercise> {
        val route = cache.get(id)
        return if (route == null)
            DbRepoResponse.notFoundResponse("Exercise with id $id wasn't found in db")
        else
            DbRepoResponse(route)
    }

    override suspend fun getAll(): DbRepoResponse<List<Exercise>> {
        return DbRepoResponse(cache.asMap().values.toList())
    }

    override suspend fun save(ex: Exercise): DbRepoResponse<Exercise> {
        val (id, lock) = randomUuid() to randomUuid()
        val newEx = ex.copy(id = id, lock = lock)
        cache.put(id, newEx)
        return DbRepoResponse(newEx)
    }

    override suspend fun update(ex: Exercise): DbRepoResponse<Exercise> {
        val id = ex.id ?: return DbRepoResponse.emptyIdResponse("Exercise")
        val lock = ex.lock ?: return DbRepoResponse.emptyLockResponse("Exercise")
        val newLock = randomUuid()
        return mutex.withLock {
            val exFromDb = cache.get(id)
            when {
                exFromDb == null -> DbRepoResponse.notFoundResponse("Exercise with id $id wasn't found in db")
                exFromDb.lock != lock -> DbRepoResponse.concurrencyErrorResponse(ex, lock, exFromDb.lock)
                else -> {
                    val updatedEx = ex.copy(id, newLock)
                    cache.put(id, updatedEx)
                    DbRepoResponse(updatedEx)
                }
            }
        }
    }

    override suspend fun delete(ex: Exercise): DbRepoResponse<Exercise> {
        val id = ex.id ?: return DbRepoResponse.emptyIdResponse("Exercise")
        val lock = ex.lock ?: return DbRepoResponse.emptyLockResponse("Exercise")
        return mutex.withLock {
            val exFromDb = cache.get(id)
            when {
                exFromDb == null -> DbRepoResponse.notFoundResponse("Exercise with id $id wasn't found in db")
                exFromDb.lock != lock -> DbRepoResponse.concurrencyErrorResponse(ex, lock, exFromDb.lock)
                else -> {
                    cache.invalidate(id)
                    DbRepoResponse(ex)
                }
            }
        }
    }
}