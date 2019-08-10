package meetme.users

import org.junit.Before
import org.junit.Test
import java.lang.reflect.Modifier
import kotlin.reflect.KProperty1
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.javaGetter

class UserServiceImplTest {

    @Before
    fun setUp() {
    }

    @Test
    fun testIncrement() {
        val user: User? = User("aaa", 1234, null, null, null, null, null, null, null, null, null, null, null, 1);

        println(user?.profileViews)
        user?.profileViews = user?.profileViews?.inc()
        println(user?.profileViews)
    }


    @Test
    fun updateLastLogin() {
        val user: User? = User("aaa", 1234, null, null, null, null, null, null, null, null, null, null, null, 1);
        user!!::class.memberProperties
                .filter { isFieldAccessible(it) }
                .map { it as KProperty1<Any, *> }
                .filter { it.get(user) != null }
                .map { it.name to it.get(user) }.toMap()
                .forEach { println("${it.key} ->" + it.value) }
    }

    fun isFieldAccessible(property: KProperty1<*, *>): Boolean {
        return property.javaGetter?.modifiers?.let { !Modifier.isPrivate(it) } ?: false
    }
}