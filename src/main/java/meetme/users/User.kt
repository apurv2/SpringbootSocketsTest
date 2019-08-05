package meetme.users

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import meetme.configuration.LocalDateDeserializer
import meetme.configuration.LocalDateSerializer
import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import java.io.Serializable

import java.time.LocalDate

@RedisHash("User")
data class User(
        @Id
        var userId: String? = null,
        @JsonDeserialize(using = LocalDateDeserializer::class)
        @JsonSerialize(using = LocalDateSerializer::class)
        var lastSeen: LocalDate? = null,
        var name: String? = null,
        val gender: String? = null,
        val sexualOrientation: String? = null,
        val profileTextDescription: String? = null) : Serializable
