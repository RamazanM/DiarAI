package com.ramazanmutlu.diarai.domain.model

import com.ramazanmutlu.diarai.data.entities.Sender

data class Message(
    val id : Int,
    val content: String,
    val timestamp: Long = System.currentTimeMillis(),
    val sender: Sender,
)