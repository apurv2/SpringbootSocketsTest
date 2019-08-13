package meetme.users

import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, String> {
    fun findByUserId(userId : String) : List<User>
}
