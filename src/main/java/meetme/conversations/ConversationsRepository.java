package meetme.conversations;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ConversationsRepository extends CrudRepository<Conversations, String> {

    List<Conversations> findByOtherUserIdOrLoggedInUserId(String loggedInUserId, String inUserId);
    List<Conversations> findByThreadId(String loggedInUserId);


}
