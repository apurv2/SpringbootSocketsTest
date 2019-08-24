package meetme.conversations

import meetme.chats.ChatService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.messaging.simp.SimpMessageSendingOperations
import org.springframework.stereotype.Service

@Service
class ConversationsServiceImpl : ConversationsService {

    @Autowired
    lateinit var conversationsRepository: ConversationsRepository

    @Autowired
    lateinit var chatService: ChatService

    @Autowired
    lateinit var messagingTemplate: SimpMessageSendingOperations

    override fun createChatMessage(conversations: Conversations) {
        var conversation: Conversations = conversations
        conversation.threadId = getThreadId(conversations)
        conversation = saveConversation(conversations)
        val chat = chatService.createChatMessage(conversation)
        chatService.saveChatMessage(chat)

        this.messagingTemplate.convertAndSend("/conversations/" + conversations.firstUserId, conversation)
        this.messagingTemplate.convertAndSend("/conversations/" + conversations.secondUserId, conversation)
        this.messagingTemplate.convertAndSend("/chatThread/" + conversations.threadId, chat)

    }

    private fun saveConversation(conversation: Conversations): Conversations {
        val dbConversation: Conversations? = conversationsRepository.findById(conversation.threadId!!).orElse(null)
        conversation.unreadCounter = dbConversation?.unreadCounter?.inc() ?: 0
        return conversationsRepository.save(conversation)
    }

    override fun findConversationById(loggedInUserId: String): List<Conversations> {
        return conversationsRepository.findByFirstUserIdOrSecondUserId(loggedInUserId, loggedInUserId)
    }

    override fun findAllConversations(): List<Conversations> {
        return conversationsRepository.findAll() as List<Conversations>
    }

    fun getThreadId(conversations: Conversations) = if (conversations.firstUserId!!.compareTo(conversations.secondUserId!!) > 0)
        conversations.firstUserId + conversations.secondUserId else
        conversations.secondUserId + conversations.firstUserId
}
