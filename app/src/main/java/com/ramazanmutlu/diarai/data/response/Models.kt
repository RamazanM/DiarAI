package com.ramazanmutlu.diarai.data.response

import com.google.gson.annotations.SerializedName

data class ModelListResponse(
    @SerializedName("data") val data: List<ModelItem>
)

data class ModelItem(
    @SerializedName("id") val id: String,
    @SerializedName("canonical_slug") val canonicalSlug: String,
    @SerializedName("hugging_face_id") val huggingFaceId: String,
    @SerializedName("name") val name: String,
    @SerializedName("created") val created: Long,
    @SerializedName("description") val description: String,
    @SerializedName("context_length") val contextLength: Int,
    @SerializedName("architecture") val architecture: Architecture,
    @SerializedName("pricing") val pricing: Pricing,
    @SerializedName("top_provider") val topProvider: TopProvider,
    @SerializedName("per_request_limits") val perRequestLimits: Any?,
    @SerializedName("supported_parameters") val supportedParameters: List<String>
)

data class Architecture(
    @SerializedName("modality") val modality: String,
    @SerializedName("input_modalities") val inputModalities: List<String>,
    @SerializedName("output_modalities") val outputModalities: List<String>,
    @SerializedName("tokenizer") val tokenizer: String,
    @SerializedName("instruct_type") val instructType: String?
)

data class Pricing(
    @SerializedName("prompt") val prompt: String,
    @SerializedName("completion") val completion: String,
    @SerializedName("request") val request: String,
    @SerializedName("image") val image: String,
    @SerializedName("web_search") val webSearch: String,
    @SerializedName("internal_reasoning") val internalReasoning: String,
    @SerializedName("input_cache_read") val inputCacheRead: String,
    @SerializedName("input_cache_write") val inputCacheWrite: String
)

data class TopProvider(
    @SerializedName("context_length") val contextLength: Int,
    @SerializedName("max_completion_tokens") val maxCompletionTokens: Int,
    @SerializedName("is_moderated") val isModerated: Boolean
)
