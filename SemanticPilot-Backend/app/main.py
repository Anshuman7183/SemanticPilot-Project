import os

from dotenv import load_dotenv
from fastapi import FastAPI

# Load environment variables from .env
load_dotenv()

print(
    "Gemini API key loaded:",
    bool(os.getenv("GEMINI_API_KEY"))
)

from app.routes import router

app = FastAPI(
    title="SemanticPilot Backend"
)

app.include_router(router)
