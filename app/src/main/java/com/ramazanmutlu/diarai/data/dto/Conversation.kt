package com.ramazanmutlu.diarai.data.dto

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

data class ConversationStyle(
    val id: String,
    val display_name: String,
    val description: String,
    val example_input: String,
    val example_output: String,
    val style_rules: StyleRules
)

data class StyleRules(
    val tone: String,
    val emoji: Boolean,
    val slang: Boolean,
    val formality_level: Int
)

fun loadConversationStyles(context: Context): List<ConversationStyle> {
    val json = context.assets.open("conversation_styles.json")
        .bufferedReader().use { it.readText() }
    val type = object : TypeToken<List<ConversationStyle>>() {}.type
    return Gson().fromJson(json, type)
}
