package com.ramazanmutlu.diarai.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ramazanmutlu.diarai.data.entities.JournalEntry

@Dao
interface JournalDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(journal: JournalEntry)

    @Query("SELECT * FROM journal_entries ORDER BY date DESC")
    suspend fun getAllJournalEntries(): List<JournalEntry>

    @Query("SELECT * FROM journal_entries WHERE date = :date LIMIT 1")
    suspend fun getJournalByDate(date: String): JournalEntry?

    @Delete
    suspend fun delete(journal: JournalEntry)
}
