from pydantic import BaseModel, Field


class SemanticContext(BaseModel):
    functionName: str | None = None
    className: str | None = None
    imports: list[str] = Field(default_factory=list)
    language: str
    filePath: str | None = None
    symbolName: str | None = None


class CompletionRequest(BaseModel):
    prefix: str
    suffix: str
    selectedText: str
    language: str
    fileContent: str
    semanticContext: SemanticContext | None = None


class CompletionResponse(BaseModel):
    completion: str
