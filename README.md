# SemanticPilot

SemanticPilot is a JetBrains IDE coding assistant prototype that shows AI-generated code completions directly inside the editor as ghost text.

I built this project to understand how Copilot-style developer tools work under the hood: editor listeners, cursor context extraction, PSI-based semantic analysis, backend orchestration, LLM completion, inline rendering, and TAB-based acceptance.

The current version is a working resume/demo project, not a production Marketplace plugin.

---

## Demo Flow

```text
Write code in a JetBrains IDE
        ↓
SemanticPilot reads cursor context
        ↓
Plugin sends request to FastAPI backend
        ↓
Backend generates completion using Gemini/Ollama/mock fallback
        ↓
Ghost text appears inside the editor
        ↓
Press TAB to accept the suggestion
```

Example input:

```python
def factorial(n):
    if
```

Example completion:

```python
n == 0:
    return 1
else:
    return n * factorial(n - 1)
```

After pressing `TAB`:

```python
def factorial(n):
    if n == 0:
        return 1
    else:
        return n * factorial(n - 1)
```

---

## What It Does

- Extracts editor context from the active JetBrains editor
- Captures prefix and suffix around the cursor
- Detects programming language from the active file
- Extracts semantic context using IntelliJ PSI
- Sends completion requests from the plugin to a FastAPI backend
- Uses Gemini as the primary LLM provider
- Falls back to Ollama if Gemini is unavailable
- Falls back to a mock completion if both providers fail
- Renders AI suggestions as inline ghost text
- Accepts suggestions using the `TAB` key
- Supports automatic completion triggering after typing pause
- Includes a manual `Tools → Test Context` action for debugging/demo

---

## Tech Stack

### JetBrains Plugin

- Kotlin
- IntelliJ Platform SDK
- IntelliJ Platform Gradle Plugin
- IntelliJ PSI APIs
- Ktor HTTP Client
- Kotlin Coroutines
- Inline Inlay Rendering

### Backend

- Python
- FastAPI
- Pydantic
- Uvicorn
- Google Gemini API
- Ollama fallback
- Mock fallback

---

## Architecture

```text
JetBrains IDE
    │
    ▼
SemanticPilot Plugin
    │
    ├── Editor Listener
    ├── Typing Listener
    ├── Cursor Context Extractor
    ├── File Context Extractor
    ├── Selection Context Extractor
    ├── PSI Semantic Context Extractor
    ├── Completion Service
    ├── Ktor API Client
    ├── Ghost Text Renderer
    └── TAB Acceptance Action
    │
    ▼
FastAPI Backend
    │
    ├── Request Validation
    ├── Prompt Builder
    ├── Gemini Provider
    ├── Ollama Provider
    └── Mock Fallback
    │
    ▼
Inline Completion in Editor
```

---

## Repository Structure

```text
SemanticPilot/
├── SemanticPilot-Project-main/
│   ├── src/main/kotlin/com/semanticpilot/
│   │   ├── actions/
│   │   │   ├── AcceptCompletionAction.kt
│   │   │   └── TestContextAction.kt
│   │   │
│   │   ├── completion/
│   │   │   ├── CompletionInserter.kt
│   │   │   ├── EditorListener.kt
│   │   │   ├── InlineGhostTextRenderer.kt
│   │   │   └── TypingListener.kt
│   │   │
│   │   ├── context/
│   │   │   ├── CursorContextExtractor.kt
│   │   │   ├── FileContextExtractor.kt
│   │   │   ├── LanguageDetector.kt
│   │   │   ├── SelectionContextExtractor.kt
│   │   │   └── SemanticContextExtractor.kt
|   |   |
│   │   │── editor/
|   |   |     └── GhostTextRenderer.kt
│   │   ├── models/
│   │   │   ├── CompletionRequest.kt
│   │   │   ├── CompletionResponse.kt
│   │   │   └── SemanticContext.kt
│   │   │
│   │   ├── network/
│   │   │   ├── ApiClient.kt
│   │   │   └── CompletionApi.kt
│   │   │
│   │   ├── services/
│   │   │   ├── CompletionService.kt
│   │   │   ├── ContextService.kt
│   │   │   └── GhostTextService.kt
│   │   │
│   │   ├── startup/
│   │   │   ├── CompletionStartupActivity.kt
│   │   │   └── SemanticPilotProjectActivity.kt
│   │   │
│   │   └── toolWindow/
│   │       ├── SemanticPilotToolWindow.kt
│   │       └── SemanticPilotToolWindowFactory.kt
│   │
│   ├── src/main/resources/META-INF/
│   │   └── plugin.xml
│   │
│   ├── build.gradle.kts
│   └── settings.gradle.kts
│
├── SemanticPilot-Backend/
│   ├── app/
│   │   ├── main.py
│   │   ├── models.py
│   │   ├── routes.py
│   │   └── services.py
│   │
│   ├── requirements.txt
│   └── .env.example
│
├── docs/
│   ├── DEMO_GUIDE.md
│   └── RESUME_BULLETS.md
│
├── PROJECT_CONTEXT.md
├── ROADMAP.md
└── README.md
```

