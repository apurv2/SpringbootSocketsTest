package meetme.conversations

import meetme.chats.ChatService
import meetme.chats.ChatThread
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.messaging.simp.SimpMessageSendingOperations
import org.springframework.stereotype.Service

@Service
class ConversationsServiceImpl : ConversationsService {

    @Autowired
    internal var conversationsRepository: ConversationsRepository? = null

    @Autowired
    internal var chatService: ChatService? = null

    @Autowired
    private val messagingTemplate: SimpMessageSendingOperations? = null

    override fun createChatMessage(conversations: Conversations) {

        conversations.threadId = getThreadId(conversations)
        conversationsRepository!!.save(conversations)

        val chat = chatService!!.createChatMessage(conversations)
        chatService!!.saveChatMessage(chat)
        //send WebSocket Message;
        this.messagingTemplate!!.convertAndSend("/conversations/" + conversations.firstUserId, conversations)
        this.messagingTemplate.convertAndSend("/conversations/" + conversations.secondUserId, chat)
        this.messagingTemplate.convertAndSend("/chatThread/" + conversations.threadId!!, chat)

    }

    override fun findConversationById(loggedInUserId: String): List<Conversations> {
        return conversationsRepository!!.findByFirstUserIdOrSecondUserId(loggedInUserId, loggedInUserId)
    }

    override fun findAllConversations(): List<Conversations> {
        return conversationsRepository!!.findAll() as List<Conversations>
    }

    fun getThreadId(conversations: Conversations) = if (conversations.firstUserId!!.compareTo(conversations.secondUserId) > 0)
        conversations.firstUserId + conversations.secondUserId else
        conversations.secondUserId + conversations.firstUserId
}
