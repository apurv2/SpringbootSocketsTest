package meetme.tweets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TweetResource {

    @Autowired
    TweetsService tweetsService;

    private void createNewTweet() {
    }

    private void follow() {
    }


    private void unFollow() {
    }

    private void showTimeline() {
    }


}
