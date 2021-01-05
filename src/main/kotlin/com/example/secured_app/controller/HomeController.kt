package com.example.secured_app.controller

import com.example.secured_app.model.User
import com.example.secured_app.model.UserDto
import com.example.secured_app.repository.UserRepository
import com.example.secured_app.service.EmailAlreadyExists
import com.example.secured_app.service.UserPrincipalDetailsService
import com.example.secured_app.service.UsernameAlreadyExists
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import org.springframework.web.context.request.WebRequest
import javax.servlet.http.HttpServletRequest
import org.springframework.security.core.context.SecurityContextHolder

import org.springframework.security.web.authentication.WebAuthenticationDetails

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.validation.Errors
import org.springframework.web.servlet.mvc.support.RedirectAttributes


@Controller
@RequestMapping("/")
class HomeController(
    val userPrincipalDetailsService: UserPrincipalDetailsService,
    val authenticationManager: AuthenticationManager
) {
    @GetMapping("/index")
    fun index(): String = "index"

    @GetMapping("/login")
    fun login(): String = "login"

    @GetMapping("/register")
    fun register(
        model: Model,
        @ModelAttribute(name = "errorUsername") errorUsername: String,
        @ModelAttribute(name = "errorEmail") errorEmail: String
    ): String {
        if (!model.containsAttribute("user")) {
            val user = UserDto()
            model.addAttribute("user", user)
        }
        return "register"
    }

    @PostMapping("/signup")
    fun registerNewUser(
        request: HttpServletRequest,
        userDto: UserDto,
        redirectAttributes: RedirectAttributes
    ): String {
        try {
            var user: User = userPrincipalDetailsService.registerNewUserAccount(userDto)
            println(user)
            authWithAuthManager(request, userDto.username, userDto.password)
        } catch (e: UsernameAlreadyExists) {
            ////////TRY TO REDIRECT WITH ERROR
            println("username ${userDto.username} already exists!")
            redirectAttributes.addAttribute("errorUsername", "Username ${userDto.username} exists!")
            redirectAttributes.addFlashAttribute("user", userDto)
            return "redirect:/register"
        } catch (e: EmailAlreadyExists) {
            println("email ${userDto.email} already exists!")
            redirectAttributes.addAttribute("errorEmail", "Email ${userDto.email} exists!")
            redirectAttributes.addFlashAttribute("user", userDto)
            return "redirect:/register"
        } catch (e: Exception) {
            println("Exception while login")
            e.printStackTrace()
        }
        return "redirect:/index"
    }

    private fun authWithAuthManager(request: HttpServletRequest, username: String, password: String) {
        val authToken = UsernamePasswordAuthenticationToken(username, password)
        authToken.details = WebAuthenticationDetails(request)
        val authentication: Authentication = authenticationManager.authenticate(authToken)
        SecurityContextHolder.getContext().authentication = authentication
    }
}