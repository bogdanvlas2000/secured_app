package com.example.secured_app.repository

import com.example.secured_app.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<User, Long> {
    fun findByUsername(username: String): User

    fun findByEmail(email: String): User
}