package com.semanticpilot.context

import com.intellij.openapi.editor.Editor

object CursorContextExtractor {

    fun extractPrefix(editor: Editor): String {

        val document = editor.document

        val offset = editor.caretModel.offset

        return document.text.substring(0, offset)
    }

    fun extractSuffix(editor: Editor): String {

        val document = editor.document

        val offset = editor.caretModel.offset

        return document.text.substring(offset)
    }
}