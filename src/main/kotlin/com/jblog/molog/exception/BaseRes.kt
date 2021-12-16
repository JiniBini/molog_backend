package com.jblog.molog.exception

import org.springframework.http.HttpStatus

class BaseRes(public val code: HttpStatus, public val message: String) {
    //
}