package com.jblog.molog.service

import com.jblog.molog.exception.BaseException
import com.jblog.molog.exception.BaseResponseCode
import com.jblog.molog.jpa.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserDetailService(private val userRepository: UserRepository): UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        return userRepository.findByEmail(username).orElseThrow{ BaseException(BaseResponseCode.USER_NOT_FOUND) }
    }
}