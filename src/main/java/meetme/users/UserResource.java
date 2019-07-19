package meetme.users;


import meetme.redis.model.User;
import meetme.utils.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ws.schild.jave.EncoderException;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class UserResource {

    AtomicLong fileNum = new AtomicLong(0);

    @Autowired
    private UserService userService;

    @Autowired
    private FileService meetMeUtils;


    @PostMapping("/users")
    @CrossOrigin
    public User createOrUpdateUser(@RequestBody User user) {
        userService.save(user);
        User user1 = userService.findById(user.getUserId());
        return user1;
    }

    @GetMapping("/users")
    public List<User> all() {

        return userService.findAll();
    }

    @PostMapping(value = "/api/files")
    @CrossOrigin
    public void uploadFile(@RequestParam("file") MultipartFile file) throws IOException, EncoderException {

        File source = meetMeUtils.convert(file);
        File target = new File(fileNum.incrementAndGet() + " target.mp4");
        meetMeUtils.compressFile(source, target);
        userService.uploadToCloudinary(target);
        meetMeUtils.cleanUp(source, target);
    }

}




