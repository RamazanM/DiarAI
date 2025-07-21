package com.ramazanmutlu.diarai.data

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ramazanmutlu.diarai.data.dao.JournalDao
import com.ramazanmutlu.diarai.data.entities.JournalEntry
import com.ramazanmutlu.diarai.data.entities.JournalStyle
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.hamcrest.CoreMatchers.hasItem
import org.hamcrest.CoreMatchers.not
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class JournalDaoTest {

    private lateinit var database: Database
    private lateinit var journalDao: JournalDao

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, Database::class.java)
            .allowMainThreadQueries() // Test için main thread kullanmak sorun değil
            .build()

        journalDao = database.getJournalDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun insertJournal_shouldAddItemToDatabase() = runTest {
        val journal = JournalEntry(
            id = 1,
            style = JournalStyle.FRIENDLY,
            content = "Test content",
            createdAt = System.currentTimeMillis(),
            date = "22-11-2025"
        )

        journalDao.insert(journal)

        val journals = journalDao.getAllJournalEntries()

        assertThat(journals,hasItem(journal))
    }

    @Test
    fun deleteJournal_shouldRemoveItemFromDatabase() = runTest {
        val journal = JournalEntry(
            id = 1,
            style = JournalStyle.FRIENDLY,
            createdAt = System.currentTimeMillis(),
            date = "22-11-2025",
            content = "Delete me"
        )

        journalDao.insert(journal)
        journalDao.delete(journal)

        val journals = journalDao.getAllJournalEntries()

        assertThat(journals,not(hasItem(journal)))
    }

    @Test
    fun getAll_shouldReturnItemsInDescendingOrder() = runTest {
        val journal1 = JournalEntry(1, "11-12-2025", JournalStyle.FRIENDLY, "First", 1000)
        val journal2 = JournalEntry(2, "12-12-2025", JournalStyle.FRIENDLY, "Second", 2000)

        journalDao.insert(journal1)
        journalDao.insert(journal2)

        val journals = journalDao.getAllJournalEntries()
        assertEquals(journals.first(),journal2)
        assertEquals(journals.last(),journal1)
    }
}
