package com.liceo.liceochat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.liceo.liceochat.ui.LiceoAccountApp
import com.liceo.liceochat.ui.theme.LiceoAccountTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LiceoAccountTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    LiceoAccountApp()
                }
            }
        }
    }
}