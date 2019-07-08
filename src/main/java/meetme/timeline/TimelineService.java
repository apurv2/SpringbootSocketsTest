package meetme.timeline;

import meetme.tweets.Tweet;

import java.util.List;

public interface TimelineService {

    Iterable<Timeline> saveTimeLine(Tweet timeline, String userId);

    List<Timeline> getTimeline(String userId);
}
