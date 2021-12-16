package com.jblog.molog.dto

import lombok.AllArgsConstructor
import lombok.Getter
import org.springframework.http.HttpStatus

@Getter
@AllArgsConstructor
class UserLoginRes(public val status: HttpStatus, public val token: String?) {
}