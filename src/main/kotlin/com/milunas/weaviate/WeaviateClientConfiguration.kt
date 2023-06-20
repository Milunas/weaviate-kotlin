package com.milunas.weaviate

import io.weaviate.client.Config
import io.weaviate.client.WeaviateClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class WeaviateClientConfiguration {

    @Bean
    fun weaviateClient() = WeaviateClient(Config("http", "localhost:8080"))
}