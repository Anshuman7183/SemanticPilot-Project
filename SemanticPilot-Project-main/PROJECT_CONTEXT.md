# IMPORTANT

This project is intended for:

- Resume value
- Portfolio value
- Learning

Target development time:

1-2 weeks maximum.

After Phase 16, development permanently stops and the project is considered complete.

# SemanticPilot Project Context

# Project Overview

SemanticPilot is an AI-powered coding assistant for JetBrains IDEs.

The goal of this project is NOT to build a production-grade GitHub Copilot competitor.

The goal is to build a high-quality, resume-worthy AI developer tools project that demonstrates:

- JetBrains Plugin Development
- Kotlin Development
- IntelliJ Platform SDK
- IntelliJ PSI
- AI-assisted code completion
- FastAPI integration
- LLM integration
- Semantic code understanding

The project should remain small, maintainable, and optimized for learning and resume value.

---

# Primary Goal

Build a JetBrains plugin that can:

- Extract code context
- Understand code semantically using PSI
- Send context to an AI backend
- Receive code completions
- Render ghost text
- Accept completions using TAB
- Trigger completions automatically

After these goals are achieved, development stops.

---

# Project Philosophy

This project is:

- Resume oriented
- Portfolio oriented
- Learning oriented

This project is NOT:

- Production software
- Enterprise software
- Distributed software
- Multi-agent software
- Research infrastructure

---

# High-Level Architecture

```text
JetBrains IDE
        │
        ▼
SemanticPilot Plugin
        │
        ▼
Context Extraction
        │
        ▼
PSI Semantic Analysis
        │
        ▼
Completion Request
        │
        ▼
FastAPI Backend
        │
        ▼
Ollama LLM
        │
        ▼
Completion Response
        │
        ▼
Ghost Text Renderer
        │
        ▼
TAB Acceptance
```

---

# Technology Stack

## Plugin

- Kotlin
- IntelliJ Platform SDK 2025.2
- IntelliJ Platform Gradle Plugin 2.9
- Kotlin Coroutines
- Ktor Client
- kotlinx.serialization

## Backend

- Python
- FastAPI
- Pydantic
- Uvicorn

## LLM

Primary:
- Gemini API

Optional fallback:
- Ollama

---

# Repository Structure

```text
com.semanticpilot

├── actions/
|     ├── AcceptCompletionAction.kt
|     └── TestContextAction.kt
│
├── completion/
│     ├── CompletionInserter.kt
│     ├── EditorListener.kt
│     ├── TypingListener.kt
|      └── InlineGhostTextRenderer.kt
│
├── context/
│     ├── CursorContextExtractor.kt
│     ├── FileContextExtractor.kt
│     ├── SelectionContextExtractor.kt
│     └── LanguageDetector.kt
│
├── models/
│     ├── CompletionRequest.kt
│     └── CompletionResponse.kt
│     
│
├── network/
│     ├── ApiClient.kt
│     └── CompletionApi.kt
│
├── services/
│     ├── CompletionService.kt
│     ├── ContextService.kt
│     └── GhostTextService.kt
│
├── editor/
│     └── GhostTextRenderer.kt
│     
│
├── startup/
│     ├── SemanticPilotProjectActivity.kt
│     └── CompletionStartupActivity.kt
├── toolWindow/
      ├── SemanticPilotToolWindowFactory.kt
      └── SemanticPilotToolWindow.kt
```
SemanticPilot-Project-main/src/main
            └── resources/META-INF   
                    └── plugin.xml

SemanticPilot-Project-main/
        ├── build.gradle.kts
        ├── gradle.properties
        ├── gradlew
        ├── gradlew.bat
        ├── PROJECT_CONTEXT.md
        ├── README.md
        ├── ROADMAP.md
        └── settings.gradle.kts


# Backend Repository Structure

```text
SemanticPilot-Backend/

app/
├── main.py
├── models.py
├── routes.py
└── services.py

requirements.txt
```

# Backend Purpose
JetBrains Plugin
        ↓
Ktor HTTP Client
        ↓
FastAPI /completion endpoint
        ↓
Completion Service
        ↓
Ollama / Mock LLM
        ↓
CompletionResponse

---

# Development Rules

## Always

- Preserve architecture
- Preserve package structure
- Prefer minimal changes
- Explain modifications
- Fix one problem at a time
- Keep code modular

## Never

- Rewrite architecture
- Rename packages
- Delete modules
- Introduce unnecessary frameworks
- Refactor unrelated code
- Over-engineer solutions

---

# Completed Phases

---

## Phase 1 — Plugin Bootstrapping

Completed:

- plugin.xml
- Gradle setup
- runIde
- Tool window
- Startup activity

Status:

✅ Completed

---

## Phase 2 — Context Extraction

Completed:

- CursorContextExtractor
- SelectionContextExtractor
- FileContextExtractor
- LanguageDetector

Status:

✅ Completed

---

## Phase 3 — Completion Request Model

Completed:

```kotlin
CompletionRequest(
    prefix,
    suffix,
    selectedText,
    language,
    fileContent
)
```

Status:

