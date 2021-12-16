package com.jblog.molog.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
@EnableWebMvc
class WebConfig: WebMvcConfigurer {
    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowedOrigins("http://localhost:8080", "http://localhost:3000") // 원하는 도메인만 허용
            .allowedHeaders("*") // 원하는 헤더만 허용
            .allowedMethods("*") // 원하는 메소드만 허용 (GET, POST, ...)
            .allowCredentials(false) // 쿠키 요청 허용 (true -> 보안상 이슈 발생 가능)
    }
}