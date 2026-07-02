package com.semanticpilot.network

import com.semanticpilot.models.CompletionRequest
import com.semanticpilot.models.CompletionResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class CompletionApi {

    private val client = HttpClient(CIO) {

        install(ContentNegotiation) {
            json(
                Json {
                    ignoreUnknownKeys = true
                    prettyPrint = true
                    isLenient = true
                }
            )
        }

        install(HttpTimeout) {
            requestTimeoutMillis = 30000
            connectTimeoutMillis = 10000
            socketTimeoutMillis = 30000
        }

        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    println(message)
                }
            }
            level = LogLevel.INFO
        }
    }

    suspend fun getCompletion(
        request: CompletionRequest
    ): CompletionResponse {

        println("====== Completion API Called ======")

        println("PREFIX:")
        println(request.prefix)

        println("SUFFIX:")
        println(request.suffix)

        println("LANGUAGE:")
        println(request.language)

        try {

            val response: CompletionResponse =
                client.post(
                    "http://127.0.0.1:8000/completion"
                ) {

                    header(
                        HttpHeaders.ContentType,
                        ContentType.Application.Json.toString()
                    )

                    setBody(request)
                }.body()

            println("====== COMPLETION RECEIVED ======")
            println(response.completion)

            return response

        } catch (e: Exception) {

            println("====== API ERROR ======")
            e.printStackTrace()

            return CompletionResponse(
                completion = ""
            )
        }
    }
}
