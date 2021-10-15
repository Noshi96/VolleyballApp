package com.papplications.volleyballteam.app.api

import android.graphics.Color
import java.util.*

class DrawService {
    fun randomPlayer(from: Int, to: Int): Int {
        val random = Random()
        return random.nextInt(to - from) + from
    }

    fun changeColor(){
        val rnd = Random()
        val color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
    }
}