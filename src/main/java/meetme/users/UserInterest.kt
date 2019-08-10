package meetme.users

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import java.io.Serializable


@RedisHash("Interests")
data class UserInterest(
        @Id
        var code: String,
        var categoryCd: String,
        var categoryDescription: String,
        var description: String,
        var selected : Boolean?) : Serializable
