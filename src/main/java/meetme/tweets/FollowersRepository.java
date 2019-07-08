package meetme.tweets;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FollowersRepository extends CrudRepository<Followers, String> {

    public List<Followers> findByUserId(String userId);
}
