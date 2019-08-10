package meetme.interests

import meetme.users.UserInterest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
class InterestsResource {

    @Autowired
    lateinit var userInterestsService: InterestsService;

    @PutMapping("/interests")
    @CrossOrigin
    fun addInterest(@RequestBody userInterest: UserInterest): UserInterest = userInterestsService!!.saveInterest(userInterest)


    @GetMapping("/interests")
    @CrossOrigin
    fun getInterests(): List<Pair<String, List<UserInterest>>> = userInterestsService!!.findAllInterests()

}