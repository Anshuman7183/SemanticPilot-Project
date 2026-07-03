package com.semanticpilot.completion

import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.EditorCustomElementRenderer
import com.intellij.openapi.editor.Inlay
import com.intellij.openapi.editor.colors.EditorFontType
import com.intellij.openapi.editor.markup.TextAttributes
import com.intellij.ui.JBColor
import java.awt.Graphics
import java.awt.Graphics2D
import java.awt.Rectangle
import java.awt.RenderingHints

object InlineGhostTextRenderer {

    private var currentEditor: Editor? = null

    private var currentInlay: Inlay<*>? = null

    private var currentSuggestion: String? = null

    fun show(
        editor: Editor,
        suggestion: String
    ) {

        clear()

        if (suggestion.isBlank()) {
            return
        }

        val displayText =
            suggestion.toInlinePreview()

        currentSuggestion = suggestion

        currentEditor = editor

        currentInlay =
            editor.inlayModel.addInlineElement(
                editor.caretModel.offset,
                true,
                GhostRenderer(displayText)
            )

        currentInlay?.update()

        editor.contentComponent.repaint()
    }

    fun accept(editor: Editor) {

        val suggestion =
            currentSuggestion ?: return

        if (!hasSuggestion(editor)) {
            return
        }

        CompletionInserter.insert(
            editor,
            suggestion
        )

        clear()
    }

    fun hasSuggestion(editor: Editor): Boolean {

        return editor == currentEditor &&
            currentSuggestion != null &&
            currentInlay != null
    }

    fun clear() {

        currentInlay?.dispose()

        currentInlay = null

        currentEditor = null

        currentSuggestion = null
    }

    private fun String.toInlinePreview(): String {

        return lineSequence()
            .firstOrNull {
                it.isNotBlank()
            }
            ?.trimEnd()
            ?: trim()
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

            val graphics =
                g as? Graphics2D

            graphics?.setRenderingHint(
                RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON
            )

            g.font =
                inlay.editor.colorsScheme.getFont(
                    EditorFontType.PLAIN
                )

            g.color =
                JBColor(
                    java.awt.Color(105, 105, 105),
                    java.awt.Color(175, 175, 175)
                )

            g.drawString(
                text,
                targetRegion.x,
                targetRegion.y + inlay.editor.ascent
            )
        }
    }
}
