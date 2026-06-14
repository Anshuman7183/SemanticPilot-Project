package com.semanticpilot.context

import com.intellij.openapi.editor.Editor
import com.semanticpilot.models.CompletionRequest

class CursorContextExtractor {

    fun extract(editor: Editor): CompletionRequest {

        val document = editor.document
        val text = document.text

        val offset = editor.caretModel.offset

        val prefix = text.substring(0, offset)
        val suffix = text.substring(offset)

        return CompletionRequest(
            prefix = prefix,
            suffix = suffix,
            language = "unknown"
        )
    }
}