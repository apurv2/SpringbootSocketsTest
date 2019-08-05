package meetme.users;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface UserService {
    void save(User user);
    User findById(String id);
    List<User> findAll();
    public Map uploadToCloudinary(File target) throws IOException;
}
