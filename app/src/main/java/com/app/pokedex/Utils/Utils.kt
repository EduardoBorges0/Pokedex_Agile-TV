package com.app.pokedex.Utils

import android.content.Context
import android.media.MediaPlayer
import com.app.pokedex.R

object Utils{
    fun playSound(context : Context) {
        val mediaPlayer = MediaPlayer.create(context, R.raw.song) 
        mediaPlayer.start()
        mediaPlayer.setOnCompletionListener {
            it.release()
        }
    }
}
