package com.semanticpilot.editor

import com.intellij.openapi.editor.Editor

object GhostTextRenderer {

    fun show(
        editor: Editor,
        suggestion: String
    ) {

        println("========== GHOST TEXT ==========")
        println(suggestion)
    }
}