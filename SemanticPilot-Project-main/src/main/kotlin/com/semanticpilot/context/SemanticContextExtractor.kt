package com.semanticpilot.context

import com.intellij.openapi.editor.Editor
import com.intellij.openapi.fileEditor.FileDocumentManager
import com.intellij.psi.PsiDocumentManager
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiNamedElement
import com.intellij.psi.PsiRecursiveElementWalkingVisitor
import com.semanticpilot.models.SemanticContext

object SemanticContextExtractor {

    fun extract(editor: Editor): SemanticContext {

        val language =
            LanguageDetector.detect(editor)

        val file =
            FileDocumentManager
                .getInstance()
                .getFile(editor.document)

        val psiFile =
            editor.project?.let {
                PsiDocumentManager
                    .getInstance(it)
                    .getPsiFile(editor.document)
            }

        val elementAtCaret =
            psiFile?.findElementAt(
                caretOffset(editor)
            )

        val functionName =
            findNamedParent(elementAtCaret) {
                it.isFunctionLike()
            }

        val className =
            findNamedParent(elementAtCaret) {
                it.isClassLike()
            }

        return SemanticContext(
            functionName = functionName,
            className = className,
            imports = psiFile?.extractImports() ?: emptyList(),
            language = language,
            filePath = file?.path,
            symbolName = functionName
                ?: className
                ?: findNamedParent(elementAtCaret) {
                    it is PsiNamedElement
                }
        )
    }

    private fun caretOffset(editor: Editor): Int {

        val textLength =
            editor.document.textLength

        if (textLength == 0) {
            return 0
        }

        return editor
            .caretModel
            .offset
            .coerceIn(0, textLength - 1)
    }

    private fun findNamedParent(
        element: PsiElement?,
        matches: (PsiElement) -> Boolean
    ): String? {

        var current =
            element

        while (current != null) {

            if (matches(current) && current is PsiNamedElement) {
                return current.name
            }

            current =
                current.parent
        }

        return null
    }

    private fun PsiElement.isFunctionLike(): Boolean {

        val typeName =
            this::class.java.simpleName.lowercase()

        return typeName.contains("function") ||
            typeName.contains("method")
    }

    private fun PsiElement.isClassLike(): Boolean {

        val typeName =
            this::class.java.simpleName.lowercase()

        return typeName.contains("class") ||
            typeName.contains("object")
    }

    private fun PsiElement.extractImports(): List<String> {

        val imports =
            mutableListOf<String>()

        accept(
            object : PsiRecursiveElementWalkingVisitor() {

                override fun visitElement(element: PsiElement) {

                    val typeName =
                        element::class.java.simpleName.lowercase()

                    if (typeName.contains("import")) {
                        imports.add(
                            element.text.trim()
                        )
                    }

                    super.visitElement(element)
                }
            }
        )

        return imports
            .filter {
                it.isNotBlank()
            }
            .distinct()
    }
}
