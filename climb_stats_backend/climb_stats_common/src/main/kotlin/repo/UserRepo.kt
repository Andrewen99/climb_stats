package repo

import model.Exercise
import model.User

/**
 * TODO: Add restrictions only for admin
 */
interface UserRepo {
    suspend fun get(id: String) : DbRepoResponse<User>
    suspend fun getAll() : DbRepoResponse<List<User>>
    suspend fun save(user: User) : DbRepoResponse<User>
    suspend fun update(user: User) : DbRepoResponse<User>
    suspend fun delete(user: User) : DbRepoResponse<Exercise>
}