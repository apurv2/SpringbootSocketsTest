package meetme.chats;

import meetme.conversations.Conversations;
import meetme.conversations.ConversationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ChatResource {

    @Autowired
    ConversationsService conversationsService;

    @Autowired
    ChatService chatService;

    @PostMapping("/conversations")
    @CrossOrigin
    public Conversations createOrUpdateChat(@RequestBody Conversations conversations) {
        return conversationsService.createChatMessage(conversations);
    }

    @MessageMapping("/send/message")
    public void onReceivedMesage(@Payload Conversations conversations) {
        conversationsService.createChatMessage(conversations);
    }

    @GetMapping("/conversations/{userId}")
    @CrossOrigin
    public List<Conversations> getUserConversations(@PathVariable String userId) {
        return conversationsService.findConversationById(userId);
    }

    @GetMapping("/conversations/all")
    @CrossOrigin
    public List<Conversations> getAllConversations() {
        return conversationsService.findAllConversations();
    }

    @GetMapping("/chats/all")
    @CrossOrigin
    public List<ChatThread> getAllChats() {
        return chatService.findAllChats();
    }

    @GetMapping("/chats/{threadId}")
    @CrossOrigin
    public List<ChatThread> findChatsByThreadId(@PathVariable String threadId) {
        return chatService.findChatsByThreadId(threadId);
    }

}
