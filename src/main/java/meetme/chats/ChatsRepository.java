package meetme.chats;

import meetme.conversations.Conversations;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ChatsRepository extends CrudRepository<ChatThread, String> {

    List<ChatThread> findByThreadId(String ThreadId);
}
