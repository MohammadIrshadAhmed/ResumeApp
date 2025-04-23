package com.example.resumeandroidapp

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.R
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.resumeandroidapp.ui.theme.ResumeAndroidAppTheme

@Composable
fun CategoryPage() {
    // TODO: I have to implement a title with scaffold
    val CategoryList = arrayListOf<String>()
    CategoryList.add("Education")
    CategoryList.add("Skills")
    var selectedCategory by remember { mutableStateOf("") }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        Column(modifier = Modifier.padding(24.dp), verticalArrangement = Arrangement.SpaceEvenly) {
            CategoryList.forEach { category ->
                showButton(category, selectedCategory, onClick = { temp, ->
                    selectedCategory = temp
                })
            }
            AddNewCategoryButton(onClick = {
            })
        }
    }
}

@Composable
fun showButton(category: String, selected_category: String, onClick: (String) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(48.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(color = if (selected_category != category) Color.Gray else Color.Blue)
            .border(width = 2.dp, color = Color.Black, shape = RoundedCornerShape(16.dp))
            .clickable {
                onClick(category)
            },
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(category)
            Spacer(modifier = Modifier.width(8.dp))
            Text(">", fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun AddNewCategoryButton(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(48.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(color = Color.Gray)
            .border(width = 2.dp, color = Color.Black, shape = RoundedCornerShape(16.dp))
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("Add a new category")
            Text("+", fontWeight = FontWeight.Bold)
        }
    }
}



@Preview
@Composable
fun CategoryPagePreview() {
    ResumeAndroidAppTheme {
        CategoryPage()
    }
}