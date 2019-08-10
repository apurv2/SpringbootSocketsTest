package meetme.interests

import meetme.users.UserInterest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class InterestsServiceImpl : InterestsService {

    @Autowired
    internal lateinit var userInterestRepository: UserInterestRepository

    override fun findAllInterests() = userInterestRepository.findAll()!!.groupBy { it.categoryCd }.toList()

    override fun saveInterest(userInterest: UserInterest): UserInterest = userInterestRepository.save(userInterest)

}