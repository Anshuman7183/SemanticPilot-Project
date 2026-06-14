package com.semanticpilot.services

import com.intellij.openapi.editor.Editor
import com.semanticpilot.context.CursorContextExtractor
import com.semanticpilot.models.CompletionRequest

class ContextService {

    private val extractor = CursorContextExtractor()

    fun buildRequest(editor: Editor): CompletionRequest {
        return extractor.extract(editor)
    }
}