package com.jblog.molog.exception

import com.jblog.molog.exception.BaseResponseCode

class BaseException(baseResponseCode: BaseResponseCode): RuntimeException() {
    private val baseResponseCode: BaseResponseCode = baseResponseCode
}