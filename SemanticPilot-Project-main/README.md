# SemanticPilot

SemanticPilot is a resume-focused JetBrains IDE coding assistant. It demonstrates Kotlin plugin development, IntelliJ PSI semantic context extraction, FastAPI integration, LLM-backed completions, inline ghost text, TAB acceptance, and automatic completion triggering.

This is a portfolio project, not a production GitHub Copilot replacement.

## Project Vision

SemanticPilot shows the core architecture behind an AI coding assistant:

- collect editor context from a JetBrains IDE
- enrich the request with PSI semantic metadata
- send the request to an AI backend
- render the completion as non-persistent inline ghost text
- accept the suggestion with TAB
- trigger suggestions automatically while typing

## Architecture

```text
JetBrains IDE
    -> SemanticPilot Plugin
    -> Cursor/File/Selection Context
    -> PSI Semantic Context
    -> Ktor HTTP Client
    -> FastAPI Backend
    -> Gemini API
    -> Ollama fallback
    -> Mock fallback
    -> Inline Ghost Text
    -> TAB Acceptance
```

## Completed Features

- JetBrains plugin startup and tool window
- Cursor, selection, file, and language context extraction
- PSI semantic extraction for function, class, imports, file path, language, and symbol
- Serializable completion request and response models
- Ktor client connected to FastAPI `/completion`
- Gemini/Ollama/mock backend completion chain
- Inline grey ghost text using IntelliJ inlays
- TAB acceptance using `WriteCommandAction`
- Automatic completion triggering with debounce and stale-request protection
- Manual `Tools > Test Context` debug action

## Tech Stack

Plugin:

- Kotlin
- IntelliJ Platform SDK 2025.2
- IntelliJ Platform Gradle Plugin 2.9
- Kotlin coroutines
- Ktor Client
- kotlinx.serialization

Backend:

- Python
- FastAPI
- Pydantic
- Uvicorn
- Gemini API
- Ollama fallback

## Setup

Build and run the plugin:

```powershell
cd D:\projects\SemanticPilot-Project\SemanticPilot-Project-main
./gradlew clean
./gradlew build
./gradlew runIde
```

Start the backend:

```powershell
cd D:\projects\SemanticPilot-Project\SemanticPilot-Backend
.\.venv\Scripts\activate
python -m uvicorn app.main:app --reload --host 127.0.0.1 --port 8000
```

Optional Ollama fallback:

```powershell
ollama serve
ollama pull qwen3:8b
```

## Demo Workflow

1. Start the FastAPI backend.
2. Run the plugin with `./gradlew runIde`.
3. Open a Python, Java, or Kotlin file in the sandbox IDE.
4. Type inside a function and pause briefly.
5. Confirm grey inline ghost text appears at the caret.
6. Press `TAB` to accept the suggestion.
7. Use `Tools > Test Context` to manually debug the request pipeline.

## Resume Status

SemanticPilot is in Phase 16: Resume & Project Polish.

Completed implementation phases:

- Phase 10: Backend Integration
- Phase 11: Real LLM Integration
- Phase 12: PSI Semantic Extraction
- Phase 13: Inline Ghost Text Rendering
- Phase 14: Completion Acceptance
- Phase 15: Automatic Completion Triggering

The project is scoped for resume and portfolio value, emphasizing practical developer tooling over production infrastructure.
