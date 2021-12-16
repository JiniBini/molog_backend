package com.jblog.molog.controller

import com.jblog.molog.exception.BaseException
import com.jblog.molog.dto.UserLoginReq
import com.jblog.molog.dto.UserLoginRes
import com.jblog.molog.dto.UserRegisterReq
import com.jblog.molog.dto.UserRegisterRes
import com.jblog.molog.entity.User
import com.jblog.molog.exception.BaseResponseCode
import com.jblog.molog.service.UserService
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.util.*
import javax.servlet.http.HttpServletResponse

@RestController
class UserController(private val userService: UserService, private val passwordEncoder: PasswordEncoder) {

    @PostMapping("/register")
    fun register(@RequestBody userRegisterReq: UserRegisterReq): ResponseEntity<UserRegisterRes> {

        if(userService.existsUser(userRegisterReq.email)) {
            throw BaseException(BaseResponseCode.DUPLICATE_EMAIL)
        }
        userRegisterReq.password = passwordEncoder.encode(userRegisterReq.password)

        return ResponseEntity.ok(userService.createUser(userRegisterReq))
    }

    @PostMapping("/login")
    fun login(@RequestBody userLoginReq: UserLoginReq): ResponseEntity<UserLoginRes> {
        if(!userService.existsUser(userLoginReq.email)) {
            throw BaseException(BaseResponseCode.USER_NOT_FOUND)
        }

        val user: User = userService.findUser(userLoginReq.email)

        if(!passwordEncoder.matches(userLoginReq.password, user.password)) {
            throw BaseException(BaseResponseCode.INVALID_PASSWORD)
        }

        return ResponseEntity.ok(userService.login(userLoginReq))
    }

}