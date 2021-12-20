package com.jblog.molog.dto.user

import lombok.AllArgsConstructor
import lombok.Getter
import org.springframework.http.HttpStatus

@Getter
@AllArgsConstructor
class UserLoginRes(public val status: HttpStatus, public val token: String?) {
    //
}