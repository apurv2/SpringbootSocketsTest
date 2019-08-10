package com.example.demo

import meetme.users.User
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class DemoApplicationTests {

    @Test
    fun contextLoads() {

        User::class.java.methods.forEach(::println)

    }

}
