package com.semanticpilot.services

import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.Inlay
import com.intellij.openapi.editor.EditorCustomElementRenderer
import java.awt.Graphics
import java.awt.Rectangle

class GhostTextService {

    private var currentInlay: Inlay<*>? = null

    fun showSuggestion(
        editor: Editor,
        suggestion: String
    ) {

        clearSuggestion()

        currentInlay =
            editor.inlayModel.addInlineElement(

                editor.caretModel.offset,

                object : EditorCustomElementRenderer {

                    override fun calcWidthInPixels(
                        inlay: Inlay<*>
                    ): Int {

                        return 200
                    }

                    override fun paint(
                        inlay: Inlay<*>,
                        g: Graphics,
                        targetRegion: Rectangle,
                        textAttributes:
                        com.intellij.openapi.editor.markup.TextAttributes
                    ) {

                        g.color =
                            java.awt.Color.GRAY

                        g.drawString(
                            suggestion,
                            targetRegion.x,
                            targetRegion.y +
                                    g.fontMetrics.ascent
                        )
                    }
                }
            )
    }

    fun clearSuggestion() {

        currentInlay?.dispose()

        currentInlay = null
    }
}