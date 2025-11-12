package com.ramazanmutlu.diarai.domain.usecase

import com.ramazanmutlu.diarai.data.Database
import com.ramazanmutlu.diarai.domain.model.Message
import com.ramazanmutlu.diarai.util.todayLong
import javax.inject.Inject

class GetMessagesOfTodayUseCase @Inject constructor(
    val database: Database
) {
    suspend operator fun invoke(): List<Message> {
        return database.getChatDao().getChatsByDate(todayLong).map { Message(it.id,it.content,it.timestamp,it.sender) }
    }
}