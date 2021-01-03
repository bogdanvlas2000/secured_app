package com.example.secured_app.model

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UserPrincipal(val user: User) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        val authorities = mutableListOf<GrantedAuthority>()
        user.getRolesList().forEach {
            if (it.isNotEmpty()) {
                val authority = SimpleGrantedAuthority("ROLE_" + it)
                authorities.add(authority)
            }
        }
        user.getPermissionsList().forEach {
            if (it.isNotEmpty()) {
                val authority = SimpleGrantedAuthority(it)
                authorities.add(authority)
            }
        }
        return authorities
    }

    override fun getPassword(): String = user.password

    override fun getUsername(): String = user.username

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = user.active == 1
}