import asyncio
import json
from urllib import request as urllib_request
from urllib.error import URLError

from app.models import CompletionRequest


OLLAMA_URL = "http://127.0.0.1:11434/api/generate"
OLLAMA_MODEL = "qwen3:8b"
MOCK_COMPLETION = "return n * factorial(n - 1)"


async def generate_completion(
    request: CompletionRequest
) -> str:
    try:
        completion = await asyncio.to_thread(
            _request_ollama_completion,
            request
        )

        if completion.strip():
            return completion

    except Exception as error:
        print("======== OLLAMA FALLBACK ========")
        print(error)

    return MOCK_COMPLETION


def _request_ollama_completion(
    request: CompletionRequest
) -> str:
    payload = {
        "model": OLLAMA_MODEL,
        "prompt": _build_prompt(request),
        "stream": False,
    }

    body = json.dumps(payload).encode("utf-8")

    http_request = urllib_request.Request(
        OLLAMA_URL,
        data=body,
        headers={
            "Content-Type": "application/json"
        },
        method="POST",
    )

    try:
        with urllib_request.urlopen(http_request, timeout=30) as response:
            response_body = response.read().decode("utf-8")

    except URLError as error:
        raise RuntimeError("Ollama is unavailable") from error

    data = json.loads(response_body)

    return data.get("response", "")


def _build_prompt(
    request: CompletionRequest
) -> str:
    return (
        "You are SemanticPilot, a coding completion assistant.\n"
        "Return only the code completion text. Do not explain.\n\n"
        f"Language: {request.language}\n\n"
        "Prefix:\n"
        f"{request.prefix}\n\n"
        "Suffix:\n"
        f"{request.suffix}\n\n"
        "Selected text:\n"
        f"{request.selectedText}\n\n"
        "Complete the code at the cursor."
    )
