package com.example.dictionary.feature_dictionary.presentation

//noinspection UsingMaterialAndMaterial3Libraries
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun WordInfoContent(
    state: WordInfoState,
    searchQuery: String,
    onSearch: (String) -> Unit,
    scaffoldState: ScaffoldState
) {

    Scaffold(
        scaffoldState = scaffoldState
    ) {
        Box {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = onSearch,
                    modifier = Modifier.fillMaxWidth(),
                    label = {
                        Text(text = "Search")
                    }
                )
                Spacer(modifier = Modifier.height(16.dp))
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(state.wordInfoItems.size) { i ->
                        val wordInfo = state.wordInfoItems[i]
                        if (i > 0) {
                            Spacer(modifier = Modifier.height(8.dp))
                        }
                        WordInfoItem(wordInfo = wordInfo)
                        if (i < state.wordInfoItems.size - 1) {
                            Divider()

                        }
                    }
                }
            }
            if (state.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(
                        Alignment.Center
                    )
                )
            }
        }
    }

}