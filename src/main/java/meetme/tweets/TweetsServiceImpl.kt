package meetme.tweets

import meetme.timeline.TimelineService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TweetsServiceImpl : TweetsService {

    @Autowired
    internal var followingRepository: FollowingRepository? = null

    @Autowired
    internal var followersRepository: FollowersRepository? = null

    @Autowired
    internal var tweetRepository: TweetRepository? = null

    @Autowired
    internal var timelineService: TimelineService? = null


    override fun follow(following: Following) {

        followingRepository!!.save(following)
        val followers = Followers()
        followers.childUserId = following.userId
        followers.userId = following.parentUserId
        followersRepository!!.save(followers)

    }

    override fun findFollowers(id: String): List<Followers> {
        return followersRepository!!.findByUserId(id)
    }

    override fun findAll(): List<Tweet> {
        return tweetRepository!!.findAll() as List<Tweet>
    }

    override fun tweet(tweet: Tweet): Tweet {
        timelineService!!.saveTimeLine(tweet, tweet.getUserId())
        return tweetRepository!!.save(tweet)

    }
}