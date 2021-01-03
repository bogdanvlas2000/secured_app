package com.example.secured_app.config

import com.example.secured_app.service.UserPrincipalDetailsService
import org.springframework.context.annotation.Bean
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint

@EnableWebSecurity
class SecurityConfig(val detailsService: UserPrincipalDetailsService) :
    WebSecurityConfigurerAdapter() {
    override fun configure(http: HttpSecurity?) {
        if (http != null) {

            http.authorizeRequests()
                .antMatchers("/index.html").permitAll()
                .antMatchers("/profile/**").authenticated()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/management/**").hasAnyRole("ADMIN", "MANAGER")
                .antMatchers("/api/public/test1").hasAuthority("ACCESS_TEST1")
                .antMatchers("/api/public/test2").hasAuthority("ACCESS_TEST2")
                .antMatchers("/api/public/users").hasRole("ADMIN")
                .and()
                .formLogin()
                .loginProcessingUrl("/signin")
                .usernameParameter("my-username")
                .passwordParameter("my-password")
                .loginPage("/login").permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .and()
                .rememberMe()
                .rememberMeParameter("check-remember-me")
                .userDetailsService(detailsService)
                .key("mySecretKey")
                .tokenValiditySeconds(259200)
                .and()
                .exceptionHandling().accessDeniedPage("/login")
        }
    }

    override fun configure(auth: AuthenticationManagerBuilder?) {
        if (auth != null) {
            auth.authenticationProvider(authenticationProvider())
        }
    }


    @Bean
    fun authenticationProvider(): DaoAuthenticationProvider {
        val provider = DaoAuthenticationProvider()
        provider.setPasswordEncoder(passwordEncoder())
        provider.setUserDetailsService(detailsService)
        return provider
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()
}