package com.semanticpilot.models

import kotlinx.serialization.Serializable

@Serializable
data class CompletionRequest(
    val prefix: String,
    val suffix: String,
    val selectedText: String,
    val language: String,
    val fileContent: String,
    val semanticContext: SemanticContext? = null
)
