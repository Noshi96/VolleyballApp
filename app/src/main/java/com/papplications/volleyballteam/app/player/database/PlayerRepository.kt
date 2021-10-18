package com.papplications.volleyballteam.app.player.database

import androidx.lifecycle.LiveData
import com.papplications.volleyballteam.app.match.database.MatchDao
import com.papplications.volleyballteam.app.player.model.Player

class PlayerRepository(private val matchDao: MatchDao, private val playerDao: PlayerDao) {

    val getAllPlayers: LiveData<List<Player>> = playerDao.getAllPlayers()

    suspend fun addPlayers(players: List<Player>) {
        playerDao.addPlayers(players = players)
    }

    suspend fun addPlayer(player: Player) {
        playerDao.addPlayer(player = player)
    }

    suspend fun updatePlayer(player: Player) {
        playerDao.updatePlayer(player = player)
    }

    suspend fun deletePlayer(player: Player) {
        playerDao.deletePlayer(player = player)
    }

    suspend fun deleteAllPlayers() {
        playerDao.deleteAllPlayers()
    }

    fun getPlayerIdByName(name: String): Int {
        return playerDao.getPlayerIdByName(name = name)
    }

    fun getPlayerByName(name: String): Player? {
        return playerDao.getPlayerByName(name = name)
    }

}