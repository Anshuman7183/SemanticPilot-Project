# SemanticPilot

> An AI-powered semantic coding assistant for JetBrains IDEs.

SemanticPilot is a next-generation AI coding assistant designed for JetBrains IDEs. Unlike traditional autocomplete systems, SemanticPilot aims to understand the semantic structure of codebases using IntelliJ PSI, retrieval-based context augmentation, and large language models to provide intelligent code completions, explanations, and agentic coding workflows.

---

## Vision

The goal of SemanticPilot is to build a Copilot-like assistant that combines:

- Semantic code understanding
- Retrieval-augmented context
- Multi-file reasoning
- Intelligent code completion
- Ghost text rendering
- Agentic coding workflows
- Acceptance-driven learning

---

## Architecture

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
Semantic Retrieval
      │
      ▼
FastAPI Backend
      │
      ▼
LLM Provider
      │
      ▼
Candidate Ranking
      │
      ▼
Ghost Text Rendering
      │
      ▼
User Acceptance Feedback
```

---

## Features

### Current Features

- IntelliJ Platform Plugin
- Context extraction
- Prefix/suffix extraction
- Language detection
- Completion request pipeline
- FastAPI backend integration
- Ktor HTTP client
- Kotlin coroutines
- Mock completion generation

### Planned Features

- Inline ghost text rendering
- Tab completion acceptance
- Automatic completion triggering
- Streaming completions
- PSI semantic analysis
- Symbol graph retrieval
- Multi-file reasoning
- Prompt optimization
- Acceptance tracking
- Fine-tuning dataset generation

---

## Project Structure

```text
SemanticPilot-Project-main/

src/main/kotlin/com/semanticpilot/

├── actions/
├── completion/
├── context/
├── models/
├── network/
├── services/
├── settings/
├── startup/
├── toolWindow/
└── util/
```

---

## Backend Architecture

```text
SemanticPilot-Backend/

app/
├── main.py
├── routes.py
├── models.py
└── services.py
```

---

## Tech Stack

### Plugin

- Kotlin
- IntelliJ Platform SDK
- IntelliJ Platform Gradle Plugin
- Kotlin Coroutines
- Ktor Client
- kotlinx.serialization

### Backend

- Python
- FastAPI
- Pydantic
- Uvicorn

### Future

- Ollama
- OpenAI API
- vLLM
- LangGraph
- PostgreSQL
- Redis
- Vector Databases

---

## Installation

### Clone Repository

```bash
git clone https://github.com/Anshuman7183/SemanticPilot.git
cd SemanticPilot
```

### Plugin

```bash
./gradlew runIde
```

### Backend

```bash
cd SemanticPilot-Backend

python -m venv venv

venv\Scripts\activate

pip install -r requirements.txt

uvicorn app.main:app --reload
```

---

## Development Status

| Phase | Status |
|--------|---------|
| Plugin Bootstrapping | ✅ |
| Context Extraction | ✅ |
| Completion Pipeline | ✅ |
| Mock Completion | ✅ |
| Backend Integration | 🚧 |
| Ghost Text Rendering | 🚧 |
| Automatic Triggering | 🚧 |
| LLM Integration | ⬜ |
| Semantic Retrieval | ⬜ |
| Production Hardening | ⬜ |

---

## Long-Term Goals

- Copilot-style completions
- Semantic code understanding
- Multi-file retrieval
- Agentic workflows
- Acceptance learning
- Local and cloud LLM support
- Production-grade JetBrains AI assistant

---

## Author

**Anshuman Anand Nayak**

B.Tech Computer Science Engineering  
VIT Bhopal University
