package com.ramazanmutlu.diarai.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ramazanmutlu.diarai.data.dao.ChatDao
import com.ramazanmutlu.diarai.data.dao.JournalDao
import com.ramazanmutlu.diarai.data.entities.DbChatMessage
import com.ramazanmutlu.diarai.data.entities.JournalEntry

@Database(
    entities = [DbChatMessage::class, JournalEntry::class],
    version = 1
)
abstract class Database : RoomDatabase() {
    abstract fun getChatDao(): ChatDao
    abstract fun getJournalDao(): JournalDao
}

