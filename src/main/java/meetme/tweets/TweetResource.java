package meetme.tweets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
public class TweetResource {

    @Autowired
    TweetsService tweetsService;

    private void createNewTweet() {
    }

    @PostMapping("tweets/follow")
    @CrossOrigin
    private void follow(@RequestBody Following following) {
        tweetsService.follow(following);
    }

    @CrossOrigin
    @GetMapping("followers/{userId}")
    private List<Followers> showFollowers(@PathVariable String userId) {
       return tweetsService.findFollowers(userId);
    }


    @CrossOrigin
    @GetMapping("followers")
    private Collection<Followers> getAllFollowers() {
        return (Collection<Followers>) tweetsService.findAll();
    }


    @PostMapping("tweets/tweet")
    @CrossOrigin
    private void tweet(@RequestBody Tweet tweet) {
        tweetsService.tweet(tweet);
    }


    private void unFollow() {
    }

    private void showTimeline() {
    }


}
