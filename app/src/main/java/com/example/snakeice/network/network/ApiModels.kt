package com.example.snakeice.network.network

class ApiModels {
    @JsonClass(generateAdapter = true)
    data class ChatRequest(val prompt: String, val max_tokens: Int = 200)


    @JsonClass(generateAdapter = true)
    data class ChatResponse(val success: Boolean = true, val output: String? = null, val raw: Map<String, Any>? = null)
}