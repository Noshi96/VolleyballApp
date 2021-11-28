package com.papplications.volleyballteam.app.api

import android.graphics.Color
import android.os.Build
import androidx.annotation.RequiresApi
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

    @RequiresApi(Build.VERSION_CODES.N)
    fun drawOneTeam(names: List<String>): MutableList<String> {
        val list: MutableList<String> = ArrayList()
        names.toMutableSet().let { it1 -> list.addAll(it1) }
        val randomElements2 = list.asSequence().shuffled().take((names.size.div(2))).toList()
        list.removeIf { i -> randomElements2.contains(i) }
        return list
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun drawTwoPlayers(names: List<String>): MutableList<String> {
        val list: MutableList<String> = ArrayList()
        names.toMutableSet().let { it1 -> list.addAll(it1) }
        val randomElements2 = list.asSequence().shuffled().take(2).toList()
        return randomElements2.toMutableList()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun deleteChosenPlayersAndReturnListWithRestPlayers(
        names: List<String>,
        chosenPlayers: MutableList<String>
    ): MutableList<String> {
        val list: MutableList<String> = ArrayList()
        names.toMutableSet().let { it1 -> list.addAll(it1) }
        list.removeIf { i -> chosenPlayers.contains(i) }
        return list
    }
}