package meetme.conversations

interface ConversationsService {
    fun createChatMessage(conversations: Conversations)

    fun findConversationById(threadId: String): List<Conversations>

    fun findAllConversations(): List<Conversations>

}
