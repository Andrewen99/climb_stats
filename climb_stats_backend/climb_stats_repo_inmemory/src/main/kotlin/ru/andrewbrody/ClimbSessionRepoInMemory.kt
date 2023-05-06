package ru.andrewbrody

import io.github.reactivecircus.cache4k.Cache
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import model.ClimbSession
import repo.ClimbSessionsRepo
import repo.DbRepoResponse
import java.util.*
import kotlin.time.Duration
import kotlin.time.Duration.Companion.hours

class ClimbSessionRepoInMemory(
    initObjects: List<ClimbSession> = emptyList(),
    ttl: Duration = 24.hours,
    val randomUuid: () -> String = { UUID.randomUUID().toString() }
) : ClimbSessionsRepo {
    private val cache = Cache.Builder<String, ClimbSession>()
        .expireAfterWrite(ttl)
        .build()
    private val mutex: Mutex = Mutex()

    init {
        initObjects.forEach {
            val id = it.id
            if (id != null) cache.put(id, it)
        }
    }
    override suspend fun getAll(userId: String): DbRepoResponse<List<ClimbSession>> {
        return DbRepoResponse(cache.asMap().values.filter { it.user.id == userId }.toList())
    }

    override suspend fun get(climbSessionId: String): DbRepoResponse<ClimbSession> {
        val session = cache.get(climbSessionId)
            ?: return DbRepoResponse.notFoundResponse("Climbing session with id $climbSessionId wasn't found in database")
        return DbRepoResponse(session)
    }

    override suspend fun save(climbSession: ClimbSession): DbRepoResponse<ClimbSession> {
        val (id, lock) = randomUuid() to randomUuid()
        val newSession = climbSession.copy(id = id, lock = lock)
        cache.put(id, newSession)
        return DbRepoResponse(newSession)
    }

    override suspend fun update(climbSession: ClimbSession): DbRepoResponse<ClimbSession> {
        val id = climbSession.id ?: return DbRepoResponse.emptyIdResponse("Climb Session")
        val lock = climbSession.lock ?: return DbRepoResponse.emptyLockResponse("Climb Session")
        val newLock = randomUuid()
        return mutex.withLock {
            val sessionFromDb = cache.get(id)
            when {
                sessionFromDb == null -> DbRepoResponse.notFoundResponse("Exercise with id $id wasn't found in db")
                sessionFromDb.lock != lock -> DbRepoResponse.concurrencyErrorResponse(climbSession, lock, sessionFromDb.lock)
                else -> {
                    val updatedSession = climbSession.copy(id = id, lock = newLock)
                    cache.put(id, updatedSession)
                    DbRepoResponse(updatedSession)
                }
            }
        }
    }

    override suspend fun delete(climbSession: ClimbSession): DbRepoResponse<ClimbSession> {
        val id = climbSession.id ?: return DbRepoResponse.emptyIdResponse("Climb Session")
        val lock = climbSession.lock ?: return DbRepoResponse.emptyLockResponse("Climb Session")
        return mutex.withLock {
            val sessionFromDb = cache.get(id)
            when {
                sessionFromDb == null -> DbRepoResponse.notFoundResponse("Exercise with id $id wasn't found in db")
                sessionFromDb.lock != lock -> DbRepoResponse.concurrencyErrorResponse(climbSession, lock, sessionFromDb.lock)
                else -> {
                    cache.invalidate(id)
                    DbRepoResponse(climbSession)
                }
            }
        }
    }
}