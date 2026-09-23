package com.liceo.liceochat.ui

import java.util.Calendar

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.liceo.liceochat.domain.model.User

@Composable
fun ProfileScreen(user: User, onLogout: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .systemBarsPadding()
            .verticalScroll(rememberScrollState())
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // TODO 12a: Green Banner Card
        Card(
            colors = CardDefaults.cardColors(containerColor = Color(0xFFE8F5E9)),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "You successfully logged in!",
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF1B5E20)
                )
                Text(text = "Welcome back, ${user.fullName}.")
            }
        }

        // TODO 12b: Headline
        Text("My Profile", style = MaterialTheme.typography.headlineSmall)

        // TODO 12c: Details Rows
        ProfileRow("Full name", user.fullName)
        ProfileRow("Email", user.email)
        ProfileRow("Birthdate", user.birthdate)
        val age = ageFrom(user.birthdate)
        ProfileRow("Age", if (age != null) "$age years old" else "Unknown")
        ProfileRow("User ID", user.id)

        // TODO 12d: Logout Button
        Button(
            onClick = onLogout,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Log out")
        }
    }
}

@Composable
fun ProfileRow(label: String, value: String) {
    Column {
        Text(label, style = MaterialTheme.typography.labelMedium)
        Text(value, style = MaterialTheme.typography.bodyLarge)
    }
}

fun ageFrom(birthdate: String): Int? {
    val parts = birthdate.split("-")
    if (parts.size != 3) return null
    val birthYear = parts[0].toIntOrNull() ?: return null
    val birthMonth = parts[1].toIntOrNull() ?: return null
    val birthDay = parts[2].toIntOrNull() ?: return null

    val today = Calendar.getInstance()
    val thisYear = today.get(Calendar.YEAR)
    val thisMonth = today.get(Calendar.MONTH) + 1
    val thisDay = today.get(Calendar.DAY_OF_MONTH)

    var age = thisYear - birthYear
    if (thisMonth < birthMonth || (thisMonth == birthMonth && thisDay < birthDay)) {
        age--
    }
    return age
}