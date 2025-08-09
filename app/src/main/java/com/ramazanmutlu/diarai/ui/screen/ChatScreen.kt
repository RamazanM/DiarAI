package com.ramazanmutlu.diarai.ui.screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ramazanmutlu.diarai.data.entities.Sender
import com.ramazanmutlu.diarai.domain.model.Message
import com.ramazanmutlu.diarai.ui.theme.DiarAiTheme


@Composable
fun ChatScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Box(modifier = modifier) {
        MessageList(
            Modifier
                .fillMaxWidth()
                .padding(bottom = 50.dp), testMessages
        )
        MessageTextBox(
            Modifier
                .align(Alignment.BottomCenter)
                .height(50.dp)
                .padding(start = 10.dp)
        ) {
            Toast.makeText(
                context,
                "Sent:$it",
                Toast.LENGTH_LONG
            ).show()
        }
    }
}

@Composable
fun MessageList(modifier: Modifier = Modifier, messages: List<Message>) {
    LazyColumn(modifier = modifier) {
        items(messages) {
            Row(
                horizontalArrangement = if (it.sender == Sender.USER) Arrangement.End else Arrangement.Start,
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .heightIn(20.dp)
                        .widthIn(50.dp)
                        .padding(
                            start = if (it.sender == Sender.USER) 30.dp else 10.dp,
                            top = 10.dp,
                            end = if (it.sender == Sender.USER) 10.dp else 30.dp,
                            bottom = 10.dp
                        )
                        .background(
                            shape = RoundedCornerShape(5.dp), color =
                                if (it.sender == Sender.USER) MaterialTheme.colorScheme.secondaryContainer else MaterialTheme.colorScheme.primaryContainer
                        )
                        .padding(2.dp)
                ) {
                    Text(
                        it.content,
                        modifier = Modifier.align(Alignment.CenterStart),
                        maxLines = 10,
                        softWrap = true
                    )
                }
            }
        }
    }
}

@Composable
fun MessageTextBox(modifier: Modifier = Modifier, onClickSend: (String) -> Unit) {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    Row(modifier) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            value = text,
            onValueChange = { text = it }
        )
        IconButton(onClick = { onClickSend(text.text) }) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.Send,
                tint = Color.Black,
                contentDescription = "Send"
            )
        }
    }
}

@Preview
@Composable
fun ChatScreenPreview() {
    DiarAiTheme {
        ChatScreen()
    }
}

@Preview
@Composable
fun MessageListPreview() {
    DiarAiTheme(dynamicColor = false) {

        MessageList(
            modifier = Modifier.fillMaxWidth(),
            testMessages
        )
    }
}

val testMessages = listOf(
    Message(
        "Selam, günün nasıl geçti?",
        sender = Sender.USER
    ),
    Message(
        "Selam! Fena değildi, biraz çalıştım. Seninki nasıldı?",
        sender = Sender.ASSISTANT
    ),
    Message(
        "Yoğundu, ama işlerimi bitirdim.",
        sender = Sender.USER
    ),
    Message(
        "Güzel, akşam için planın var mı?",
        sender = Sender.ASSISTANT
    ),
    Message(
        "Belki biraz oyun oynarım.",
        sender = Sender.USER
    ),
    Message(
        "Hangi oyunları seviyorsun?",
        sender = Sender.ASSISTANT
    ),
    Message(
        "Genelde RPG ve macera oyunları.",
        sender = Sender.USER
    ),
    Message(
        "Baldur’s Gate 3’ü denedin mi?",
        sender = Sender.ASSISTANT
    ),
    Message(
        "Evet, hatta bitirdim.",
        sender = Sender.USER
    ),
    Message(
        "Harika! Peki kitap okur musun?",
        sender = Sender.ASSISTANT
    ),
    Message(
        "Evet, genelde bilim kurgu okuyorum.",
        sender = Sender.USER
    ),
    Message(
        "O zaman Isaac Asimov’u seviyorsundur.",
        sender = Sender.ASSISTANT
    ),
    Message(
        "Kesinlikle, özellikle Vakıf serisi favorim.",
        sender = Sender.USER
    ),
    Message(
        "Ben de severim. Peki kahve mi çay mı?",
        sender = Sender.ASSISTANT
    ),
    Message(
        "Kesinlikle kahve!",
        sender = Sender.USER
    ),
    Message(
        "Sabahları kahve içmeden başlayamayanlardan mısın?",
        sender = Sender.ASSISTANT
    ),
    Message(
        "Aynen öyle.",
        sender = Sender.USER
    ),
    Message(
        "Ben de aynıyım, kahve ruhun yakıtı gibi 😄",
        sender = Sender.ASSISTANT
    ),
    Message(
        "Aynen, kahvesiz bir günüm eksik hissediliyor.",
        sender = Sender.USER
    )
)
