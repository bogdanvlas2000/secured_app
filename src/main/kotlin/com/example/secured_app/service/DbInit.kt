package com.example.secured_app.service

import com.example.secured_app.model.User
import com.example.secured_app.repository.UserRepository
import org.springframework.boot.CommandLineRunner
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class DbInit(val userRepository: UserRepository, val passwordEncoder: PasswordEncoder) : CommandLineRunner {
    override fun run(vararg args: String?) {
        val admin = User("admin", passwordEncoder.encode("admin"), "ADMIN", "ACCESS_TEST1 ACCESS_TEST2")
        val manager = User("manager", passwordEncoder.encode("manager"), "MANAGER", "ACCESS_TEST1")
        val user = User("user", passwordEncoder.encode("user"), "USER", "")

        userRepository.saveAll(listOf(admin, manager, user))
        println("Data base init done ------------------ 3 users added!!!!!")
    }

}