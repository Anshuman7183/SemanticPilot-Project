# SemanticPilot Resume Notes

## Short Project Description

SemanticPilot is a JetBrains IDE AI coding assistant built with Kotlin, IntelliJ Platform SDK, PSI semantic extraction, Ktor, and a FastAPI backend with Gemini/Ollama-backed completions. It renders inline ghost text, accepts suggestions with TAB, and triggers completions automatically while typing.

## Resume Bullets

- Built a JetBrains IDE plugin in Kotlin that extracts cursor, file, selection, and PSI semantic context, then sends structured completion requests to a FastAPI AI backend.
- Implemented inline ghost text rendering with IntelliJ inlays, TAB-based completion acceptance, and automatic debounced completion triggering using Kotlin coroutines.
- Integrated a FastAPI completion backend with Gemini primary inference, Ollama fallback, and mock fallback while keeping the project scoped as a resume-ready developer tooling prototype.

## Interview Explanation

SemanticPilot demonstrates the core architecture behind an AI coding assistant without presenting itself as production software. The plugin gathers editor context, enriches it with PSI semantic information such as current function, class, imports, file path, and symbol, then sends a serialized request to a FastAPI backend. The backend returns a completion through Gemini, Ollama, or a safe mock fallback. In the IDE, SemanticPilot renders the result as non-persistent inline ghost text, accepts it with TAB inside a write command, and automatically triggers suggestions after typing with debounce and stale-request protection.
