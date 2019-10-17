package meetme.users

import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : PagingAndSortingRepository<User, String> {
    fun findByUserId(userId: String): List<User>
    fun findFirst10ByFreeMsgs() : List<User>
}
