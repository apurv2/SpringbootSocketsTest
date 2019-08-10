package meetme.interests

import meetme.users.UserInterest
import org.springframework.data.repository.CrudRepository

interface UserInterestRepository : CrudRepository<UserInterest, String> {
}
