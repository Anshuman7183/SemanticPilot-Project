package com.semanticpilot.completion

import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.editor.Editor
import com.semanticpilot.models.CompletionResponse

object CompletionInserter {

    fun insert(
        editor: Editor,
        response: CompletionResponse
    ) {

        WriteCommandAction.runWriteCommandAction(
            editor.project
        ) {

            editor.document.insertString(
                editor.caretModel.offset,
                response.completion
            )
        }
    }
}