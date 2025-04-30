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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.resumeandroidapp.Utils.getCategoryAndDescription
import com.example.resumeandroidapp.Utils.makeDetailString
import com.example.resumeandroidapp.ui.theme.ResumeAndroidAppTheme

@Composable
fun CategoryPage(userName: String,userViewModel: UserViewModel, onNavigate: (String) -> Unit) {

    val usersData by userViewModel.usersData.collectAsState()
    var userDetail by remember { mutableStateOf("") }
    userDetail = usersData.firstOrNull { it.name == userName }?.detail ?: ""
    val categoryList by remember(userDetail) {
        mutableStateOf(getCategoryAndDescription(userDetail))
    }
    userViewModel.setCategoryDescriptionList(categoryList)
    var selectedCategory by remember { mutableStateOf("") }
    var showInputDialog by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
    ) {
        Column(modifier = Modifier.padding(24.dp), verticalArrangement = Arrangement.SpaceEvenly) {
            categoryList.forEach { category ->
                ShowButton(category.first, selectedCategory, onClick = { temp ->
                    selectedCategory = temp
                    onNavigate(temp)
                })
            }
            AddNewCategoryButton(onClick = {
                showInputDialog = true
            })
        }
    }
    if (showInputDialog) {
        EnterCategoryandDescription(categoryList, onClick = { category, description, showDialog ->
            showInputDialog = showDialog
            val detail = makeDetailString(userDetail, category, description)
            userViewModel.updateCategory(userName, detail)
        }, onDismiss = { showDialog ->
            showInputDialog = showDialog
        })
    }
}

@Composable
fun ShowButton(category: String, selectedCategory: String, onClick: (String) -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .height(48.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(color = if (selectedCategory != category) Color.Gray else Color.Blue)
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

@Composable
fun EnterCategoryandDescription(
    categoryList: ArrayList<Pair<String, String>>,
    onClick: (category: String, description: String, showDialog: Boolean) -> Unit,
    onDismiss: (showDialog: Boolean) -> Unit
) {
    var category by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var enableConfirmButton by remember { mutableStateOf(false) }
    AlertDialog(
        onDismissRequest = { onDismiss(false) },
        confirmButton = {
            Button(
                onClick = { onClick(category, description, false) },
                enabled = enableConfirmButton
            ) {
                Text("Confirm")
            }
        },
        dismissButton = {
            Button(onClick = { onDismiss(false) }) {
                Text("Cancel")
            }
        },
        title = { Text("Enter a new category and description") },
        text = {
            Column {
                OutlinedTextField(
                    value = category,
                    onValueChange = {
                        category = it.filter { it.isLetterOrDigit() }
                        enableConfirmButton =
                            (categoryList.none { it.first == category } && description.isNotEmpty())
                    },
                    label = { Text("Category") }
                )
                OutlinedTextField(
                    value = description,
                    onValueChange = {
                        description = it.filter { it.isLetterOrDigit() }
                        enableConfirmButton =
                            (categoryList.none { it.first == category } && description.isNotEmpty())
                    },
                    label = { Text("Description") }
                )
            }
        }
    )

}

@Preview
@Composable
fun CategoryPagePreview() {
    ResumeAndroidAppTheme {
        CategoryPage("", viewModel(), onNavigate = {})
    }
}