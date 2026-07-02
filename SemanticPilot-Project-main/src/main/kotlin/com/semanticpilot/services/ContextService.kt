package com.semanticpilot.services

import com.intellij.openapi.editor.Editor
import com.semanticpilot.context.CursorContextExtractor
import com.semanticpilot.context.FileContextExtractor
import com.semanticpilot.context.LanguageDetector
import com.semanticpilot.context.SelectionContextExtractor
import com.semanticpilot.models.CompletionRequest

class ContextService {

    fun buildRequest(editor: Editor): CompletionRequest {

        return CompletionRequest(

            prefix = CursorContextExtractor.extractPrefix(editor),

            suffix = CursorContextExtractor.extractSuffix(editor),

            selectedText = SelectionContextExtractor.extract(editor) ?: "",

            language = LanguageDetector.detect(editor),

            fileContent = FileContextExtractor.extract(editor)
        )
    }
}
