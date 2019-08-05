package meetme.tweets;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TweetsService {


    void follow(Following following);

    List<Followers> findFollowers(String id);

    List<Tweet> findAll();

    Tweet tweet(Tweet tweet);
}
