package com.ramazanmutlu.diarai.data

import com.ramazanmutlu.diarai.data.request.ChatCompletionRequest
import com.ramazanmutlu.diarai.data.response.ChatCompletionResponse
import com.ramazanmutlu.diarai.data.response.CompletionResponse
import com.ramazanmutlu.diarai.data.response.ModelListResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface OpenRouterService {
    @GET("/models")
    suspend fun getAvailableModels(): ModelListResponse

    @POST("/completions")
    suspend fun completion(model: String, prompt: String): CompletionResponse

    @POST("/chat/completions")
    suspend fun chatCompletion(@Body request: ChatCompletionRequest): ChatCompletionResponse
}