---

## Backend Provider Chain

SemanticPilot uses a fallback chain so the demo remains stable:

```text
Gemini API
    ↓ if unavailable
Ollama
    ↓ if unavailable
Mock Completion
```

This means the plugin can still demonstrate the full IDE flow even if an external provider is temporarily unavailable.

---

## Setup

### 1. Clone the Repository

```bash
git clone https://github.com/Anshuman7183/SemanticPilot.git
cd SemanticPilot
```

---

## Backend Setup

Go to the backend folder:

```bash
cd SemanticPilot-Backend
```

Create a virtual environment:

```bash
python -m venv .venv
```

Activate it on Windows:

```bash
.venv\Scripts\activate
```

Install dependencies:

```bash
pip install -r requirements.txt
```

Create a `.env` file inside `SemanticPilot-Backend/`:

```env
GEMINI_API_KEY=your_gemini_api_key_here
MODEL_NAME=gemini-2.5-flash
```

Run the backend:

```bash
python -m uvicorn app.main:app --host 127.0.0.1 --port 8000
```

Expected output:

```text
Uvicorn running on http://127.0.0.1:8000
```

---

## Plugin Setup

Open a second terminal and go to the plugin folder:

```bash
cd SemanticPilot-Project-main
```

Run the JetBrains sandbox IDE:

```bash
.\gradlew.bat runIde
```

This launches a sandbox JetBrains IDE with the SemanticPilot plugin installed.

---

## Manual Test

In the sandbox IDE, create a Python file named `factorial.py`.

Add this code:

```python
def factorial(n):
    if
```

Place the cursor after `if`.

Then run:

```text
Tools → Test Context
```

Expected behavior:

1. The backend receives a `/completion` request.
2. A ghost text suggestion appears inside the editor.
3. Pressing `TAB` inserts the suggestion into the file.
4. The ghost text disappears after acceptance.

A successful backend log should include something like:

```text
POST /completion HTTP/1.1" 200 OK
```

---

## API

### POST `/completion`

Example request:

```json
{
  "prefix": "def factorial(n):\n    if",
  "suffix": "",
  "language": "python",
  "selectedText": "",
  "semanticContext": {
    "fileName": "factorial.py",
    "symbols": [],
    "imports": [],
    "currentFunction": "factorial"
  }
}
```

Example response:

```json
{
  "completion": "n == 0:\n        return 1\n    else:\n        return n * factorial(n - 1)"
}
```

---

## Current Status

| Module | Status |
|---|---|
| JetBrains plugin bootstrapping | Done |
| Editor context extraction | Done |
| Prefix/suffix extraction | Done |
| Language detection | Done |
| PSI semantic extraction | Done |
| FastAPI backend | Done |
| Gemini integration | Done |
| Ollama fallback | Done |
| Mock fallback | Done |
| Ghost text rendering | Done |
| TAB acceptance | Done |
| Automatic completion trigger | Done |
| Manual test action | Done |
| Documentation | Done |

---

## Screenshots and Demo Assets

Recommended files for the `docs/assets/` folder:

```text
docs/assets/01-project-structure.png
docs/assets/02-backend-running.png
docs/assets/03-ghost-text-suggestion.png
docs/assets/04-tab-accepted-completion.png
docs/assets/05-backend-request-log.png
docs/assets/semanticpilot-demo.gif
```

Suggested demo recording:

```text
Type code → ghost text appears → press TAB → completion is inserted
```

---

## What I Learned

This project helped me work through the core pieces of an IDE-native AI assistant:

- how JetBrains plugins are structured
- how editor actions and listeners work
- how to safely read editor context
- how PSI can be used for semantic code understanding
- how to connect a Kotlin plugin with a Python backend
- how to render inline suggestions inside an IDE
- how to build a reliable fallback chain for LLM-based features

---

## Limitations

SemanticPilot is a prototype. It is not intended to be a production-ready coding assistant yet.

Current limitations:

- completions are not streamed token-by-token
- multi-file retrieval is not fully implemented
- prompt optimization is still basic
- language-specific formatting can be improved
- there is no user-level settings UI for provider selection
- there is no production telemetry or acceptance-learning pipeline

---

## Future Improvements

Possible next steps:

- streaming completions
- stronger prompt templates
- multi-file context retrieval
- project-level symbol graph
- provider selection from plugin settings
- better formatting for Python, Java, Kotlin, and TypeScript
- acceptance tracking for future evaluation
- packaging for JetBrains Marketplace testing

---

## Summary

SemanticPilot can be summarized as:

> Built an AI-powered JetBrains coding assistant using Kotlin, IntelliJ Platform SDK, PSI-based context extraction, FastAPI, Gemini/Ollama integration, inline ghost text rendering, and TAB-based completion acceptance.

---

## Author

**Anshuman Anand Nayak**  
B.Tech Computer Science and Engineering  
VIT Bhopal University
