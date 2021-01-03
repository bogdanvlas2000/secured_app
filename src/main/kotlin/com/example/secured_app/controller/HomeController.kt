package com.example.secured_app.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/")
class HomeController {
    @GetMapping("/index")
    fun index(): String = "index"

    @GetMapping("/login")
    fun login(): String = "login"

}