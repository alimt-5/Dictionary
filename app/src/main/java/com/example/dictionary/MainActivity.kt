package com.example.dictionary

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.dictionary.feature_dictionary.presentation.WordInfoScreen
import com.example.dictionary.ui.theme.DictionaryTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DictionaryTheme {
                WordInfoScreen()
            }
        }
    }
}
