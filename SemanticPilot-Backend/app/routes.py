from fastapi import APIRouter
from app.models import CompletionRequest, CompletionResponse
from app.services import generate_completion

router = APIRouter()


@router.post("/completion")
async def completion(
    request: CompletionRequest
):

    print("======== REQUEST ========")

    print(request.prefix)

    completion_text = await generate_completion(request)

    return CompletionResponse(
        completion=completion_text
    )
