package com.milunas.weaviate

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.*

@Configuration
class Base64Configuration {

    @Bean
    fun base64Encoder(): Base64.Encoder = Base64.getEncoder()

    @Bean
    fun base64Decoder(): Base64.Decoder = Base64.getDecoder()
}