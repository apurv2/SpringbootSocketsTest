package meetme.users

import com.cloudinary.Cloudinary
import com.cloudinary.utils.ObjectUtils
import meetme.interests.UserInterestRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.io.File
import java.io.IOException
import org.springframework.data.keyvalue.core.KeyValueTemplate
import org.springframework.data.redis.core.PartialUpdate
import kotlin.reflect.KProperty1
import kotlin.reflect.full.memberProperties


@Service
class UserServiceImpl : UserService {

    @Autowired
    internal lateinit var userRepository: UserRepository

    @Autowired
    lateinit var kvTemplate: KeyValueTemplate

    @Autowired
    internal lateinit var userInterestRepository: UserInterestRepository

    @Autowired
    internal lateinit var cloudinary: Cloudinary

    override fun save(user: User) {
        user.lastSeen = System.currentTimeMillis()
        var dbUser: User? = userRepository.findById(user.userId).get()
        if (dbUser != null) updateUser(user) else userRepository.save(user)
    }

    override fun findById(id: String): User {

        var user: User = userRepository.findById(id).get()
        user.profileViews = if (user.profileViews != null) user.profileViews?.inc() else 1
        updateUser(user, "profileViews")
        return user
    }

    override fun findAll(): List<User> = userRepository.findAll() as List<User>

    @Throws(IOException::class)
    override fun uploadToCloudinary(targetFile: File): Map<*, *> {
        return cloudinary.uploader().upload(targetFile, ObjectUtils.asMap("resource_type", "video"))
    }

    override fun saveInterest(userInterest: List<UserInterest>, userId: String): PartialUpdate<User> = kvTemplate.update(PartialUpdate<User>(userId, User::class.java!!)
            .set("interests", userInterest))

    override fun findAllUserInterests(userId: String): List<Pair<String, List<UserInterest>>> {
        var userInterest: List<UserInterest> = userInterestRepository.findAll() as List<UserInterest>
        var dbUser: User? = userRepository.findById(userId).get()

        var kinksMap: Map<String, List<UserInterest>> = dbUser?.interests!!.groupBy { it.code }

        userInterest.filter { kinksMap.containsKey(it.code) }
                .forEach { it.selected = true }

        return userInterest.groupBy { it.categoryCd }.toList()
    }

    override fun updateUser(user: User, columnName: String?): PartialUpdate<User> {

        var partialUpdate: PartialUpdate<User> = PartialUpdate(user.userId, User::class.java)
        getUserKV(user, columnName).forEach { partialUpdate = partialUpdate.set(it.key, it.value!!) }
        return kvTemplate.update(partialUpdate)
    }

    private fun getUserKV(user: User?, excludedColumn: String? = null): Map<String, Any?> =
            user!!::class.memberProperties
                    .map { it as KProperty1<Any, *> }
                    .filter { it.get(user) != null && "userId" != it.name }
                    .filter { excludedColumn != null && excludedColumn == it.name }
                    .map { it.name to it.get(user) }.toMap()
}
