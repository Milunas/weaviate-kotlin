package com.milunas.weaviate

import io.weaviate.client.WeaviateClient
import io.weaviate.client.v1.schema.model.WeaviateClass
import jakarta.annotation.PostConstruct
import org.springframework.context.annotation.Configuration

//@Configuration
class WeaviateCreateSchema(
    private val weaviateClient: WeaviateClient,
) {

/*
    @PostConstruct
    fun createSchema() = weaviateClient.schema().classCreator().withClass(weaviateClass())

    private fun weaviateClass() = WeaviateClass.builder()
        .className("")
        .vectorizer("")
        .moduleConfig(???) // TODO - moduleConfig is Any
        .build()
*/

}