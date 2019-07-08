package meetme.timeline;

import org.springframework.data.repository.CrudRepository;

import java.sql.Time;
import java.util.List;

public interface TimelineRepository extends CrudRepository<Timeline, String> {

    public List<Timeline> findByUserId(String userId);

}
