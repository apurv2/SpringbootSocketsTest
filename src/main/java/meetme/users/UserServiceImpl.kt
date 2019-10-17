package meetme.users

import com.cloudinary.Cloudinary
import com.cloudinary.utils.ObjectUtils
import meetme.interests.UserInterestRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Sort
import org.springframework.data.keyvalue.core.KeyValueTemplate
import org.springframework.data.redis.core.PartialUpdate
import org.springframework.data.redis.core.mapping.RedisMappingContext
import org.springframework.data.redis.repository.core.MappingRedisEntityInformation
import org.springframework.stereotype.Service
import java.io.File
import java.io.IOException
import java.time.LocalDateTime
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

    var mappingContext = RedisMappingContext()


    override fun save(user: User) {


        user.lastSeen = LocalDateTime.now()
        val dbUser: User? = findById(user.userId!!)
        if (dbUser != null) updateUser(user) else userRepository.save(user)

    }

    override fun findById(id: String): User? {
        val user: List<User> = userRepository.findByUserId(id)
        return if (user.isNotEmpty()) user[0] else null
    }

    fun incrementUserVisit(user: User) {
        user.profileViews = user.profileViews.inc()
        updateUser(user, "profileViews")
    }

    override fun findUserByUserId(userId: String): User? {
        var user: User? = findById(userId)
        if (user != null) incrementUserVisit(user)
        return user
    }

    private fun <T> getEntityInformation(entityClass: Class<T>)
            :
            MappingRedisEntityInformation<out Any, T> {
        return MappingRedisEntityInformation(mappingContext.getRequiredPersistentEntity(entityClass))
    }

    override fun findAll(): List<User> {

//        var query: KeyValueQuery<User> = KeyValueQuery<User>()
//        query.sort = Sort(Sort.Direction.DESC, "freeMsgs")
//        return kvTemplate.find(query, User::class.java) as List<User>

        return userRepository.findAll(Sort.by("freeMsgs").descending()) as List<User>
//        return userRepository.findFirst10ByFreeMsgs()
    }

    @Throws(IOException::class)
    override fun uploadToCloudinary(targetFile: File) = cloudinary
            .uploader()
            .upload(targetFile, ObjectUtils.asMap("resource_type", "video"))

    override fun saveInterest(userInterest: List<UserInterest>, userId: String): PartialUpdate<User> =
            kvTemplate
                    .update(PartialUpdate<User>(userId, User::class.java)
                            .set("interests", userInterest))

    override fun findAllUserInterests(userId: String): List<Pair<String, List<UserInterest>>> {
        val kinksMap = findUserByUserId(userId)?.interests?.groupBy { it.code }
                ?: emptyMap()

        return userInterestRepository
                .findAll()
                .filter { kinksMap.containsKey(it.code) }
                .onEach { it.selected = true }
                .groupBy { it.categoryCd }
                .toList()
    }

    override fun updateUser(user: User, columnName: String?): PartialUpdate<User> {

        var partialUpdate: PartialUpdate<User> = PartialUpdate(user.userId!!, User::class.java)
        getUserKV(user, columnName).forEach { partialUpdate = partialUpdate.set(it.key, it.value!!) }
        return kvTemplate.update(partialUpdate)
    }

    fun getUserKV(user: User?, excludedColumn: String? = null) =
            user!!::class.memberProperties
                    .map { it as KProperty1<Any, *> }
                    .filter { it.get(user) != null && "userId" != it.name && ("profileViews" != it.name || it.get(user) != 0L) }
                    .filter { excludedColumn?.equals(it.name) ?: true }
                    .map { it.name to it.get(user) }.toMap()
}
