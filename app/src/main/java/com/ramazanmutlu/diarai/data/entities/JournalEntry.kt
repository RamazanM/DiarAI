package com.ramazanmutlu.diarai.data.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "journal_entries")
data class JournalEntry(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val date: String, // YYYY-MM-DD
    val style: JournalStyle,
    val content: String,
    val createdAt: Long = System.currentTimeMillis()
)
