package meetme.chats

import meetme.conversations.Conversations
import meetme.conversations.ConversationsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.web.bind.annotation.*

@RestController
class ChatResource {

    @Autowired
    internal var conversationsService: ConversationsService? = null

    @Autowired
    internal var chatService: ChatService? = null

    val allConversations: List<Conversations>
        @GetMapping("/conversations/all")
        @CrossOrigin
        get() = conversationsService!!.findAllConversations()

    val allChats: List<ChatThread>
        @GetMapping("/chats/all")
        @CrossOrigin
        get() = chatService!!.findAllChats()

    @PostMapping("/conversations")
    @CrossOrigin
    fun createOrUpdateChat(@RequestBody conversations: Conversations) = conversationsService!!.createChatMessage(conversations)

    @MessageMapping("/send/message")
    fun onReceivedMesage(@Payload conversations: Conversations) = conversationsService!!.createChatMessage(conversations)


    @GetMapping("/conversations/{userId}")
    @CrossOrigin
    fun getUserConversations(@PathVariable userId: String) = conversationsService!!.findConversationById(userId)


    @GetMapping("/chats/{threadId}")
    @CrossOrigin
    fun findChatsByThreadId(@PathVariable threadId: String) = chatService!!.findChatsByThreadId(threadId)


}
