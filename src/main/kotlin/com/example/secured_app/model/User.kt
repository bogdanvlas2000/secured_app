package com.example.secured_app.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
class User(username: String, password: String, roles: String, permissions: String) {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long = 0

    var username: String = username

    var password: String = password

    var active: Int = 1

    var roles: String = roles

    var permissions: String = permissions

    fun getRolesList(): List<String> = roles.split(" ").toList()

    fun getPermissionsList(): List<String> = permissions.split(" ").toList()
}