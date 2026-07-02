package com.semanticpilot.context

import com.intellij.openapi.editor.Editor

object SelectionContextExtractor {

    fun extract(editor: Editor): String? {

        val selectionModel = editor.selectionModel

        return if (selectionModel.hasSelection())
            selectionModel.selectedText
        else
            null
    }
}