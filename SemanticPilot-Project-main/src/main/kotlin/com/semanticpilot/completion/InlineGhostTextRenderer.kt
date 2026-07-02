package com.semanticpilot.completion

import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.Inlay
import com.intellij.openapi.editor.EditorCustomElementRenderer
import com.intellij.openapi.editor.colors.EditorFontType
import com.intellij.openapi.editor.markup.TextAttributes
import java.awt.Color
import java.awt.Graphics
import java.awt.Rectangle

object InlineGhostTextRenderer {

    private var currentInlay: Inlay<*>? = null

    private var currentSuggestion: String? = null

    fun show(
        editor: Editor,
        suggestion: String
    ) {

        clear()

        currentSuggestion = suggestion

        currentInlay =
            editor.inlayModel.addInlineElement(
                editor.caretModel.offset,
                true,
                GhostRenderer(suggestion)
            )
    }

    fun accept(editor: Editor) {

        val suggestion =
            currentSuggestion ?: return

        WriteCommandAction.runWriteCommandAction(
            editor.project
        ) {
            editor.document.insertString(
                editor.caretModel.offset,
                suggestion
            )
        }

        clear()
    }

    fun clear() {

        currentInlay?.dispose()

        currentInlay = null

        currentSuggestion = null
    }

    private class GhostRenderer(
        private val text: String
    ) : EditorCustomElementRenderer {

        override fun calcWidthInPixels(
            inlay: Inlay<*>
        ): Int {

            val metrics =
                inlay.editor.contentComponent
                    .getFontMetrics(
                        inlay.editor.colorsScheme
                            .getFont(EditorFontType.PLAIN)
                    )

            return metrics.stringWidth(text)
        }

        override fun paint(
            inlay: Inlay<*>,
            g: Graphics,
            targetRegion: Rectangle,
            textAttributes: TextAttributes
        ) {

            g.font =
                inlay.editor.colorsScheme.getFont(
                    EditorFontType.PLAIN
                )

            g.color = Color.GRAY

            g.drawString(
                text,
                targetRegion.x,
                targetRegion.y + inlay.editor.ascent
            )
        }
    }
}