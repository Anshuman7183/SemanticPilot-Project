# SemanticPilot

SemanticPilot is a production-quality AI coding assistant for JetBrains IDEs inspired by GitHub Copilot and Sweep AI.

## Goals

- Streaming code completions
- PSI-driven semantic context extraction
- Repository-aware retrieval
- Multi-model support
- Syntax verification
- Agentic coding workflows
- Acceptance logging for future DPO fine-tuning

## Architecture

JetBrains Plugin
↓
FastAPI Backend
↓
Semantic Retrieval
↓
Prompt Builder
↓
LLM
↓
Ranking
↓
Ghost Text

## Tech Stack

Plugin:
- Kotlin
- IntelliJ Platform SDK

Backend:
- Python
- FastAPI

Models:
- OpenAI
- Claude
- Ollama

Future:
- LangGraph
- Symbol Graph
- Hybrid Retrieval
- DPO