package meetme.chats

import meetme.conversations.Conversations

interface ChatService {
    fun createChatMessage(conversations: Conversations): ChatThread

    fun findAllChats(): List<ChatThread>

    fun findChatsByThreadId(threadId: String): List<ChatThread>
    fun saveChatMessage(chatThread: ChatThread): ChatThread
}
