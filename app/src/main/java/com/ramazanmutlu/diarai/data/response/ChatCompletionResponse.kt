package com.ramazanmutlu.diarai.data.response

data class ChatCompletionResponse(
    val id: String,
    val choices: List<ChatCompletionChoice>,
    val created: Long,
    val model: String,
    val system_fingerprint: String?,
    val objectType: String,
    val usage: Usage?,
    val provider: String
)

data class ChatCompletionChoice(
    val index: Int,
    val message: ChatMessageResponse,
    val finish_reason: String?,
    val logprobs: LogProbs? = null
)

data class ChatMessageResponse(
    val role: String,
    val content: String?,
    val tool_calls: List<ToolCall>? = null,
    val tool_call_id: String? = null
)

data class Usage(
    val prompt_tokens: Int,
    val completion_tokens: Int,
    val total_tokens: Int
)

data class ToolCall(
    val id: String,
    val type: String,
    val function: ToolFunction
)

data class ToolFunction(
    val name: String,
    val arguments: String // JSON string
)

data class LogProbs(
    val content: List<LogProb>? = null
)

data class LogProb(
    val token: String,
    val logprob: Double,
    val bytes: List<Int>,
    val top_logprobs: List<TopLogProb>
)

data class TopLogProb(
    val token: String,
    val logprob: Double,
    val bytes: List<Int>
)
