package com.milunas.weaviate

import com.google.gson.internal.LinkedTreeMap
import io.weaviate.client.WeaviateClient
import io.weaviate.client.v1.graphql.query.argument.NearImageArgument
import io.weaviate.client.v1.graphql.query.fields.Field
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import java.util.*
import kotlin.collections.ArrayList

@RestController
class WeaviateImageController(
    private val base64Encoder: Base64.Encoder,
    private val base64Decoder: Base64.Decoder,
    private val weaviateClient: WeaviateClient,
) {

    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

    @PostMapping(
        path = ["/images"],
        produces = [MediaType.IMAGE_JPEG_VALUE],
    )
    fun findSimilarFor(
        @RequestParam("file") file: MultipartFile,
    ): ByteArray? {
        val base64File = base64Encoder.encodeToString(file.bytes)

        val result = weaviateClient.graphQL().get()
            .withClassName("Groceries")
            .withNearImage(
                NearImageArgument.builder().image(base64File).build()
            )
            .withLimit(1)
            .withFields(
                Field.builder().name("image").build()
            )
            .run()

        result.error?.let { logger.warn("${it.statusCode}: ${it.messages}") }

        // Definitely there is place for improvement
        val resultBase64File = ((((((result.result?.data
                as? LinkedTreeMap<*, *>)?.values?.firstOrNull()
                as? LinkedTreeMap<*, *>)?.values?.firstOrNull())
                as? ArrayList<*>)?.firstOrNull()
                as? LinkedTreeMap<*, *>)
            ?.values?.firstOrNull()) as String?

        return resultBase64File?.let { base64Decoder.decode(it) }
    }

    @PostMapping("/import")
    fun importImage(
        @RequestParam("file") file: MultipartFile,
    ) {
        val base64File = base64Encoder.encodeToString(file.bytes)

        val result = weaviateClient.data().creator()
            .withClassName("Groceries")
            .withProperties(
                mapOf(
                    Pair("image", base64File),
                )
            )
            .run()

        result.error?.let { logger.warn("${it.statusCode}: ${it.messages}") }
    }
}