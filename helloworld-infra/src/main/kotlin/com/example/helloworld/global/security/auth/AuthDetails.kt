package com.example.helloworld.global.security.auth

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.*

class AuthDetails(
    private val name: String
): UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return Collections.emptyList();
    }

    override fun getPassword(): String? {
        return null
    }

    override fun getUsername(): String {
        return name
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}