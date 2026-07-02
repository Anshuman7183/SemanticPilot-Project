package com.semanticpilot.services

import com.intellij.openapi.editor.Editor
import com.semanticpilot.models.CompletionResponse
import com.semanticpilot.network.ApiClient

class CompletionService {

    private val contextService = ContextService()

    suspend fun getCompletion(
        editor: Editor
    ): CompletionResponse {

        println("STEP 1")

        val request =
            contextService.buildRequest(editor)

        println("STEP 2")

        val response =
            ApiClient
                .completionApi
                .getCompletion(request)

        println("STEP 3")

        return response
    }
}