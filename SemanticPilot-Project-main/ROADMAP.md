# SemanticPilot Roadmap

SemanticPilot is a resume-focused JetBrains AI coding assistant. The project demonstrates Kotlin plugin development, IntelliJ PSI, FastAPI backend integration, LLM completions, inline ghost text, TAB acceptance, and automatic completion triggering.

The project is intentionally scoped for portfolio value. It is not intended to become production infrastructure.

## Phase Progress

## Phase 1 - Plugin Bootstrapping

Status: Completed

- plugin.xml
- startup activity
- tool window
- Gradle build
- runIde workflow

## Phase 2 - Context Extraction

Status: Completed

- cursor context
- selection context
- file context
- language detection

## Phase 3 - Completion Request Model

Status: Completed

- structured completion request
- serializable completion response

## Phase 4 - Mock Completion Pipeline

Status: Completed

- CompletionApi
- CompletionService
- TestContextAction

## Phase 5 - Completion Pipeline

Status: Completed

```text
Editor
  -> ContextService
  -> CompletionService
  -> CompletionApi
```

## Phase 6 - Rendering Architecture

Status: Completed

- renderer skeleton
- insertion pipeline

## Phase 7 - Ghost Text Architecture

Status: Completed

- ghost text rendering structure
- cleanup structure

## Phase 8 - Completion Acceptance Architecture

Status: Completed

- TAB acceptance structure
- insertion structure

## Phase 9 - Automatic Trigger Architecture

Status: Completed

- document listener structure
- debounce structure
- cancellation structure

## Phase 10 - Backend Integration

Status: Completed

- FastAPI backend
- Swagger UI
- Ktor client
- coroutine networking
- JSON serialization

## Phase 11 - Real LLM Integration

Status: Completed

- Gemini API primary backend path
- Ollama fallback path
- mock fallback path
- async completion pipeline

## Phase 12 - PSI Semantic Extraction

Status: Completed

- current function extraction
- current class extraction
- import extraction
- symbol extraction
- file metadata extraction

## Phase 13 - Inline Ghost Text Rendering

Status: Completed

- IntelliJ inlay renderer
- grey inline suggestion text
- non-persistent ghost text
- cleanup before new suggestions

## Phase 14 - Completion Acceptance

Status: Completed

- TAB acceptance
- WriteCommandAction insertion
- ghost cleanup after acceptance

## Phase 15 - Automatic Completion Triggering

Status: Completed

- document listeners
- debounce after typing
- background completion requests
- stale request cancellation/ignore path
- automatic ghost text rendering

## Phase 16 - Resume & Project Polish

Status: In Progress

- final README
- demo guide
- resume bullets
- screenshots
- demo GIF/video

## Final Completion Criteria

- Plugin runs in JetBrains sandbox IDE
- Backend starts locally
- LLM completion path works with fallback
- PSI semantic context is included in requests
- Ghost text appears inline
- TAB accepts visible suggestions
- Completions trigger automatically while typing
- Portfolio screenshots/GIF are captured
- Resume description is finalized

## Features Explicitly Excluded

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

After Phase 16, development stops.
