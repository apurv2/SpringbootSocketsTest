package meetme.timeline;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@RedisHash("Timeline")
public class Timeline {

    @Id
    private String id;

    @Indexed
    private String userId;
    private String tweetMessage;
    private String tweetURL;
    private String tweetedUserId;
    private String tweetedUserName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTweetMessage() {
        return tweetMessage;
    }

    public void setTweetMessage(String tweetMessage) {
        this.tweetMessage = tweetMessage;
    }

    public String getTweetURL() {
        return tweetURL;
    }

    public void setTweetURL(String tweetURL) {
        this.tweetURL = tweetURL;
    }

    public String getTweetedUserId() {
        return tweetedUserId;
    }

    public void setTweetedUserId(String tweetedUserId) {
        this.tweetedUserId = tweetedUserId;
    }

    public String getTweetedUserName() {
        return tweetedUserName;
    }

    public void setTweetedUserName(String tweetedUserName) {
        this.tweetedUserName = tweetedUserName;
    }
}
