package com.ramazanmutlu.diarai.data

import com.ramazanmutlu.diarai.data.response.ModelListResponse
import retrofit2.http.GET

interface OpenRouterService {
    @GET("/models")
    suspend fun getAvailableModels(): ModelListResponse
}