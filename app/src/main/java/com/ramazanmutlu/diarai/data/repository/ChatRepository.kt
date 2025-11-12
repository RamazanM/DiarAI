package com.ramazanmutlu.diarai.data.repository

import com.ramazanmutlu.diarai.data.Database
import com.ramazanmutlu.diarai.data.entities.DbChatMessage
import javax.inject.Inject

class ChatRepository @Inject constructor(database: Database) {
    val chatDao = database.getChatDao()

    suspend fun addMessage(message: DbChatMessage) {
        chatDao.insert(message)
    }

    suspend fun getChatsByDate(date: Long): List<DbChatMessage> {
        return chatDao.getChatsByDate(date)
    }

    suspend fun getLastChats(count: Int): List<DbChatMessage> {
        return chatDao.getLastChats(count)
    }

    suspend fun deleteOldChats(date: Long) {
        chatDao.deleteOldChats(date)
    }

    suspend fun deleteChat(messageId: Int){
        chatDao.deleteChat(messageId)
    }


}