package com.jblog.molog.service

import com.jblog.molog.dto.UserLoginReq
import com.jblog.molog.dto.UserLoginRes
import com.jblog.molog.dto.UserRegisterReq
import com.jblog.molog.dto.UserRegisterRes
import com.jblog.molog.entity.User
import com.jblog.molog.exception.BaseException
import com.jblog.molog.exception.BaseResponseCode
import com.jblog.molog.jpa.UserRepository
import com.jblog.molog.utils.JwtTokenProvider
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository, private val jwtTokenProvider: JwtTokenProvider) {

    fun findUser(email: String): User {
        return userRepository.findByEmail(email).orElseThrow{BaseException(BaseResponseCode.USER_NOT_FOUND)}
    }

    fun existsUser(email: String): Boolean {
        return userRepository.existsByEmail(email).orElseThrow{BaseException(BaseResponseCode.DUPLICATE_EMAIL)}
    }

    fun createUser(userRegisterReq: UserRegisterReq): UserRegisterRes {
        val user = User(userRegisterReq.name, userRegisterReq.email, userRegisterReq.password)
        userRepository.save(user)

        return UserRegisterRes(user.id, user.email)
    }

    fun login(userLoginReq: UserLoginReq): UserLoginRes {
        val token: String = jwtTokenProvider.createToken(userLoginReq.email)

        return UserLoginRes(HttpStatus.OK, token)
    }
}