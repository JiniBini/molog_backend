package com.jblog.molog.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class testController {

    @GetMapping("/get")
    fun test(): String {
        return "hello"
    }
}