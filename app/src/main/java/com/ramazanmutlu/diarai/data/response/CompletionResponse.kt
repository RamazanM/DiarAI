package com.ramazanmutlu.diarai.data.response

data class CompletionResponse(
    val id: String,
    val choices: List<Choice>
)

data class Choice(
    val text: String,
    val index: Int,
    val finish_reason: String
)
