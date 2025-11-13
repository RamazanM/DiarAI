package com.ramazanmutlu.diarai.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.ramazanmutlu.diarai.domain.model.Message
import com.ramazanmutlu.diarai.domain.usecase.DeleteMessageUseCase
import com.ramazanmutlu.diarai.domain.usecase.GetMessagesOfTodayUseCase
import com.ramazanmutlu.diarai.domain.usecase.SendMessageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

data class ChatScreenState(
    val messages: List<Message> = listOf(),
    val loading: Boolean = false
)
@HiltViewModel
class ChatViewModel @Inject constructor(
    private val sendMessageUseCase: SendMessageUseCase,
    private val getMessagesOfTodayUseCase: GetMessagesOfTodayUseCase,
    private val deleteMessageUseCase: DeleteMessageUseCase
): ViewModel() {
    private val _state = MutableStateFlow(ChatScreenState())
    val state = _state.asStateFlow()

    suspend fun fetchTodayMessages() {
        _state.update { it.copy(messages = getMessagesOfTodayUseCase()) }
    }

    suspend fun sendMessage(message: String) {
        sendMessageUseCase(message)
        fetchTodayMessages()
    }

    suspend fun deleteMessage(id: Int) {
        deleteMessageUseCase(id)
    }

}