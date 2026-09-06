package com.example.dictionary.feature_dictionary.presentation.audio

import android.content.Context
import android.media.MediaPlayer

class PronunciationPlayer(
    context: Context
) {

    private val appContext = context.applicationContext
    private var player: MediaPlayer? = null

    fun play(
        url: String,
        onLoading: (Boolean) -> Unit,
        onError: () -> Unit
    ) {
        release()
        onLoading(true)

        try {
            player = MediaPlayer().apply {
                setDataSource(url)
                setOnPreparedListener { mediaPlayer ->
                    onLoading(false)
                    mediaPlayer.start()
                }
                setOnCompletionListener {
                    release()
                }
                setOnErrorListener { _, _, _ ->
                    onLoading(false)
                    onError()
                    release()
                    true
                }
                prepareAsync()
            }
        } catch (_: Exception) {
            onLoading(false)
            onError()
            release()
        }
    }

    fun release() {
        player?.release()
        player = null
    }
}
