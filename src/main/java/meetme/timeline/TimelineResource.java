package meetme.timeline;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TimelineResource {

    @Autowired
    TimelineService timelineService;


    @CrossOrigin
    @GetMapping("timeline/{userId}")
    private List<Timeline> getTimeline(@PathVariable String userId) {
        return timelineService.getTimeline(userId);
    }


}
