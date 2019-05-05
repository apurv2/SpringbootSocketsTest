package meetme.tweets;

import org.springframework.data.repository.CrudRepository;

public interface FollowersRepository extends CrudRepository<Followers, String> {
}
