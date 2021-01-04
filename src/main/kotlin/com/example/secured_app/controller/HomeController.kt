package com.example.secured_app.controller

import com.example.secured_app.model.User
import com.example.secured_app.model.UserDto
import com.example.secured_app.repository.UserRepository
import com.example.secured_app.service.EmailAlreadyExists
import com.example.secured_app.service.UserPrincipalDetailsService
import com.example.secured_app.service.UsernameAlreadyExists
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.context.request.WebRequest

@Controller
@RequestMapping("/")
class HomeController(val userPrincipalDetailsService: UserPrincipalDetailsService) {
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
    fun registerNewUser(userDto: UserDto, model: Model): String {
        try {
            var user: User = userPrincipalDetailsService.registerNewUserAccount(userDto)


            println(user)
        } catch (e: UsernameAlreadyExists) {
            println("username ${userDto.username} already exists!")
        } catch (e: EmailAlreadyExists) {
            println("email ${userDto.email} already exists!")
        }
        return "redirect:/index"
    }

}