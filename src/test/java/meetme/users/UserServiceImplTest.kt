package meetme.users

import com.google.gson.Gson
import com.sun.tools.doclets.internal.toolkit.util.DocPath
import org.apache.commons.beanutils.PropertyUtils
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.springframework.boot.test.mock.mockito.MockBean
import java.time.LocalDate
import java.time.LocalDateTime
import kotlin.reflect.full.memberProperties

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

    var user1: User = User(
            userId = "CTiWEknLbHdK7NBrZSsXiVviVLU2",
            lastSeen = LocalDateTime.now(),
            name = "Apurv K",
            gender = "m",
            sexualOrientation = "straight",
            interests = emptyList(),
            userName = "appugadu",
            profileTextDescription = "hello",
            latitude = 28.7588833,
            longitude = -81.3178446,
            city = "Lake Mary",
            freeMsgs = 6,
            birthday = LocalDate.now(),
            profileViews = 26,
            feet = 5,
            inches = 6,
            bodyType = "athletic",
            ethnicity = "asian",
            verified = true,
            weight = 130)


    @Test
    fun shouldPrintAllValues() = Assert.assertEquals(user1::class.memberProperties.size - 1, userServiceImpl.getUserKV(user1).size)

    @Test
    fun shouldReturnOnlyPassedValue() = Assert.assertEquals(userServiceImpl.getUserKV(user1, "lastSeen").size, 1)

    @Test
    fun itShouldReturnValueForProfileViews() {
        user1.profileViews = 0
        Assert.assertEquals(userServiceImpl.getUserKV(user1).size, user1::class.memberProperties.size - 2)
        user1.profileViews = 12
    }


    @Test
    fun itShouldCopyNonNullValues() {

        var user2: User = User(
                userId = "CTiWEknLbHdK7NBrZSsXiVviVLU2",
                lastSeen = LocalDateTime.now(),
                name = "user2",
                gender = "m",
                sexualOrientation = "straight",
                interests = emptyList(),
                userName = "appugadu",
                profileTextDescription = "hello",
                latitude = 28.7588833,
                longitude = -81.3178446,
                city = "Lake Mary",
                freeMsgs = 6,
                birthday = LocalDate.now(),
                profileViews = 26,
                feet = 5,
                inches = 6,
                bodyType = "athletic",
                ethnicity = "asian",
                verified = true,
                weight = 170)

        PropertyUtils.describe(user2)
                .filter { it.value != null && it.key != "name" }
//                .filter({ e -> !e.getKey().equals("class") })
                .forEach {
                    try {
                        PropertyUtils.setProperty(user1, it.key.toString(), it.value)
                    } catch (e: Exception) {
                        // Error setting property ...;
                    }
                }

        println("""user1==${user1.weight} & name ===${user1.name}""")
        print("""user2==${user2.weight}& name ===${user2.name}""")


    }

    @Test
    fun testGetKV() {

//        val json = "{\n" +
//                "    \"userId\": \"CTiWEknLbHdK7NBrZSsXiVviVLU2\",\n" +
//                "    \"lastSeen\": 1565534238409,\n" +
//                "    \"name\": \"Apurv K\",\n" +
//                "    \"gender\": \"m\",\n" +
//                "    \"sexualOrientation\": null,\n" +
//                "    \"interests\": null,\n" +
//                "    \"userName\": \"appugadu\",\n" +
//                "    \"profileTextDescription\": \"hello world this is me the man\",\n" +
//                "    \"latitude\": 28.7588833,\n" +
//                "    \"longitude\": -81.3178446,\n" +
//                "    \"city\": \"Lake Mary\",\n" +
//                "    \"freeMsgs\": 6,\n" +
//                "    \"birthDay\": null,\n" +
//                "    \"profileViews\": 2,\n" +
//                "    \"birthday\": \"2019-08-11T14:37:17.455Z\",\n" +
//                "    \"userInterests\": [\n" +
//                "        {\n" +
//                "            \"code\": \"ro\",\n" +
//                "            \"categoryCd\": \"one\",\n" +
//                "            \"categoryDescription\": \"one desc\",\n" +
//                "            \"description\": \"ronkey\",\n" +
//                "            \"selected\": true\n" +
//                "        },\n" +
//                "        {\n" +
//                "            \"code\": \"do\",\n" +
//                "            \"categoryCd\": \"two\",\n" +
//                "            \"categoryDescription\": \"two desc\",\n" +
//                "            \"description\": \"donkey\",\n" +
//                "            \"selected\": true\n" +
//                "        }\n" +
//                "    ]\n" +
//                "}"
//        var user: User = Gson().fromJson(json, User::class.java)
//
//        var userList: Map<String, Any?> = userServiceImpl.getUserKV(user)
//        print(userList.size)
//
//        println(userList)


    }

}