package com.example.dictionary.feature_dictionary.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.dictionary.R
import com.example.dictionary.feature_dictionary.domain.model.WordInfo
import com.example.dictionary.feature_dictionary.presentation.audio.PronunciationPlayer

@Composable
fun WordInfoItem(
    wordInfo: WordInfo,
    modifier: Modifier = Modifier
) {
    var isPlaying by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val player = remember {
        PronunciationPlayer(context)
    }

    DisposableEffect(Unit) {
        onDispose {
            player.release()
        }
    }

    Column(modifier = modifier) {
        Row {
            Text(
                text = wordInfo.word,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.weight(1f))

            wordInfo.audioUrl
                ?.takeIf(String::isNotBlank)
                ?.let { url ->
                    if (isPlaying) {
                        CircularProgressIndicator()
                    } else {
                        IconButton(
                            onClick = {
                                player.play(
                                    url = url,
                                    onLoading = { isPlaying = it },
                                    onError = { isPlaying = false }
                                )
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.PlayArrow,
                                contentDescription = stringResource(
                                    R.string.pronunciation
                                )
                            )
                        }
                    }
                }
        }

        wordInfo.phonetic?.takeIf(String::isNotBlank)?.let {
            Text(
                text = it,
                fontWeight = FontWeight.Light
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        wordInfo.origin
            ?.takeIf(String::isNotBlank)
            ?.let {
                Text(text = it)
                Spacer(modifier = Modifier.height(12.dp))
            }

        wordInfo.meanings.forEach { meaning ->
            Text(
                text = meaning.partOfSpeech,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(4.dp))

            meaning.definitions.forEachIndexed { index, definition ->
                Text(text = "${index + 1}. ${definition.definition}")

                definition.example
                    ?.takeIf(String::isNotBlank)
                    ?.let {
                        Text(
                            text = "${stringResource(R.string.example)} $it"
                        )
                    }

                Spacer(modifier = Modifier.height(8.dp))
            }

            Spacer(modifier = Modifier.height(12.dp))
        }
    }
}
