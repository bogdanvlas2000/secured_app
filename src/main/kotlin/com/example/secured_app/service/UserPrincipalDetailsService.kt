package com.example.secured_app.service

import com.example.secured_app.model.Role
import com.example.secured_app.model.User
import com.example.secured_app.model.UserDto
import com.example.secured_app.model.UserPrincipal
import com.example.secured_app.repository.RoleRepository
import com.example.secured_app.repository.UserRepository
import jdk.nashorn.internal.objects.NativeRegExp
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserPrincipalDetailsService(val userRepository: UserRepository, val roleRepository: RoleRepository) :
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

    @Transactional
    fun registerNewUserAccount(userDto: UserDto): User {
        var usernameExists = try {
            userRepository.findByUsername(userDto.username)
            true
        } catch (e: EmptyResultDataAccessException) {
            false
        }

        var emailExists = try {
            userRepository.findByEmail(userDto.email)
            true
        } catch (e: Exception) {
            false
        }

        if (usernameExists) throw UsernameAlreadyExists(userDto.username)
        if (emailExists) throw EmailAlreadyExists(userDto.email)

        val role: Role = roleRepository.findByName("ROLE_USER")

        var passwordEncoder = BCryptPasswordEncoder()

        var user = User(
            userDto.username,
            passwordEncoder.encode(userDto.password),
            userDto.email,
            mutableListOf(role),
            1
        )
        try {
            userRepository.save(user)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return user
    }
}