package com.semanticpilot.context

import com.intellij.openapi.editor.Editor

object FileContextExtractor {

    fun extract(editor: Editor): String {

        return editor.document.text
    }
}