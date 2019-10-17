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
        var userId: String? = null,
        var lastSeen: LocalDateTime? = null,
        var name: String? = null,
        var gender: String? = null,
        var sexualOrientation: String? = null,
        var interests: List<UserInterest>? = null,
        var userName: String? = null,
        var profileTextDescription: String? = null,
        var latitude: Double? = null,
        var longitude: Double? = null,
        var city: String? = null,
        var freeMsgs: Int? = null,
        var birthday: LocalDate? = null,
        var profileViews: Long = 0,
        var feet: Int? = null,
        var inches: Int? = null,
        var weight: Int? = null,
        var ethnicity: String? = null,
        var bodyType: String? = null,
        var verified: Boolean? = null) : Serializable

