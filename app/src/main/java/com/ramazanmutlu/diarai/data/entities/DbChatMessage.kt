package com.ramazanmutlu.diarai.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

enum class Sender { USER, ASSISTANT }

@Entity(tableName = "chat_messages")
data class DbChatMessage(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val content: String,
    val timestamp: Long = System.currentTimeMillis(),
    val sender: Sender,
)