package com.example.secured_app.model

class UserDto(username: String = "", password: String = "", confirmPassword: String = "", email: String = "") {
    val username: String = username
    val password: String = password
    val confirmPassword: String = confirmPassword
    val email: String = email

    override fun toString(): String {
        return "UserDto(username='$username', password='$password', confirmPassword='$confirmPassword',  email='$email'"
    }
}