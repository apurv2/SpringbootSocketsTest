package meetme.chats;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;

@RestController
public class WebSocketController {

    @Autowired
    private SimpMessageSendingOperations messagingTemplate;
//
//    @MessageMapping("/message.{username}")
//    public void processMessageFromClient(
//            @Payload String message, @DestinationVariable("username") String username) throws Exception {
//        messagingTemplate.convertAndSend("/user/" + username + "/exchange/amq.direct/chatThread.message", message);
//    }


//    @MessageMapping("/send/message.{username}")
//    public void onReceivedMesage(@Payload String message, @DestinationVariable("username") String username) {
//        this.messagingTemplate.convertAndSend("/chatThread/" + username, message);
//    }
}