package com.ramazanmutlu.diarai.presentation.ui.screen

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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.ramazanmutlu.diarai.data.entities.Sender
import com.ramazanmutlu.diarai.domain.model.Message
import com.ramazanmutlu.diarai.presentation.ui.theme.DiarAiTheme
import com.ramazanmutlu.diarai.presentation.viewmodel.ChatViewModel
import kotlinx.coroutines.launch


@Composable
fun ChatScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val viewModel: ChatViewModel = hiltViewModel<ChatViewModel>()
    val state by viewModel.state.collectAsState()
    val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(null) {
        viewModel.fetchTodayMessages()
    }
    Box(modifier = modifier) {
        MessageList(
            Modifier
                .fillMaxWidth()
                .padding(bottom = 50.dp), state.messages
        )
        MessageTextBox(
            Modifier
                .align(Alignment.BottomCenter)
                .height(50.dp)
                .padding(start = 10.dp), { message->
                coroutineScope.launch {
                    viewModel.sendMessage(message)
                    Toast.makeText(
                        context,
                        "Sent:$message",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        )
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
        id = 1,
        content = "Hi, how was your day?",
        sender = Sender.USER
    ),
    Message(
        id = 2,
        content = "Hi! Not bad, I worked a bit. How about yours?",
        sender = Sender.ASSISTANT
    ),
    Message(
        id = 3,
        content = "It was busy, but I finished my work.",
        sender = Sender.USER
    ),
    Message(
        id = 4,
        content = "Nice, do you have any plans for the evening?",
        sender = Sender.ASSISTANT
    ),
    Message(
        id = 5,
        content = "Maybe I’ll play some games.",
        sender = Sender.USER
    ),
    Message(
        id = 6,
        content = "What kind of games do you like?",
        sender = Sender.ASSISTANT
    ),
    Message(
        id = 7,
        content = "Usually RPG and adventure games.",
        sender = Sender.USER
    ),
    Message(
        id = 8,
        content = "Have you tried Baldur’s Gate 3?",
        sender = Sender.ASSISTANT
    ),
    Message(
        id = 9,
        content = "Yes, I’ve even finished it.",
        sender = Sender.USER
    ),
    Message(
        id = 10,
        content = "Awesome! Do you read books?",
        sender = Sender.ASSISTANT
    ),
    Message(
        id = 11,
        content = "Yes, I usually read science fiction.",
        sender = Sender.USER
    ),
    Message(
        id = 12,
        content = "Then you must like Isaac Asimov.",
        sender = Sender.ASSISTANT
    ),
    Message(
        id = 13,
        content = "Absolutely, the Foundation series is my favorite.",
        sender = Sender.USER
    ),
    Message(
        id = 14,
        content = "I love it too. So, coffee or tea?",
        sender = Sender.ASSISTANT
    ),
    Message(
        id = 15,
        content = "Definitely coffee!",
        sender = Sender.USER
    ),
    Message(
        id = 16,
        content = "Are you one of those who can’t start the day without coffee?",
        sender = Sender.ASSISTANT
    ),
    Message(
        id = 17,
        content = "Exactly.",
        sender = Sender.USER
    ),
    Message(
        id = 18,
        content = "Me too, coffee is like fuel for the soul 😄",
        sender = Sender.ASSISTANT
    ),
    Message(
        id = 19,
        content = "Totally, a day without coffee feels incomplete.",
        sender = Sender.USER
    )
)
