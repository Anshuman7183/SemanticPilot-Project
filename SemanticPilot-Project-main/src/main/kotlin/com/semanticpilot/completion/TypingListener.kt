package com.semanticpilot.completion

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.event.DocumentEvent
import com.intellij.openapi.editor.event.DocumentListener
import com.semanticpilot.services.CompletionService
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.swing.Timer

class TypingListener(
    private val editor: Editor
) : DocumentListener {

    private val completionService =
        CompletionService()

    private var timer: Timer? = null

    private var requestJob: Job? = null

    private var requestVersion = 0

    override fun documentChanged(
        event: DocumentEvent
    ) {

        requestVersion++

        timer?.stop()

        requestJob?.cancel()

        InlineGhostTextRenderer.clear()

        timer = Timer(500) {

            val version =
                requestVersion

            CoroutineScope(
                Dispatchers.IO
            ).launch {

                requestJob =
                    coroutineContext[Job]

                try {

                    val response =
                        completionService.getCompletion(
                            editor
                        )

                    ApplicationManager
                        .getApplication()
                        .invokeLater {

                            if (version == requestVersion) {

                                InlineGhostTextRenderer.show(
                                    editor,
                                    response.completion
                                )
                            }
                        }

                } catch (e: CancellationException) {
                    throw e

                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

        timer?.isRepeats = false
        timer?.start()
    }

    fun dispose() {

        timer?.stop()

        requestJob?.cancel()

        timer = null

        requestJob = null
    }
}
