package com.milunas.weaviate

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WeaviateApplication

fun main(args: Array<String>) {
	runApplication<WeaviateApplication>(*args)
}
