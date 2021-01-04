package com.example.secured_app.controller

import com.example.secured_app.model.UserDto
import com.example.secured_app.repository.UserRepository
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.context.request.WebRequest

@Controller
@RequestMapping("/")
class HomeController(val userRepository: UserRepository) {
    @GetMapping("/index")
    fun index(): String = "index"

    @GetMapping("/login")
    fun login(): String = "login"

    @GetMapping("/register")
    fun register(model: Model): String {
        val user = UserDto()
        model.addAttribute("user", user)
        return "register"
    }

    @PostMapping("/signup")
    fun signup(user: UserDto, model: Model): String {
        println(user)
        return "redirect:/index"
    }

}