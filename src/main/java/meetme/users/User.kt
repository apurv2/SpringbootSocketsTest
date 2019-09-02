package meetme.users

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.index.Indexed
import java.io.Serializable
import java.time.LocalDate
import java.time.LocalDateTime

@RedisHash("User")
data class User(
        @Id
        @Indexed
        var userId: String,
        var lastSeen: LocalDateTime?,
        var name: String?,
        var gender: String?,
        var sexualOrientation: String?,
        var interests: List<UserInterest>?,
        var userName: String?,
        var profileTextDescription: String?,
        var latitude: Double?,
        var longitude: Double?,
        var city: String?,
        var freeMsgs: Int?,
        var birthday: LocalDate?,
        var profileViews: Long = 0,
        var feet: Int?,
        var inches: Int,
        var weight: Int,
        var ethnicity: String? = null,
        var bodyType: String? = null,
        var verified: Boolean ) : Serializable

