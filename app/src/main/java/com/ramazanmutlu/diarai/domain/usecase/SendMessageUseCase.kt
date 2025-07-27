package com.ramazanmutlu.diarai.domain.usecase

import android.annotation.SuppressLint
import android.os.Build
import com.ramazanmutlu.diarai.Config
import com.ramazanmutlu.diarai.data.Database
import com.ramazanmutlu.diarai.data.OpenRouterService
import com.ramazanmutlu.diarai.data.entities.DbChatMessage
import com.ramazanmutlu.diarai.data.entities.Sender
import com.ramazanmutlu.diarai.data.request.ChatCompletionRequest
import com.ramazanmutlu.diarai.data.request.ChatMessage
import com.ramazanmutlu.diarai.data.response.ChatMessageResponse
import com.ramazanmutlu.diarai.domain.model.Message
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.Date
import javax.inject.Inject

class SendMessageUseCase @Inject constructor(
    val database: Database,
    val service: OpenRouterService
) {
    @SuppressLint("SimpleDateFormat")
    suspend fun invoke(message: Message): ChatMessageResponse {
        database.getChatDao().insert(
            DbChatMessage(
                content = message.content,
                sender = Sender.USER
            )
        )

        val todayLong: Long = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            LocalDate.now().toEpochDay()
        } else {
            val formatter: DateFormat = SimpleDateFormat("dd/MM/yyyy")

            val today = Date()

            formatter.parse(formatter.format(today))?.time ?: Date().time
        }

        val messages = database.getChatDao().getChatsByDate(todayLong)

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