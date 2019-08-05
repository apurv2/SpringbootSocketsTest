package meetme.tweets

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
class TweetResource {

    @Autowired
    lateinit var tweetsService: TweetsService;


    private val allFollowers: Collection<Followers>
        @CrossOrigin
        @GetMapping("followers")
        get() = tweetsService!!.findAll() as Collection<Followers>

    @PostMapping("tweets/follow")
    @CrossOrigin
    private fun follow(@RequestBody following: Following) = tweetsService!!.follow(following)

    @CrossOrigin
    @GetMapping("followers/{userId}")
    private fun showFollowers(@PathVariable userId: String) = tweetsService!!.findFollowers(userId)

    @PostMapping("tweets/tweet")
    @CrossOrigin
    private fun tweet(@RequestBody tweet: Tweet): Tweet = tweetsService!!.tweet(tweet)


    private fun unFollow() {}

    @GetMapping("tweets/all")
    @CrossOrigin
    private fun getAllTweets() = tweetsService!!.findAll()
}
