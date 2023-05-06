package ru.andrewbrody

import io.github.reactivecircus.cache4k.Cache
import model.StrengthTraining
import repo.DbRepoResponse
import repo.StrengthTrainingRepo
import kotlin.time.Duration
import kotlin.time.Duration.Companion.hours

class StrengthTrainingRepoInMemory(
    initObjects: List<StrengthTraining> = emptyList(),
    ttl: Duration = 24.hours,
): StrengthTrainingRepo {
    private val cache = Cache.Builder<String, StrengthTraining>()
        .expireAfterWrite(ttl)
        .build()

    init {
        initObjects.forEach {
            val id = it.id
            if (id != null) cache.put(id, it)
        }
    }
    override fun getAll(userId: String): DbRepoResponse<List<StrengthTraining>> {
        return DbRepoResponse(cache.asMap().values.filter { it.user.id == userId }.toList())
    }
}