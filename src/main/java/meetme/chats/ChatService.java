package meetme.chats;

import meetme.conversations.Conversations;

import java.util.List;

public interface ChatService {
    ChatThread createChatMessage(Conversations conversations);

    List<ChatThread> findAllChats();

    List<ChatThread> findChatsByThreadId(String threadId);
}
