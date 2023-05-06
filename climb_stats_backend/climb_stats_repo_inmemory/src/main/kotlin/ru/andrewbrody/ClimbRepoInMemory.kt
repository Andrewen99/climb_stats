package ru.andrewbrody

import io.github.reactivecircus.cache4k.Cache
import model.Climb
import repo.ClimbRepo
import repo.DbRepoResponse
import kotlin.time.Duration
import kotlin.time.Duration.Companion.hours

class ClimbRepoInMemory(
    initObjects: List<Climb> = emptyList(),
    ttl: Duration = 24.hours,
): ClimbRepo {
    private val cache = Cache.Builder<String, Climb>()
        .expireAfterWrite(ttl)
        .build()

    init {
        initObjects.forEach {
            val id = it.id
            if (id != null) cache.put(id, it)
        }
    }
    override fun getAll(userId: String): DbRepoResponse<List<Climb>> {
        return DbRepoResponse(cache.asMap().values.filter { it.user.id != null }.toList())
    }
}