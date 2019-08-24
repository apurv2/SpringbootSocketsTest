package meetme.chats

import org.springframework.data.repository.CrudRepository

interface ChatsRepository : CrudRepository<ChatThread, String> {
    fun findByThreadIdOrderByTimestampDesc(threadId: String): List<ChatThread>
}
