package meetme.users

import com.google.gson.Gson
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.springframework.boot.test.mock.mockito.MockBean

class UserServiceImplTest {

    @Before
    fun setUp() {
    }

    @MockBean
    private lateinit var userServiceImpl: UserServiceImpl;

    @Before
    fun beforeTests() {
        userServiceImpl = UserServiceImpl();
    }

//    @Test
//    fun testIncrement() {
//        val user: User? = User("aaa", 1234, null, null, null, null, null, null, null, null, null, null, null, 1);
//
////        println(user?.profileViews)
////        user?.profileViews = user?.profileViews.inc()
////        println(user?.profileViews)
//    }
//
//    @Test
//    fun dummyTest() {
//
//        var excludedColumn: String? = "sir";
//        var currentColumn: String? = "sir";
//
//        var hello: Boolean = excludedColumn?.equals(currentColumn) ?: true
//
////        var hello: Boolean = excludedColumn ?: excludedColumn == currentColumn
//
//        print(hello)
//
//    }
//
//
//    @Test
//    fun updateLastLogin() {
//        val user: User? = User("aaa", 1234, null, null, null, null, null, null, null, null, null, null, null, 1);
//        user!!::class.memberProperties
//                .filter { isFieldAccessible(it) }
//                .map { it as KProperty1<Any, *> }
//                .filter { it.get(user) != null }
//                .map { it.name to it.get(user) }.toMap()
//                .forEach { println("${it.key} ->" + it.value) }
//    }
//
//    fun isFieldAccessible(property: KProperty1<*, *>): Boolean {
//        return property.javaGetter?.modifiers?.let { !Modifier.isPrivate(it) } ?: false
//    }

    @Test
    fun testgetUserKVAllValues() {

        var user1: User = User(userId = "CTiWEknLbHdK7NBrZSsXiVviVLU2",
                lastSeen = 1565476099251,
                name = "Apurv K",
                gender = "m",
                sexualOrientation = null,
                interests = null,
                userName = "appugadu",
                profileTextDescription = null,
                latitude = 28.7588833,
                longitude = -81.3178446,
                city = "Lake Mary",
                freeMsgs = 6,
                birthDay = null,
                profileViews = 26)

        var userList: Map<String, Any?> = userServiceImpl.getUserKV(user1)
        Assert.assertEquals(9, userList.size)
    }

    @Test
    fun testgetUserKVParticularString() {

        var user1: User = User(userId = "CTiWEknLbHdK7NBrZSsXiVviVLU2",
                lastSeen = 1565476099251,
                name = "Apurv K",
                gender = "m",
                sexualOrientation = null,
                interests = null,
                userName = "appugadu",
                profileTextDescription = null,
                latitude = 28.7588833,
                longitude = -81.3178446,
                city = "Lake Mary",
                freeMsgs = 6,
                birthDay = null,
                profileViews = 26)

        var userList: Map<String, Any?> = userServiceImpl.getUserKV(user1, "lastSeen")
        Assert.assertEquals(userList.size, 1)
    }


    @Test
    fun testGetKV() {

        val json = "{\n" +
                "    \"userId\": \"CTiWEknLbHdK7NBrZSsXiVviVLU2\",\n" +
                "    \"lastSeen\": 1565534238409,\n" +
                "    \"name\": \"Apurv K\",\n" +
                "    \"gender\": \"m\",\n" +
                "    \"sexualOrientation\": null,\n" +
                "    \"interests\": null,\n" +
                "    \"userName\": \"appugadu\",\n" +
                "    \"profileTextDescription\": \"hello world this is me the man\",\n" +
                "    \"latitude\": 28.7588833,\n" +
                "    \"longitude\": -81.3178446,\n" +
                "    \"city\": \"Lake Mary\",\n" +
                "    \"freeMsgs\": 6,\n" +
                "    \"birthDay\": null,\n" +
                "    \"profileViews\": 2,\n" +
                "    \"birthday\": \"2019-08-11T14:37:17.455Z\",\n" +
                "    \"userInterests\": [\n" +
                "        {\n" +
                "            \"code\": \"ro\",\n" +
                "            \"categoryCd\": \"one\",\n" +
                "            \"categoryDescription\": \"one desc\",\n" +
                "            \"description\": \"ronkey\",\n" +
                "            \"selected\": true\n" +
                "        },\n" +
                "        {\n" +
                "            \"code\": \"do\",\n" +
                "            \"categoryCd\": \"two\",\n" +
                "            \"categoryDescription\": \"two desc\",\n" +
                "            \"description\": \"donkey\",\n" +
                "            \"selected\": true\n" +
                "        }\n" +
                "    ]\n" +
                "}"
        var user: User = Gson().fromJson(json, User::class.java)

        var userList: Map<String, Any?> = userServiceImpl.getUserKV(user)
        print(userList.size)

        println(userList)




    }

}