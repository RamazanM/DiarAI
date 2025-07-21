package com.ramazanmutlu.diarai.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ramazanmutlu.diarai.data.entities.ChatMessage

@Dao
interface ChatDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(chat: ChatMessage)

    @Query("SELECT * FROM chat_messages WHERE timestamp = :date ORDER BY timestamp ASC")
    suspend fun getChatsByDate(date: Long): List<ChatMessage>

    @Query("SELECT * FROM chat_messages ORDER BY timestamp ASC LIMIT :count")
    suspend fun getLastChats(count:Int): List<ChatMessage>

    @Query("DELETE FROM chat_messages WHERE timestamp < :date")
    suspend fun deleteOldChats(date: Long)
}
