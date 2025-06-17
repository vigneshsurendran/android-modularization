package com.azabost.quest

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.azabost.quest.ui.theme.QuestTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import javax.inject.Provider

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var greetingServiceProvider: Provider<GreetingService>

    @Inject
    lateinit var defaultGreetingServiceProvider: Provider<DefaultGreetingService>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QuestTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        Greeting(
                            greeting = greetingServiceProvider.get().getGreeting("interface 1"),
                        )
                        Greeting(
                            greeting = greetingServiceProvider.get().getGreeting("interface 2"),
                        )
                        Greeting(
                            greeting = defaultGreetingServiceProvider.get().getGreeting("implementation 1"),
                        )
                        Greeting(
                            greeting = defaultGreetingServiceProvider.get().getGreeting("implementation 2"),
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(greeting: String, modifier: Modifier = Modifier) {
    Text(
        text = greeting,
        modifier = modifier
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Greeting(
            greeting = "Preview!",
            modifier = Modifier
                .padding(innerPadding)
        )
    }
}
