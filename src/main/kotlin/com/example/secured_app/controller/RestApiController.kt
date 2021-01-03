package com.example.secured_app.controller

import com.example.secured_app.model.User
import com.example.secured_app.repository.UserRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/public")
class PublicRestApiController(val userRepository: UserRepository) {
    @GetMapping("/test1")
    fun test1(): String = "First test!"

    @GetMapping("/test2")
    fun test2(): String = "/Second test!"

    @GetMapping("/users")
    fun users(): List<User> = userRepository.findAll()

}