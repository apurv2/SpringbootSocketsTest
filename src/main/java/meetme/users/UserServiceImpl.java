package meetme.users;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import meetme.redis.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    Cloudinary cloudinary;

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

    public Map uploadToCloudinary(File targetFile) throws IOException {
        return cloudinary.uploader().upload(targetFile, ObjectUtils.asMap("resource_type", "video"));
    }


}
