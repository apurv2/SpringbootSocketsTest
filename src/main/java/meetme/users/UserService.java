package meetme.users;

import meetme.redis.model.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    void save(User user);
    User findById(String id);
    List<User> findAll();
}
