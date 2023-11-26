package com.projectss.theUltimateTodo.todo.quickInput.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ApiRequest(
        @JsonProperty("model")
        String model,
        @JsonProperty("prompt")
        String prompt,
        @JsonProperty("max_tokens")
        Integer maxTokens
        ) {
}
