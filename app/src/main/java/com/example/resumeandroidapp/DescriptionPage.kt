package com.example.resumeandroidapp

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.resumeandroidapp.ui.theme.ResumeAndroidAppTheme

@Composable
fun DescriptionPage(category: String, userViewModel: UserViewModel) {
    val textScrollState = rememberScrollState()
    val categoryDescriptionList by userViewModel.categoryDescriptionList.collectAsState()
    val description = categoryDescriptionList.first { it.first == category }.second
    Box(modifier = Modifier.fillMaxSize().background(color = Color.White)) {
        Column(modifier = Modifier.padding(32.dp)) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .clip(shape = RoundedCornerShape(16.dp))
                    .background(color = Color.Yellow)
                    .border(width = 2.dp, color = Color.Black, shape = RoundedCornerShape(16.dp))
                    .horizontalScroll(textScrollState),
                contentAlignment = Alignment.Center
            ) {
                Row(modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
                    Text(category, modifier = Modifier, maxLines = 1, overflow = TextOverflow.Ellipsis)
                }
            }
            Spacer(modifier = Modifier.fillMaxWidth().height(24.dp))
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(16.dp))
                    .background(color = Color.White)
                    .border(width = 2.dp, color = Color.Black, shape = RoundedCornerShape(16.dp)),
            ) {
                Row(modifier = Modifier.padding(16.dp)) {
                    Text(text = description)
                }
            }
        }
    }
}

@Preview
@Composable
fun DescriptionPagePreview() {
    ResumeAndroidAppTheme {
        DescriptionPage("", viewModel())
    }
}