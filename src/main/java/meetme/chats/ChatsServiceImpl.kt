package meetme.chats

import meetme.conversations.Conversations
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.keyvalue.core.KeyValueTemplate
import org.springframework.data.redis.core.PartialUpdate
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class ChatsServiceImpl : ChatService {

    @Autowired
    lateinit var chatsRepository: ChatsRepository

    @Autowired
    lateinit var kvTemplate: KeyValueTemplate

    override fun createChatMessage(conversations: Conversations) =
            ChatThread().apply {
                message = conversations.lastMessage
                threadId = conversations.threadId
                senderId = conversations.firstUserId
                receiverId = conversations.secondUserId
                timestamp = LocalDateTime.now()
            }

    override fun findAllChats() = chatsRepository.findAll() as List<ChatThread>

    override fun findChatsByThreadId(threadId: String) = chatsRepository.findByThreadIdOrderByTimestampDesc(threadId).sortedBy { it.timestamp }

    override fun saveChatMessage(chatThread: ChatThread) = chatsRepository.save(chatThread)

    override fun setReadReceipt(threadId: String) =
            kvTemplate.update(PartialUpdate(threadId, Conversations::class.java)
                    .set("unreadCounter", 0))

}


