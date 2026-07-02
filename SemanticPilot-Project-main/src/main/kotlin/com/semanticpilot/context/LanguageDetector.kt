package com.semanticpilot.context

import com.intellij.openapi.editor.Editor
import com.intellij.openapi.fileEditor.FileDocumentManager

object LanguageDetector {

    fun detect(editor: Editor): String {

        val file = FileDocumentManager
            .getInstance()
            .getFile(editor.document)

        return when (file?.extension?.lowercase()) {

            "py" -> "python"

            "java" -> "java"

            "kt" -> "kotlin"

            "js" -> "javascript"

            "ts" -> "typescript"

            "cpp" -> "cpp"

            "c" -> "c"

            else -> "text"
        }
    }
}