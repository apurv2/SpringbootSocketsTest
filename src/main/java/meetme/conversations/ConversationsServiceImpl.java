package meetme.conversations;

import meetme.chats.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConversationsServiceImpl implements ConversationsService {

    @Autowired
    ConversationsRepository conversationsRepository;

    @Autowired
    ChatService chatService;

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @Override
    public Conversations createChatMessage(Conversations conversations) {

//        conversations.setTimestamp(new Timestamp(new Date().getTime()));

        String threadId = conversations.getLoggedInUserId().compareTo(conversations.getOtherUserId()) > 0 ?
                conversations.getLoggedInUserId() + conversations.getOtherUserId() :
                conversations.getOtherUserId() + conversations.getLoggedInUserId();
        conversations.setThreadId(threadId);
        conversationsRepository.save(conversations);
        chatService.createChatMessage(conversations);

        //send WebSocket Message;
        this.messagingTemplate.convertAndSend("/conversations/" + conversations.getLoggedInUserId(), conversations);
        this.messagingTemplate.convertAndSend("/conversations/" + conversations.getOtherUserId(), conversations);
        this.messagingTemplate.convertAndSend("/chatThread/" + conversations.getThreadId(), conversations);

        return findConversationById(conversations.getLoggedInUserId()).get(0);

    }

    @Override
    public List<Conversations> findConversationById(String loggedInUserId) {
        return conversationsRepository.findByLoggedInUserId(loggedInUserId);
    }

    @Override
    public List<Conversations> findAllConversations() {
        return (List<Conversations>) conversationsRepository.findAll();
    }
}
