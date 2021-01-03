package com.example.secured_app.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/management")
class ManagementController {
    @GetMapping("index")
    fun index(): String = "management/index"
}