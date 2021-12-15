package com.jblog.molog.dto

import org.springframework.http.HttpStatus

class UserLoginRes(private val status: HttpStatus, private val token: String?) {
}