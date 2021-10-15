package com.papplications.volleyballteam.app.match.database

import androidx.lifecycle.LiveData
import com.papplications.volleyballteam.app.match.model.Player

class PlayerRepository(private val playerDao: PlayerDao){

    val fetchAllData: LiveData<List<Player>> = playerDao.fetchAllData()

    suspend fun addPlayer(player: Player){
        playerDao.addPlayer(player)
    }
}