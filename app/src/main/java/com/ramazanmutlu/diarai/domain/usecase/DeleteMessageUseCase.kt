package com.ramazanmutlu.diarai.domain.usecase

import com.ramazanmutlu.diarai.data.Database
import javax.inject.Inject

class DeleteMessageUseCase @Inject constructor(
    val database: Database
) {
    suspend operator fun invoke(id: Int) {
         database.getChatDao().deleteChat(id)
    }
}