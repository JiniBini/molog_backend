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
import org.springframework.http.HttpStatus
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository, private val passwordEncoder: PasswordEncoder, private val jwtTokenProvider: JwtTokenProvider) {
    fun existsUser(email: String): Boolean {
        return userRepository.existsByEmail(email).orElseThrow{BaseException(BaseResponseCode.DUPLICATE_EMAIL)}
    }

    fun createUser(userRegisterReq: UserRegisterReq): UserRegisterRes {
        var password: String = passwordEncoder.encode(userRegisterReq.password)
        var user: User
        try {
            user = userRepository.save(User(userRegisterReq.name, userRegisterReq.email, password))
        } catch (e: Exception) {
            throw BaseException(BaseResponseCode.FAILED_TO_SAVE_USER)
        }

        return UserRegisterRes(user.id, user.email)
    }

    fun login(userLoginReq: UserLoginReq): UserLoginRes {
        var user: User = userRepository.findByEmail(userLoginReq.email).orElseThrow{BaseException(BaseResponseCode.USER_NOT_FOUND)}
        if(!passwordEncoder.matches(userLoginReq.password, user.password)) {
            throw BaseException(BaseResponseCode.INVALID_PASSWORD)
        }
        var token: String? = jwtTokenProvider.createToken(userLoginReq.email)

        print(token)
        return UserLoginRes(HttpStatus.OK, token)
    }
}