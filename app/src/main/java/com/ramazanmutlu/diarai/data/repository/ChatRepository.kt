package com.ramazanmutlu.diarai.data.repository

import com.ramazanmutlu.diarai.data.Database
import com.ramazanmutlu.diarai.data.entities.ChatMessage
import javax.inject.Inject

class ChatRepository @Inject constructor(private val database: Database) {
    val chatDao = database.getChatDao()

    suspend fun addMessage(message: ChatMessage) {
        chatDao.insert(message)
    }

    suspend fun getChatsByDate(date: Long): List<ChatMessage> {
        return chatDao.getChatsByDate(date)
    }

    suspend fun getLastChats(count: Int): List<ChatMessage> {
        return chatDao.getLastChats(count)
    }

    suspend fun deleteOldChats(date: Long) {
        chatDao.deleteOldChats(date)
    }

    suspend fun deleteChat(chatMessage: ChatMessage){
        chatDao.deleteChat(chatMessage)
    }


}