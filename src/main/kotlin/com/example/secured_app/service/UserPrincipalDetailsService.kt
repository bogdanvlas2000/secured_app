package com.example.secured_app.service

import com.example.secured_app.model.User
import com.example.secured_app.model.UserPrincipal
import com.example.secured_app.repository.RoleRepository
import com.example.secured_app.repository.UserRepository
import jdk.nashorn.internal.objects.NativeRegExp
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserPrincipalDetailsService(val userRepository: UserRepository) :
    UserDetailsService {


    override fun loadUserByUsername(username: String?): UserDetails {
        if (username != null) {
            val user: User = userRepository.findByUsername(username)

            val encoder = BCryptPasswordEncoder()

            println(user)

            val userPrincipal = UserPrincipal(user)
            return userPrincipal

        } else {
            println("No user founded!")
            throw UsernameNotFoundException(username)
        }
    }
}