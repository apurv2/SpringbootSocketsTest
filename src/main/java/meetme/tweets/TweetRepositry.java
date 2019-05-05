package meetme.tweets;

import org.springframework.data.repository.CrudRepository;

public interface TweetRepositry extends CrudRepository<Tweet, String> {
}
