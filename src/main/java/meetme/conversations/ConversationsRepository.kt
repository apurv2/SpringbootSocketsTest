package meetme.conversations

import org.springframework.data.repository.CrudRepository

interface ConversationsRepository : CrudRepository<Conversations, String> {

    fun findByFirstUserIdOrSecondUserId(loggedInUserId: String, inUserId: String): List<Conversations>
    fun findByThreadId(loggedInUserId: String): List<Conversations>

}
