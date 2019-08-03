package meetme.timeline

import meetme.tweets.Followers
import meetme.tweets.Tweet
import meetme.tweets.TweetsService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TimelineServiceImpl : TimelineService {

    @Autowired
    lateinit var timelineRepository: TimelineRepository

    @Autowired
    lateinit var tweetsService: TweetsService

    private fun createTimelineEntries(followers: List<Followers>, tweet: Tweet) = followers.map { mapTimeline(tweet, it) }

    override fun saveTimeLine(tweet: Tweet, userId: String) =
            timelineRepository!!.saveAll(createTimelineEntries(tweetsService!!.findFollowers(userId), tweet))!!


    override fun getTimeline(userId: String) = timelineRepository!!.findByUserId(userId)

    private fun mapTimeline(tweet: Tweet, followers: Followers): Timeline {
        return Timeline().apply {
            tweetMessage = tweet.tweetMessage
            userId = followers.childUserId
            tweetedUserName = tweet.userName
        }
    }
}