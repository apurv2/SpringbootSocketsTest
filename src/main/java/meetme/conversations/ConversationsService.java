package meetme.conversations;

import java.util.List;

public interface ConversationsService {
    void createChatMessage(Conversations conversations);

    List<Conversations> findConversationById(String threadId);

    List<Conversations> findAllConversations();

}
