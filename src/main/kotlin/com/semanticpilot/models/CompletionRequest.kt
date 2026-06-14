package com.semanticpilot.models

data class CompletionRequest(
    val prefix: String,
    val suffix: String,
    val language: String
)