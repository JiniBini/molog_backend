package com.jblog.molog.exception

import com.jblog.molog.exception.BaseResponseCode

class BaseException(baseResponseCode: BaseResponseCode): RuntimeException() {
    public val baseResponseCode: BaseResponseCode = baseResponseCode
}