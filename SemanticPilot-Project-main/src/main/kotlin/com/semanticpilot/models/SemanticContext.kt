package com.semanticpilot.models

import kotlinx.serialization.Serializable

@Serializable
data class SemanticContext(
    val functionName: String?,
    val className: String?,
    val imports: List<String>,
    val language: String,
    val filePath: String?,
    val symbolName: String?
)
