package com.semanticpilot.completion

import com.intellij.openapi.editor.EditorFactory
import com.intellij.openapi.project.Project

object EditorListener {

    fun initialize(
        project: Project
    ) {

        val editors =
            EditorFactory.getInstance()
                .allEditors

        editors.forEach {

            it.document.addDocumentListener(
                TypingListener(it)
            )
        }
    }
}