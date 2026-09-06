package com.example.dictionary.feature_dictionary.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.dictionary.R

@Composable
fun WordInfoContent(
    state: WordInfoState,
    onSearch: (String) -> Unit,
    contentPadding: PaddingValues
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(contentPadding)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            OutlinedTextField(
                value = state.query,
                onValueChange = onSearch,
                modifier = Modifier.fillMaxWidth(),
                label = {
                    Text(stringResource(R.string.search))
                },
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            if (!state.isLoading && state.words.isEmpty() && state.query.isNotBlank()) {
                Text(
                    text = stringResource(R.string.error_not_found),
                    style = MaterialTheme.typography.bodyLarge
                )
            }

            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                items(
                    items = state.words,
                    key = { it.word }
                ) { wordInfo ->
                    WordInfoItem(wordInfo = wordInfo)

                    if (wordInfo != state.words.lastOrNull()) {
                        HorizontalDivider(
                            modifier = Modifier.padding(vertical = 12.dp)
                        )
                    }
                }
            }
        }

        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}
