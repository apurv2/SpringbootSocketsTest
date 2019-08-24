package meetme.chats

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.index.Indexed
import java.io.Serializable
import java.sql.Timestamp
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

@RedisHash("ChatThread")
data class ChatThread(
        @Id
        var id: String? = null,
        @Indexed
        var threadId: String? = null,
        var message: String? = null,
        var senderUserName: String? = null,
        var receiverUserName: String? = null,
        var senderId: String? = null,
        var receiverId: String? = null,
        @Indexed
        var timestamp: LocalDateTime? = null) : Serializable
