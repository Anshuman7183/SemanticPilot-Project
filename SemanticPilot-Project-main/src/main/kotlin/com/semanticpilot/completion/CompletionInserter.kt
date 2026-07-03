package com.semanticpilot.completion

import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.editor.Editor
import com.semanticpilot.models.CompletionResponse

object CompletionInserter {

    fun insert(
        editor: Editor,
        response: CompletionResponse
    ) {

        insert(
            editor,
            response.completion
        )
    }

    fun insert(
        editor: Editor,
        completion: String
    ) {

        WriteCommandAction.runWriteCommandAction(
            editor.project
        ) {

            editor.document.insertString(
                editor.caretModel.offset,
                completion
            )
        }
    }
}
