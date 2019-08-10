package meetme.users

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.index.Indexed
import java.io.Serializable

@RedisHash("User")
data class User(
        @Id
        @Indexed
        var userId: String,
        var lastSeen: Long?,
        var name: String?,
        var gender: String?,
        var sexualOrientation: String?,
        var interests: List<UserInterest>?,
        var userName: String?,
        var profileTextDescription: String?,
        var latitude: Double?,
        var longitude: Double?,
        var city: String?,
        var freeMsgs: Integer?,
        var birthDay: Long?,
        var profileViews : Long?) : Serializable

