import asyncio
import json
import os
from urllib import request as urllib_request
from urllib.error import URLError

from google import genai

from app.models import CompletionRequest


GEMINI_API_KEY = os.getenv("GEMINI_API_KEY")
MODEL_NAME = os.getenv("MODEL_NAME", "gemini-2.5-flash")

OLLAMA_URL = "http://127.0.0.1:11434/api/generate"
OLLAMA_MODEL = "qwen3:8b"

MOCK_COMPLETION = "return n * factorial(n - 1)"


gemini_client = None

if GEMINI_API_KEY:
    gemini_client = genai.Client(
        api_key=GEMINI_API_KEY
    )


async def generate_completion(
    request: CompletionRequest
) -> str:

    # 1. Gemini primary provider
    try:
        completion = await asyncio.to_thread(
            _request_gemini_completion,
            request
        )

        if completion.strip():
            return completion.strip()

    except Exception as error:
        print("======== GEMINI FALLBACK ========")
        print(error)

    # 2. Ollama fallback provider
    try:
        completion = await asyncio.to_thread(
            _request_ollama_completion,
            request
        )

        if completion.strip():
            return completion.strip()

    except Exception as error:
        print("======== OLLAMA FALLBACK ========")
        print(error)

    # 3. Final mock fallback
    return MOCK_COMPLETION


def _request_gemini_completion(
    request: CompletionRequest
) -> str:

    if gemini_client is None:
        raise RuntimeError(
            "Gemini API key missing"
        )

    response = gemini_client.models.generate_content(
        model=MODEL_NAME,
        contents=_build_prompt(request)
    )

    return response.text or ""


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
        with urllib_request.urlopen(
            http_request,
            timeout=30
        ) as response:
            response_body = response.read().decode(
                "utf-8"
            )

    except URLError as error:
        raise RuntimeError(
            "Ollama is unavailable"
        ) from error

    data = json.loads(response_body)

    return data.get("response", "")


def _build_prompt(
    request: CompletionRequest
) -> str:

    return (
        "You are SemanticPilot, an AI coding completion assistant.\n"
        "Return only the missing code completion text.\n"
        "Do not explain.\n"
        "Do not use markdown.\n"
        "Do not wrap the answer in code fences.\n\n"
        f"Language: {request.language}\n\n"
        "Prefix before cursor:\n"
        f"{request.prefix}\n\n"
        "Suffix after cursor:\n"
        f"{request.suffix}\n\n"
        "Selected text:\n"
        f"{request.selectedText}\n\n"
        "Complete the code at the cursor."
    )