package com.app.pokedex.Utils

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import com.app.pokedex.R

class MusicService : Service() {
    private var mediaPlayer: MediaPlayer? = null

    override fun onCreate() {
        super.onCreate()
        // Verifique se o arquivo de música existe no diretório raw
        mediaPlayer = MediaPlayer.create(this, R.raw.backgroundsound)
        mediaPlayer?.isLooping = true // Opcional: para tocar a música em loop
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        // Se o mediaPlayer não for nulo, começa a tocar
        mediaPlayer?.let {
            if (!it.isPlaying) {
                it.start()
            }
        }
        // Retorna START_STICKY para que o serviço continue em execução se o sistema precisar reiniciar
        return START_STICKY
    }

    override fun onBind(intent: Intent?): IBinder? {
        // Não precisamos de binding, portanto retornamos null
        return null
    }

    override fun onDestroy() {
        // Libera o recurso do mediaPlayer quando o serviço for destruído
        mediaPlayer?.apply {
            stop()
            release()
        }
        super.onDestroy()
    }
}
