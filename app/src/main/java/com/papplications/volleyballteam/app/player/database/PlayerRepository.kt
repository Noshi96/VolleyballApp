package com.papplications.volleyballteam.app.player.database

import androidx.lifecycle.LiveData
import com.papplications.volleyballteam.app.player.model.Player

class PlayerRepository(private val playerDao: PlayerDao){

    val fetchAllData: LiveData<List<Player>> = playerDao.fetchAllData()

    suspend fun addPlayer(player: Player){
        playerDao.addPlayer(player)
    }

    suspend fun updatePlayer(player: Player) {
        playerDao.updatePlayer(player)
    }
}