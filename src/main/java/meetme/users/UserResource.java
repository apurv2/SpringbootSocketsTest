package meetme.users;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import meetme.redis.model.User;

import java.util.List;

@RestController
public class UserResource {

    @Autowired
    private UserService userService;

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
}




