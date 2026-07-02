package com.semanticpilot.models

import kotlinx.serialization.Serializable

@Serializable
data class CompletionResponse(
    val completion: String
)