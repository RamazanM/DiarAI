package com.ramazanmutlu.diarai.data.repository

import androidx.room.Delete
import androidx.room.Query
import com.ramazanmutlu.diarai.data.Database
import com.ramazanmutlu.diarai.data.entities.JournalEntry
import javax.inject.Inject

class JournalRepository @Inject constructor(database: Database) {
    val journalDao = database.getJournalDao()

    suspend fun insert(journal: JournalEntry){
        journalDao.insert(journal)
    }

    suspend fun getAllJournalEntries(): List<JournalEntry>{
        return journalDao.getAllJournalEntries()
    }

    suspend fun getJournalByDate(date: String): JournalEntry?{
        return journalDao.getJournalByDate(date)
    }

    @Delete
    suspend fun delete(journal: JournalEntry){
        journalDao.delete(journal)
    }

}