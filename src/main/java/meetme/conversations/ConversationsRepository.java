package meetme.conversations;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ConversationsRepository extends CrudRepository<Conversations, String> {

    List<Conversations> findByLoggedInUserId(String loggedInUserId);
    List<Conversations> findByThreadId(String loggedInUserId);


}
