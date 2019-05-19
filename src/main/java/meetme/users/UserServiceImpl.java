package meetme.users;

import meetme.redis.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public void save(User user) {

        Date input = new Date();
        LocalDateTime conv = LocalDateTime.ofInstant(input.toInstant(), ZoneId.systemDefault());
        LocalDate convDate = conv.toLocalDate();

        user.setLastSeen(convDate);
        userRepository.save(user);
    }

    @Override
    public User findById(String id) {
        return userRepository.findByUserId(id);
    }

    @Override
    public List<User> findAll() {
        return (List<User>) userRepository.findAll();
    }
}
