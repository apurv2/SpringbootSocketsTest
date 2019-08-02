package meetme.timeline;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;
import meetme.tweets.Followers;
import meetme.tweets.Tweet;
import meetme.tweets.TweetsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TimelineServiceImpl implements TimelineService {

    @Autowired
    TimelineRepository timelineRepository;

    @Autowired
    TweetsService tweetsService;

    public Iterable<Timeline> createTimelineEntries(List<Followers> followers, Tweet tweet) {

        return followers.stream().map(item -> mapTimeline(tweet, item))
                .collect(Collectors.toList());

    }

    @Override
    public Iterable<Timeline> saveTimeLine(Tweet tweet, String userId) {
        List<Followers> followers = tweetsService.findFollowers(userId);
//        return timelineRepository.save(createTimelineEntries(followers, tweet));
        return null;
    }

    @Override
    public List<Timeline> getTimeline(String userId) {
        return timelineRepository.findByUserId(userId);
    }

    private Timeline mapTimeline(Tweet tweet, Followers followers) {


        Timeline timeline = new Timeline();
        timeline.setTweetMessage(tweet.getTweetMessage());
        timeline.setUserId(followers.getChildUserId());
        timeline.setTweetedUserName(tweet.getUserName());
        return timeline;
    }
}
