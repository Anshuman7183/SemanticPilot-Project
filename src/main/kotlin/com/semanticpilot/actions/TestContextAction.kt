package com.semanticpilot.actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.semanticpilot.services.ContextService

class TestContextAction : AnAction() {

    override fun actionPerformed(event: AnActionEvent) {

        val editor = event.getData(CommonDataKeys.EDITOR)
            ?: return

        val service = ContextService()

        val request = service.buildRequest(editor)

        println("========== PREFIX ==========")
        println(request.prefix)

        println("========== SUFFIX ==========")
        println(request.suffix)
    }
}