# SemanticPilot Demo Guide

## Start Backend

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

## Run Plugin

```powershell
cd D:\projects\SemanticPilot-Project\SemanticPilot-Project-main
./gradlew clean
./gradlew build
./gradlew runIde
```

## Test Ghost Text

1. Open a Python, Java, or Kotlin file in the sandbox IDE.
2. Place the caret inside a function.
3. Use `Tools > Test Context`.
4. Confirm grey inline ghost text appears at the caret.
5. Confirm the text is not inserted into the document yet.

## Test TAB Acceptance

1. Show ghost text using automatic triggering or `Tools > Test Context`.
2. Press `TAB`.
3. Confirm the full suggestion is inserted into the document.
4. Confirm the ghost text disappears.

## Test Auto-Triggering

1. Type inside an editor.
2. Pause briefly after typing.
3. Confirm SemanticPilot automatically requests a completion.
4. Confirm old ghost text clears when typing continues.

## Screenshots And GIF To Capture

- Plugin running in the JetBrains sandbox IDE.
- Backend console showing a `/completion` request.
- Grey inline ghost text at the caret.
- Accepted completion after pressing `TAB`.
- Short GIF: type, pause, ghost text appears, press `TAB`, suggestion is inserted.
