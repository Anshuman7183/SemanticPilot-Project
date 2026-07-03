package com.semanticpilot.actions

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.semanticpilot.completion.InlineGhostTextRenderer

class AcceptCompletionAction : AnAction() {

    override fun update(
        e: AnActionEvent
    ) {

        val editor =
            e.getData(
                CommonDataKeys.EDITOR
            )

        e.presentation.isEnabled =
            editor != null &&
                InlineGhostTextRenderer.hasSuggestion(
                    editor
                )
    }

    override fun actionPerformed(
        e: AnActionEvent
    ) {

        val editor =
            e.getData(
                CommonDataKeys.EDITOR
            ) ?: return

        InlineGhostTextRenderer.accept(
            editor
        )
    }
}
