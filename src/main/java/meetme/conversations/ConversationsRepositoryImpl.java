//package meetme.conversations;
//
//import org.springframework.data.redis.core.HashOperations;
//import org.springframework.data.redis.core.RedisTemplate;
//
//public class ConversationsRepositoryImpl implements ConversationsRepository {
//
//    private RedisTemplate<String, Conversations> redisTemplate;
//
//    private HashOperations hashOperations;
//
//
//    public ConversationsRepositoryImpl(RedisTemplate<String, Conversations> redisTemplate) {
//        this.redisTemplate = redisTemplate;
//        hashOperations = redisTemplate.opsForHash();
//    }
//
//    @Override
//    public void saveUserConversations(Conversations conversation) {
//        hashOperations.put("USERCONVERSATION", conversation.getId(), conversation);
//
//    }
//
//    @Override
//    public Conversations findUserConversationsById(String id) {
//        return (Conversations) hashOperations.get("USERCONVERSATION", id);
//    }
//
//    @Override
//    public void updateUserConversations(Conversations conversation) {
//        saveUserConversations(conversation);
//    }
//
//    @Override
//    public void deleteUserConversations(String id) {
//        hashOperations.delete("USERCONVERSATION", id);
//
//    }
//
//}
