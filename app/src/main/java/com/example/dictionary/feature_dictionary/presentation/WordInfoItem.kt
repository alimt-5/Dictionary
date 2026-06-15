package com.example.dictionary.feature_dictionary.presentation


import android.media.MediaPlayer
import android.widget.Toast
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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dictionary.R
import com.example.dictionary.feature_dictionary.domain.model.WordInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun WordInfoItem(
    wordInfo: WordInfo,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    var isLoading by remember { mutableStateOf(false) }
    var mediaPlayer: MediaPlayer? = remember { null }

    fun playAudio(url: String) {
        if (isLoading) return
        isLoading = true

        scope.launch {
            try {
                val player = withContext(Dispatchers.IO) {
                    MediaPlayer().apply {
                        setDataSource(url)
                        prepare()
                    }
                }
                mediaPlayer?.release()
                mediaPlayer = player
                player.start()
                isLoading = false
                player.setOnCompletionListener {
                    player.release()
                    mediaPlayer = null
                }
                player.setOnErrorListener { _, _, _ ->
                    isLoading = false
                    Toast.makeText(
                        context,
                        context.getString(R.string.error_playing_sound),
                        Toast.LENGTH_SHORT
                    ).show()
                    true
                }
            } catch (e: Exception) {
                e.printStackTrace()
                isLoading = false
                Toast.makeText(
                    context,
                    context.getString(R.string.error_loading_sound) + " ${e.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            mediaPlayer?.release()
            mediaPlayer = null
        }
    }

    Column(modifier = modifier) {
        Row {
            Text(
                text = wordInfo.word,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(Modifier.weight(1f))
            wordInfo.audioUrl
                ?.takeIf { it.isNotBlank() }
                ?.let { url ->

                    if (isLoading) {
                        CircularProgressIndicator()
                    } else {
                        IconButton(
                            onClick = {
                                playAudio(url)
                            }
                        ) {
                            Icon(
                                imageVector = Icons.Default.PlayArrow,
                                contentDescription = stringResource(R.string.pronunciation)
                            )
                        }
                    }
                }
        }

        wordInfo.phonetic?.let { Text(text = it, fontWeight = FontWeight.Light) }
        Spacer(modifier = Modifier.height(16.dp))
        wordInfo.origin?.let { Text(text = it) }

        wordInfo.meanings.forEach { meaning ->
            Text(text = meaning.partOfSpeech, fontWeight = FontWeight.Bold)
            meaning.definitions.forEachIndexed { i, definition ->
                Text(text = "${i + 1}. ${definition.definition}")
                Spacer(modifier = Modifier.height(8.dp))
                definition.example?.let { example ->
                    Text(text = "${stringResource(R.string.example)} $example")
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}