package meetme.interests

import meetme.users.UserInterest
import org.springframework.stereotype.Service

interface InterestsService {

    fun saveInterest(userInterest: UserInterest): UserInterest

    fun findAllInterests(): List<Pair<String, List<UserInterest>>>

}
