package com.ramazanmutlu.diarai.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ramazanmutlu.diarai.data.entities.DbChatMessage

@Dao
interface ChatDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(chat: DbChatMessage)

    @Query("SELECT * FROM chat_messages WHERE timestamp = :date ORDER BY timestamp ASC")
    suspend fun getChatsByDate(date: Long): List<DbChatMessage>

    @Query("SELECT * FROM chat_messages ORDER BY timestamp ASC LIMIT :count")
    suspend fun getLastChats(count:Int): List<DbChatMessage>

    @Query("DELETE FROM chat_messages WHERE timestamp < :date")
    suspend fun deleteOldChats(date: Long)

    @Delete
    suspend fun deleteChat(chat: DbChatMessage)
}
