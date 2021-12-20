package com.jblog.molog

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@EnableJpaAuditing
@SpringBootApplication
class MologApplication

fun main(args: Array<String>) {
    runApplication<MologApplication>(*args)
}
