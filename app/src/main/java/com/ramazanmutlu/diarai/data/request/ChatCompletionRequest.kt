package com.ramazanmutlu.diarai.data.request

data class ChatCompletionRequest(
    val model: String,
    val messages: List<ChatMessage>,
    val temperature: Double? = null,
    val top_p: Double? = null,
    val max_tokens: Int? = null,
    val stream: Boolean? = null,
    val stop: List<String>? = null,
    val presence_penalty: Double? = null,
    val frequency_penalty: Double? = null
)

data class ChatMessage(
    val role: String,  // "system", "user", "assistant"
    val content: String,
    val name: String? = null,
    val tool_call_id: String? = null
)