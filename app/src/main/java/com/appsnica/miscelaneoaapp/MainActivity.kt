package com.appsnica.miscelaneoaapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.appsnica.miscelaneoaapp.ui.theme.MiscelaneoaAppTheme
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import kotlinx.coroutines.launch


class MainActivity : ComponentActivity() {
        private val userPreferences by lazy { UserPreferences(this) }

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContent {
                val examplePreference by userPreferences.examplePreference.collectAsState()
                val scope = rememberCoroutineScope()
                var inputValue by remember { mutableStateOf("") }

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    Text(text = "Stored Preference: ${examplePreference ?: "Not Set"}")
                    Spacer(modifier = Modifier.height(8.dp))
                    TextField(
                        value = inputValue,
                        onValueChange = { inputValue = it },
                        label = { Text("Input Value") }
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(onClick = {
                        scope.launch {
                            userPreferences.saveExamplePreference(inputValue)
                        }
                    }) {
                        Text("Save Preference")
                    }
                }
            }
        }
    }


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello ya sabes $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MiscelaneoaAppTheme {
        Greeting("Android")
    }
}