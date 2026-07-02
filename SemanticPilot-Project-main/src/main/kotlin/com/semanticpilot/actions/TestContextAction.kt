package com.semanticpilot.actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.application.ApplicationManager
import com.semanticpilot.completion.InlineGhostTextRenderer
import com.semanticpilot.services.CompletionService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TestContextAction : AnAction() {

    private val completionService =
        CompletionService()

    override fun actionPerformed(
        e: AnActionEvent
    ) {

        println(
            "TEST CONTEXT ACTION EXECUTED"
        )

        val editor =
            e.getData(CommonDataKeys.EDITOR)
                ?: return

        CoroutineScope(
            Dispatchers.IO
        ).launch {

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
        }
    }
}