package meetme.tweets;

import meetme.timeline.Timeline;
import meetme.timeline.TimelineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TweetsServiceImpl implements TweetsService {

    @Autowired
    FollowingRepository followingRepository;

    @Autowired
    FollowersRepository followersRepository;

    @Autowired
    TweetRepository tweetRepository;

    @Autowired
    TimelineService timelineService;


    @Override
    public void follow(Following following) {

        followingRepository.save(following);
        Followers followers = new Followers();
        followers.childUserId = following.userId;
        followers.userId = following.parentUserId;
        followersRepository.save(followers);

    }

    @Override
    public List<Followers> findFollowers(String id) {
        return followersRepository.findByUserId(id);
    }

    @Override
    public List<Followers> findAll() {
        return (List<Followers>) followersRepository.findAll();
    }

    @Override
    public Tweet tweet(Tweet tweet) {
        timelineService.saveTimeLine(tweet, tweet.getUserId());
        return tweetRepository.save(tweet);

    }
}