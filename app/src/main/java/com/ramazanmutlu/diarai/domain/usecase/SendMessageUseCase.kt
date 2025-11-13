package com.ramazanmutlu.diarai.domain.usecase

import android.annotation.SuppressLint
import com.ramazanmutlu.diarai.Config
import com.ramazanmutlu.diarai.data.Database
import com.ramazanmutlu.diarai.data.OpenRouterService
import com.ramazanmutlu.diarai.data.entities.DbChatMessage
import com.ramazanmutlu.diarai.data.entities.Sender
import com.ramazanmutlu.diarai.data.request.ChatCompletionRequest
import com.ramazanmutlu.diarai.data.request.ChatMessage
import com.ramazanmutlu.diarai.data.response.ChatMessageResponse
import com.ramazanmutlu.diarai.util.getStartOfTodayTimestamp
import java.util.Date
import javax.inject.Inject

class SendMessageUseCase @Inject constructor(
    val database: Database,
    val service: OpenRouterService
) {
    @SuppressLint("SimpleDateFormat")
    suspend operator fun invoke(message: String): ChatMessageResponse {
        database.getChatDao().insert(
            DbChatMessage(
                content = message,
                sender = Sender.USER,
                timestamp = Date().time
            )
        )



        val messages = database.getChatDao().getChatsByDate(getStartOfTodayTimestamp())

        val response = service.chatCompletion(
            ChatCompletionRequest(
                model = Config.SELECTED_MODEL,
                messages = messages.map {
                    ChatMessage(
                        role = if (it.sender == Sender.USER) "user" else "assistant",
                        content = it.content,
                    )
                },
            )
        )
        response.choices.first().message.let {
            database.getChatDao().insert(
                DbChatMessage(
                    content = it.content ?: "N/A",
                    timestamp = Date().time,
                    sender = Sender.ASSISTANT
                )
            )
            return it
        }
    }

}