package com.jblog.molog.controller

import com.jblog.molog.exception.BaseException
import com.jblog.molog.dto.UserLoginReq
import com.jblog.molog.dto.UserLoginRes
import com.jblog.molog.dto.UserRegisterReq
import com.jblog.molog.dto.UserRegisterRes
import com.jblog.molog.exception.BaseResponseCode
import com.jblog.molog.service.UserService
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.util.*
import javax.servlet.http.HttpServletResponse

@RestController
class UserController(private val passwordEncoder: PasswordEncoder, private val userService: UserService) {
    @PostMapping("/register")
    fun register(@RequestBody userRegisterReq: UserRegisterReq): ResponseEntity<UserRegisterRes> {

        if(userService.existsUser(userRegisterReq.email)) {
            throw BaseException(BaseResponseCode.BAD_REQUEST)
        }

        return ResponseEntity.ok(userService.createUser(userRegisterReq))
    }

    @PostMapping("/login")
    fun login(@RequestBody userLoginReq: UserLoginReq): ResponseEntity<UserLoginRes> {
        if(!userService.existsUser(userLoginReq.email)) {
            throw BaseException(BaseResponseCode.USER_NOT_FOUND)
        }

        val userLoginRes: UserLoginRes = userService.login(userLoginReq)
//        return ResponseEntity.ok(userService.login(userLoginReq))
        return ResponseEntity.ok(userLoginRes)
    }

}