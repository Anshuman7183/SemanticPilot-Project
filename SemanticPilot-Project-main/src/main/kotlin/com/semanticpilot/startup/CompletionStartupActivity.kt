package com.semanticpilot.startup

import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.ProjectActivity
import com.semanticpilot.completion.EditorListener

class CompletionStartupActivity : ProjectActivity {

    override suspend fun execute(
        project: Project
    ) {

        EditorListener.initialize(
            project
        )

        println(
            "SemanticPilot Completion System Started"
        )
    }
}