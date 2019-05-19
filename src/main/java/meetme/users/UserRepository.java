package meetme.users;

import meetme.redis.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {
    User findByUserId(String id);
}
