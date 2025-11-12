package com.ramazanmutlu.diarai.presentation.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ramazanmutlu.diarai.data.dto.ConversationStyle
import com.ramazanmutlu.diarai.data.dto.StyleRules
import com.ramazanmutlu.diarai.presentation.ui.theme.DiarAiTheme

@Composable
fun StyleSelectionScreen(modifier: Modifier = Modifier, styles: List<ConversationStyle>) {
    val pagerState = rememberPagerState(pageCount = { styles.size })
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        HorizontalPager(modifier = Modifier.weight(1f), state = pagerState) { page ->
            Column(
                modifier = Modifier.padding(30.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    styles[page].display_name,
                    style = MaterialTheme.typography.headlineLarge,
                    textAlign = TextAlign.Center
                )
                Spacer(Modifier.height(30.dp))
                Text(
                    styles[page].description,
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center
                )
            }
        }
        Row(
            Modifier
                .wrapContentHeight()
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 8.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(pagerState.pageCount) { iteration ->
                val color =
                    if (pagerState.currentPage == iteration) Color.DarkGray else Color.LightGray
                Box(
                    modifier = Modifier
                        .padding(2.dp)
                        .clip(CircleShape)
                        .background(color)
                        .size(16.dp)
                )
            }
        }
        TextButton(
            modifier = Modifier.padding(30.dp),
            colors = ButtonDefaults.textButtonColors(containerColor = MaterialTheme.colorScheme.inversePrimary),
            onClick = {}) {
            Text(
                "Continue",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(10.dp)
            )
        }
    }
}

@Composable
@Preview
fun StyleSelectionScreenPreview() {
    DiarAiTheme(dynamicColor = false) {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
            StyleSelectionScreen(Modifier.padding(innerPadding), conversationStyles)
        }
    }
}

val conversationStyles = listOf(
    ConversationStyle(
        id = "casual_summary",
        display_name = "Casual Daily Summary",
        description = "Summarizes the day's chats in a friendly, relaxed tone with informal language.",
        style_rules = StyleRules(
            tone = "friendly, relaxed",
            emoji = true,
            slang = true,
            formality_level = 1
        ),
        example_input = "",
        example_output = ""
    ),
    ConversationStyle(
        id = "formal_summary",
        display_name = "Formal Daily Summary",
        description = "Summarizes the day's chats in a professional, well-structured, and neutral tone.",
        style_rules = StyleRules(
            tone = "professional, structured",
            emoji = false,
            slang = false,
            formality_level = 5
        ),
        example_input = "",
        example_output = ""
    ),
    ConversationStyle(
        id = "humorous_summary",
        display_name = "Humorous Daily Summary",
        description = "Summarizes the day's chats with a playful, witty, and lighthearted narrative.",

        style_rules = StyleRules(
            tone = "playful, witty",
            emoji = true,
            slang = true,
            formality_level = 2
        ),
        example_input = "",
        example_output = ""
    )
)