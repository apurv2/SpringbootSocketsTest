package meetme.conversations

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.index.Indexed
import java.io.Serializable

import java.sql.Timestamp

@RedisHash("conversations")
data class Conversations(
        @Id
        @Indexed
        var threadId: String? = null,
        @Indexed
        val firstUserId: String? = null,
        @Indexed
        var secondUserId: String? = null,
        var firstUserName: String? = null,
        var secondUserName: String? = null,
        var lastMessage: String? = null,
        var timestamp: Timestamp? = null,
        var unreadCounter: Int = 0,
        var unreadUserId: String? = null) : Serializable
