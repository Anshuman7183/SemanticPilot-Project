package com.semanticpilot.completion

import com.intellij.openapi.editor.event.DocumentEvent
import com.intellij.openapi.editor.event.DocumentListener
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.application.ApplicationManager
import com.semanticpilot.services.CompletionService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.swing.Timer

class TypingListener(
    private val editor: Editor
) : DocumentListener {

    private val completionService =
        CompletionService()

    private var timer: Timer? = null

    override fun documentChanged(
        event: DocumentEvent
    ) {

        timer?.stop()

        timer = Timer(500) {

            CoroutineScope(
                Dispatchers.IO
            ).launch {

                try {

                    val response =
                        completionService.getCompletion(
                            editor
                        )

                    ApplicationManager
                        .getApplication()
                        .invokeLater {

                            InlineGhostTextRenderer.show(
                                editor,
                                response.completion
                            )
                        }

                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

        timer?.isRepeats = false
        timer?.start()
    }
}
