package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                GroceryListApp()
            }
        }
    }
}

@Composable
fun GroceryListApp() {
    // ---- 1. STATE ----
    var newItem by remember { mutableStateOf("") }
    val groceries = remember { mutableStateListOf<String>() }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        // App Title
        Text(text = "My Grocery List", fontSize = 24.sp)
        Spacer(modifier = Modifier.height(16.dp))

        // ---- 2. INPUT + ADD EVENT ----
        Row(verticalAlignment = Alignment.CenterVertically) {
            OutlinedTextField(
                value = newItem,
                onValueChange = { newItem = it },
                label = { Text("Enter an item...") },
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = {
                // Part B (Challenge 1): Ignore empty or whitespace-only items
                if (newItem.isNotBlank()) {
                    groceries.add(newItem.trim())
                    newItem = ""
                }
            }) {
                Text("Add")
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Part B (Challenge 2): Live item count
        Text(text = "Total items: ${groceries.size}", fontSize = 16.sp)

        Spacer(modifier = Modifier.height(8.dp))

        // Divider separating total count and list
        HorizontalDivider()

        Spacer(modifier = Modifier.height(8.dp))

        // ---- 3. LIST + DELETE EVENT ----
        LazyColumn {
            items(groceries) { item ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = item, fontSize = 18.sp)
                    // Styled [ x ] delete button matching the wireframe
                    OutlinedButton(
                        onClick = { groceries.remove(item) },
                        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 0.dp)
                    ) {
                        Text("x")
                    }
                }
            }
        }
    }
}