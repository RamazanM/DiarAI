package com.ramazanmutlu.diarai.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.UUID

enum class Sender { USER, ASSISTANT }

@Entity(tableName = "messages")
data class ChatMessage(
    @PrimaryKey val id: String = UUID.randomUUID().toString(),
    val timestamp: Long = System.currentTimeMillis(),
    val sender: Sender,
    val content: String
)