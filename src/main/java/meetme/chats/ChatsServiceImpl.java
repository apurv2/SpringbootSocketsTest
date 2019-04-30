package meetme.chats;

import meetme.conversations.Conversations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatsServiceImpl implements ChatService {

    @Autowired
    ChatsRepository chatsRepository;

    @Override
    public ChatThread createChatMessage(Conversations conversations) {

        ChatThread chatThread = new ChatThread();

        chatThread.setMessage(conversations.getLastMessage());
        chatThread.setThreadId(conversations.getThreadId());
        chatThread.setSenderId(conversations.getLoggedInUserId());
        chatThread.setReceiverId(conversations.getOtherUserId());


        return chatsRepository.save(chatThread);
    }

    @Override
    public List<ChatThread> findAllChats() {
        return (List<ChatThread>) chatsRepository.findAll();
    }

    @Override
    public List<ChatThread> findChatsByThreadId(String threadId) {
        return chatsRepository.findByThreadId(threadId);
    }
}
