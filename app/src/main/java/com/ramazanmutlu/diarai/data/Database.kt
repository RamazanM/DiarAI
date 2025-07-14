package com.ramazanmutlu.diarai.data

import androidx.room.Dao
import androidx.room.Database
import androidx.room.Query
import androidx.room.RoomDatabase
import com.ramazanmutlu.diarai.data.entities.ChatMessage

@Database(
    entities = [ChatMessage::class],
    version = 1
)
abstract class Database : RoomDatabase() {
    abstract fun getMessagesDAO(): MessageDAO
}

@Dao
interface MessageDAO {
    @Query("SELECT * FROM messages")
    suspend fun allMessages():List<ChatMessage>
}
