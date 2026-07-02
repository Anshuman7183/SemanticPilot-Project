from pydantic import BaseModel


class CompletionRequest(BaseModel):
    prefix: str
    suffix: str
    selectedText: str
    language: str
    fileContent: str


class CompletionResponse(BaseModel):
    completion: str