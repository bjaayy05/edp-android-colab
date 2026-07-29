package com.example.myapplication

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.ProfileTheme

@Composable
fun ProfileScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        // ----------------------------------------------------
        // 1. Circular Avatar — initials placeholder
        // Box + .clip(CircleShape) + .background(primary) + .border(...)
        // ----------------------------------------------------
        Box(
            modifier = Modifier
                .size(110.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primary)
                .border(
                    width = 2.dp,
                    color = MaterialTheme.colorScheme.secondary,
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "BJ", // Initials placeholder
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onPrimary,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // ----------------------------------------------------
        // 2. Full Name — bold, largest text with headlineSmall & primary color
        // ----------------------------------------------------
        Text(
            text = "Bench Jay C. Sumalinog",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(4.dp))

        // ----------------------------------------------------
        // 3. Course + Section subtitle — typography.titleMedium
        // ----------------------------------------------------
        Text(
            text = "BSIT • Section A1",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(24.dp))

        // ----------------------------------------------------
        // 4. Info Card — groups the fields with Card
        // ----------------------------------------------------
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                // ----------------------------------------------------
                // 5. Five info rows in order: Full Name, Course, Section, Mobile No., Email
                // ----------------------------------------------------
                InfoRow(
                    icon = Icons.Default.Person,
                    label = "Full Name",
                    value = "Bench Jay C. Sumalinog"
                )
                HorizontalDivider()

                InfoRow(
                    icon = Icons.Default.School,
                    label = "Course",
                    value = "Bachelor of Science in Information Technology"
                )
                HorizontalDivider()

                InfoRow(
                    icon = Icons.Default.Info,
                    label = "Section",
                    value = "BSIT-3"
                )
                HorizontalDivider()

                InfoRow(
                    icon = Icons.Default.Phone,
                    label = "Mobile Number",
                    value = "+63 912 345 6789"
                )
                HorizontalDivider()

                InfoRow(
                    icon = Icons.Default.Email,
                    label = "Email Address",
                    value = "bsumalinog84951@liceo.edu.ph"
                )
            }
        }
    }
}

// ----------------------------------------------------
// Reusable InfoRow Composable matching: Row { Icon · Column(label + value) }, using .weight(1f)
// ----------------------------------------------------
@Composable
fun InfoRow(
    icon: ImageVector,
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = MaterialTheme.colorScheme.primary,
            modifier = Modifier.size(24.dp)
        )

        Spacer(modifier = Modifier.width(16.dp))

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = value,
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Medium),
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}

// ----------------------------------------------------
// Previews
// ----------------------------------------------------
@Preview(name = "Light Mode", showBackground = true)
@Composable
fun LightModePreview() {
    ProfileTheme(darkTheme = false) {
        ProfileScreen()
    }
}

@Preview(
    name = "Dark Mode",
    showBackground = true,
    uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun DarkModePreview() {
    ProfileTheme(darkTheme = true) {
        ProfileScreen()
    }
}