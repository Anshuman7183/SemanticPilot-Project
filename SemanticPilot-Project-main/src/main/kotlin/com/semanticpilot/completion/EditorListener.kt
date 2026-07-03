package com.semanticpilot.completion

import com.intellij.openapi.editor.EditorFactory
import com.intellij.openapi.editor.event.EditorFactoryEvent
import com.intellij.openapi.editor.event.EditorFactoryListener
import com.intellij.openapi.project.Project
import java.util.IdentityHashMap

object EditorListener {

    private val listeners =
        IdentityHashMap<com.intellij.openapi.editor.Editor, TypingListener>()

    fun initialize(
        project: Project
    ) {

        val editorFactory =
            EditorFactory.getInstance()

        editorFactory
            .allEditors
            .forEach {

                attach(
                    project,
                    it
                )
            }

        editorFactory.addEditorFactoryListener(
            object : EditorFactoryListener {

                override fun editorCreated(
                    event: EditorFactoryEvent
                ) {

                    attach(
                        project,
                        event.editor
                    )
                }

                override fun editorReleased(
                    event: EditorFactoryEvent
                ) {

                    detach(
                        event.editor
                    )
                }
            },
            project
        )
    }

    private fun attach(
        project: Project,
        editor: com.intellij.openapi.editor.Editor
    ) {

        if (editor.project != project) {
            return
        }

        if (listeners.containsKey(editor)) {
            return
        }

        val listener =
            TypingListener(editor)

        editor.document.addDocumentListener(
            listener
        )

        listeners[editor] =
            listener
    }

    private fun detach(
        editor: com.intellij.openapi.editor.Editor
    ) {

        val listener =
            listeners.remove(editor)
                ?: return

        editor.document.removeDocumentListener(
            listener
        )

        listener.dispose()
    }
}
