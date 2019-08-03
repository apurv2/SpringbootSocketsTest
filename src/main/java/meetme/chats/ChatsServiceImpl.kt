package meetme.chats

import meetme.conversations.Conversations
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import ws.schild.jave.AudioAttributes
import ws.schild.jave.VideoAttributes

@Service
class ChatsServiceImpl : ChatService {

    @Autowired
    internal var chatsRepository: ChatsRepository? = null

    override fun createChatMessage(conversations: Conversations) =
            ChatThread().apply {
                message = conversations.lastMessage
                threadId = conversations.threadId
                senderId = conversations.firstUserId
                receiverId = conversations.secondUserId
            }

    override fun findAllChats() = chatsRepository!!.findAll() as List<ChatThread>

    override fun findChatsByThreadId(threadId: String) = chatsRepository!!.findByThreadId(threadId)

    override fun saveChatMessage(chatThread: ChatThread) = chatsRepository!!.save(chatThread)

}


