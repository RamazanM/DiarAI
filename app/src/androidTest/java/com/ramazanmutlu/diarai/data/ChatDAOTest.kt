package com.ramazanmutlu.diarai.data

import android.content.Context
import android.util.Log
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ramazanmutlu.diarai.data.dao.ChatDao
import com.ramazanmutlu.diarai.data.entities.ChatMessage
import com.ramazanmutlu.diarai.data.entities.Sender
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.text.SimpleDateFormat
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class ChatDaoTest {

    private lateinit var db: Database
    private lateinit var chatDao: ChatDao

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, Database::class.java).allowMainThreadQueries()
            .build()

        chatDao = db.getChatDao()
    }

    @After
    fun tearDown() {
        db.close()
    }

    @OptIn(ExperimentalTime::class)
    @Test
    fun insertAndFetchChat() = runTest {
        val chat = ChatMessage(
            content = "Hello!",
            timestamp = "2025-07-15".toLongDate(),
            id = 0,
            sender = Sender.USER,
            sessionId = "1"
        )
        chatDao.insert(chat)
        val lastChats = chatDao.getLastChats(10)
        Log.d("TAG", "insertAndFetchChat: ${lastChats.first()}")
        val chats = chatDao.getChatsByDate("2025-07-15".toLongDate())
        assertEquals(1, chats.size)
        assertEquals("Hello!", chats.first().content)
    }

    @Test
    fun deleteOldChatsTest() = runTest {
        val old = ChatMessage(
            content = "old", timestamp = "2024-12-01".toLongDate(), sender = Sender.USER,
            sessionId = "1",
        )
        val recent = ChatMessage(
            content = "new", timestamp = "2025-12-01".toLongDate(), sender = Sender.USER,
            sessionId = "1",
        )
        chatDao.insert(old)
        chatDao.insert(recent)

        chatDao.deleteOldChats("2025-01-01".toLongDate())
        val remaining = chatDao.getLastChats(10)

        assertEquals(1, remaining.size)
        assertEquals("new", remaining.first().content)
    }
}

@OptIn(ExperimentalTime::class)
fun String.toLongDate() = SimpleDateFormat("yyyy-MM-dd").parse(this)?.time ?: Clock.System.now().epochSeconds