✅ Completed

---

## Phase 4 — Mock Completion Pipeline

Completed:

- CompletionApi
- CompletionService
- TestContextAction

Status:

⚠ Partially Verified

---

## Phase 5 — Completion Architecture

Completed:

```text
Editor
   ↓
ContextService
   ↓
CompletionService
   ↓
CompletionApi
```

Status:

✅ Completed

---

## Phase 6 — Rendering Architecture

Completed:

- renderer skeleton
- insertion pipeline

Status:

✅ Completed

---

## Phase 7 — Ghost Text Architecture

Completed:

- rendering architecture
- cleanup architecture

Status:

⚠ In Progress

---

## Phase 8 — Completion Acceptance Architecture

Completed:

- TAB acceptance architecture
- insertion architecture

Status:

⚠ In Progress

---

## Phase 9 — Automatic Trigger Architecture

Completed:

- document listener architecture
- debounce architecture
- cancellation architecture

Status:

⚠ In Progress

---

## Phase 10 — Backend Integration

Current Work:

- FastAPI backend
- Swagger UI
- Ktor Client
- Kotlin Coroutines
- kotlinx.serialization

Current objective:

- Restore Gradle build stability
- Complete FastAPI integration
- Verify Ktor communication pipeline


Status:

⚠ In Progress

---

# Remaining Phases

---

## Phase 11 — Real LLM Integration

Goal:

Replace mock completions with real LLM completions.

Technology:

- Ollama
- qwen2.5:3b

Deliverables:

- Working LLM completions
- Async completion pipeline

Status:

⬜ Not Started

---

## Phase 12 — PSI Semantic Extraction

Goal:

Understand source code semantically.

Deliverables:

- Current function extraction
- Current class extraction
- Import extraction
- Symbol extraction
- File metadata extraction

Example:

```python
def factorial(n):
```

becomes:

```json
{
  "function":"factorial",
  "language":"python",
  "imports":[]
}
```

Status:

⬜ Not Started

---

## Phase 13 — Ghost Text Rendering

Goal:

Render completions inline similar to GitHub Copilot.

Example:

```python
def factorial(n):
    if
```

shows:

```python
n == 0:
    return 1
```

as ghost text.

Deliverables:

- Inlay renderer
- Ghost cleanup
- Cursor tracking

Status:

⬜ Not Started

---

## Phase 14 — Completion Acceptance

Goal:

Accept completions via TAB.

Deliverables:

- TAB acceptance
- Completion insertion
- Cursor repositioning

Status:

⬜ Not Started

---

## Phase 15 — Automatic Completion Triggering

Goal:

Generate completions while typing.

Deliverables:

- Document listeners
- Debouncing
- Cancellation
- Background requests

Example:

```text
User types
     ↓
Wait 300ms
     ↓
Request completion
     ↓
Render completion
```

Status:

⬜ Not Started

---

## Phase 16 — Resume & Project Polish

Goal:

Prepare SemanticPilot for resume and portfolio use.

Deliverables:

- Architecture diagram
- Screenshots
- Demo GIF/video
- Final README
- Final documentation

Architecture:

```text
JetBrains Plugin
        ↓
Context Extraction
        ↓
PSI Analysis
        ↓
FastAPI
        ↓
Ollama
        ↓
Completion
        ↓
Ghost Text
```

Status:

⬜ Not Started

---

# Project Completion Criteria

SemanticPilot is considered complete when:

✅ Plugin runs  
✅ Backend works  
✅ Ollama integration works  
✅ PSI extraction works  
✅ Ghost text works  
✅ TAB acceptance works  
✅ Automatic triggering works  
✅ Screenshots exist  
✅ Demo exists  
✅ Resume entry is finalized  

After Phase 16:

# STOP DEVELOPMENT

---

# Features Explicitly Excluded

The following features are intentionally excluded:

- Vector databases
- RAG
- Redis
- PostgreSQL
- LangGraph
- Multi-agent systems
- Fine-tuning
- DPO
- Telemetry
- Acceptance learning
- Ranking models
- Cloud deployment
- Distributed systems
- Enterprise features
- Production infrastructure

---

# Instructions For AI Agents (Codex)

## Token Optimization Rules

- Read only files relevant to the current issue.
- Never scan the entire repository unless explicitly requested.
- Prefer fixing one bug at a time.
- Prefer minimal diffs.
- Avoid generating new modules unless necessary.
- Preserve existing folder structure.
- Do not rewrite completed phases.

Before making changes:

1. Read this file completely.
2. Explain the problem.
3. Explain the root cause.
4. Explain the proposed fix.
5. List affected files.
6. Wait for confirmation.

Always:

- preserve architecture
- preserve package structure
- make minimal changes
- explain decisions

Never:

- rewrite architecture
- over-engineer solutions
- introduce unnecessary technologies
- optimize for production

---

# Current Priority

1. Fix Gradle build.
2. Complete backend integration.
3. Integrate Ollama.
4. Implement PSI extraction.
5. Implement ghost text.
6. Implement TAB acceptance.
7. Implement auto triggering.
8. Polish project.
9. Finish project.