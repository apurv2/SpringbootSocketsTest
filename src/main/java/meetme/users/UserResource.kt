package meetme.users


import meetme.utils.FileService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile
import ws.schild.jave.EncoderException

import java.io.File
import java.io.IOException
import java.util.concurrent.atomic.AtomicLong

@RestController
class UserResource {

    internal var fileNum = AtomicLong(0)

    @Autowired
    private lateinit var userService: UserService

    @Autowired
    private lateinit var meetMeUtils: FileService


    @PostMapping("/users")
    @CrossOrigin
    fun createOrUpdateUser(@RequestBody user: User): User? {
        userService.save(user)
        return userService.findById(user.userId)
    }

    @GetMapping("/users")
    @CrossOrigin
    fun all(): List<User> = userService.findAll()

    @GetMapping("/users/{userId}")
    @CrossOrigin
    fun findById(@PathVariable("userId") userId: String) = userService.findById(userId)

    @PostMapping(value = "/api/files")
    @CrossOrigin
    @Throws(IOException::class, EncoderException::class)
    fun uploadFile(@RequestParam("file") file: MultipartFile) {

        val source = meetMeUtils.convert(file)
        val target = File(fileNum.incrementAndGet().toString() + " target.mp4")
        meetMeUtils.compressFile(source, target)
        userService.uploadToCloudinary(target)
        meetMeUtils.cleanUp(source, target)
    }

    @GetMapping("/interests/{userId}")
    @CrossOrigin
    fun getAllUserInterests(@PathVariable("userId") userId: String): List<Pair<String, List<UserInterest>>> = userService.findAllUserInterests(userId)

    @PutMapping("/interests/{userId}")
    @CrossOrigin
    fun updateUserInterests(@PathVariable("userId") userId: String, @RequestBody userInterests: List<UserInterest>?) = userService.saveInterest(userInterests!!, userId)

}




