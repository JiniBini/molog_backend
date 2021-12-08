package com.jblog.molog

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MologApplication

fun main(args: Array<String>) {
    runApplication<MologApplication>(*args)
}
