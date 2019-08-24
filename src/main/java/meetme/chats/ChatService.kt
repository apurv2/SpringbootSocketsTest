package meetme.chats

import meetme.conversations.Conversations
import org.springframework.data.redis.core.PartialUpdate

interface ChatService {
    fun createChatMessage(conversations: Conversations): ChatThread

    fun findAllChats(): List<ChatThread>

    fun findChatsByThreadId(threadId: String): List<ChatThread>
    fun saveChatMessage(chatThread: ChatThread): ChatThread
    fun setReadReceipt(threadId: String): PartialUpdate<Conversations>
}
