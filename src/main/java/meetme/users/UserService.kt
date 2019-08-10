package meetme.users

import org.springframework.data.redis.core.PartialUpdate
import java.io.File
import java.io.IOException

interface UserService {
    fun save(user: User)

    fun findById(id: String): User?

    fun findAll(): List<User>

    @Throws(IOException::class)
    fun uploadToCloudinary(target: File): Map<*, *>

    fun findAllUserInterests(user: String): List<Pair<String, List<UserInterest>>>

    fun saveInterest(userInterest: List<UserInterest>, userId: String): PartialUpdate<User>
    fun updateUser(user: User, columnName: String? = null): PartialUpdate<User>
}
