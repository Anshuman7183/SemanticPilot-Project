# SemanticPilot Development Roadmap

# Project Vision

SemanticPilot is an AI-powered coding assistant for JetBrains IDEs.

The long-term goal is to build a production-grade semantic coding assistant that combines:

- Semantic code understanding
- Retrieval-augmented context
- Multi-file reasoning
- Agentic workflows
- Ghost text completions
- Intelligent ranking
- Fine-tuning from user acceptance data

The architecture follows:

```
JetBrains Plugin
        ↓
Context Extraction
        ↓
Semantic Retrieval
        ↓
FastAPI Backend
        ↓
LLM Inference
        ↓
Candidate Ranking
        ↓
Ghost Text Rendering
        ↓
User Acceptance Feedback
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

## Future

- Ollama
- OpenAI API
- vLLM
- LangGraph
- Vector Database
- PostgreSQL
- Redis

---

# Current Project Structure

```
com.semanticpilot

actions/
completion/
context/
models/
network/
services/
settings/
startup/
toolWindow/
util/
```

---

# Development Rules

## Important

- Never rewrite the architecture.
- Never change package structure without approval.
- Fix one issue at a time.
- Prefer minimal changes.
- Explain all modifications.
- Preserve compatibility with IntelliJ Platform SDK 2025.2.
- Preserve Kotlin coroutines architecture.
- Preserve FastAPI backend architecture.

---

# Phase Progress

---

## Phase 1 — Plugin Bootstrapping

### Goal
Create working IntelliJ plugin.

### Status
✅ COMPLETED

### Deliverables

- plugin.xml
- startup activity
- tool window
- Gradle build
- runIde working

---

## Phase 2 — Context Extraction

### Goal
Extract editor context.

### Status
✅ COMPLETED

### Deliverables

- CursorContextExtractor
- SelectionContextExtractor
- FileContextExtractor
- LanguageDetector

---

## Phase 3 — Completion Request Model

### Goal
Create structured completion request.

### Status
✅ COMPLETED

### Deliverables

```kotlin
CompletionRequest(
    prefix,
    suffix,
    selectedText,
    language,
    fileContent
)
```

---

## Phase 4 — Mock Completion API

### Goal
Create local completion pipeline.

### Status
⚠ PARTIALLY VERIFIED

### Deliverables

- CompletionApi
- CompletionService
- TestContextAction
- hardcoded completion

### Remaining

- verify popup rendering
- verify completion insertion

---

## Phase 5 — Completion Pipeline

### Goal
Create end-to-end request flow.

### Status
✅ COMPLETED

### Deliverables

```
Editor
   ↓
ContextService
   ↓
CompletionService
   ↓
CompletionApi
```

---

## Phase 6 — Completion Rendering Architecture

### Goal
Prepare ghost rendering pipeline.

### Status
✅ COMPLETED

### Deliverables

- InlineGhostTextRenderer
- insertion pipeline

---

## Phase 7 — Ghost Text Rendering

### Goal
Display inline AI suggestions.

### Status
⚠ IN PROGRESS

### Deliverables

- inline inlay rendering
- ghost text display
- ghost cleanup

---

## Phase 8 — Accept Completion

### Goal
Accept suggestions via keyboard.

### Status
⚠ IN PROGRESS

### Deliverables

- TAB acceptance
- insertion
- cleanup

---

## Phase 9 — Automatic Triggering

### Goal
Trigger completions while typing.

### Status
⚠ IN PROGRESS

### Deliverables

- document listener
- debounce
- completion trigger

---

## Phase 10 — Backend Integration

### Goal
Connect plugin to FastAPI backend.

### Status
⚠ IN PROGRESS

### Deliverables

- FastAPI backend
- Swagger UI
- Ktor client
- coroutine networking
- JSON serialization

### Current Blocking Issue

```
Gradle plugin conflict:
org.jetbrains.intellij.platform already on classpath
```

---

## Phase 11 — Real LLM Integration

### Goal
Replace mock completion.

### Status
⬜ NOT STARTED

### Deliverables

- Ollama
- OpenAI
- local models
- streaming responses

---

## Phase 12 — PSI Semantic Extraction

### Goal
Understand source code semantically.

### Status
⬜ NOT STARTED

### Deliverables

- PSI traversal
- function extraction
- class extraction
- imports
- symbols
- references

---

## Phase 13 — Symbol Graph Retrieval

### Goal
Build semantic retrieval.

### Status
⬜ NOT STARTED

### Deliverables

- dependency graph
- call graph
- cross-file retrieval
- ranking

---

## Phase 14 — Prompt Builder

### Goal
Construct optimized prompts.

### Status
⬜ NOT STARTED

### Deliverables

- token budget manager
- prompt templates
- FIM prompting
- retrieval augmentation

---

## Phase 15 — Production Hardening

### Goal
Make SemanticPilot production-ready.

### Status
⬜ NOT STARTED

### Deliverables

- cancellation
- caching
- telemetry
- acceptance logging
- reranking
- streaming
- DPO dataset generation

---

# Current Priority

1. Fix Gradle build system.
2. Verify Ktor integration.
3. Verify FastAPI communication.
4. Verify coroutine pipeline.
5. Fix ghost rendering.
6. Fix TAB acceptance.
7. Fix automatic triggering.

---

# Instructions For Codex

Before making changes:

1. Explain the issue.
2. Explain the root cause.
3. Explain the proposed fix.
4. List affected files.
5. Wait for confirmation.

Never:

- rewrite architecture
- rename packages
- delete modules
- refactor unrelated code
- change completed phases

Always:

- preserve existing architecture
- make minimal changes
- keep code modular
- explain decisions