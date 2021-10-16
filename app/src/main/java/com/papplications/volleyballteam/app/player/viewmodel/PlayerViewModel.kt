package com.papplications.volleyballteam.app.player.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.papplications.volleyballteam.app.player.database.PlayerDatabase
import com.papplications.volleyballteam.app.player.database.PlayerRepository
import com.papplications.volleyballteam.app.player.model.Player
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PlayerViewModel(application: Application) : AndroidViewModel(application) {

    val fetchAllData: LiveData<List<Player>>
    private val repository: PlayerRepository

    init {
        val playerDao = PlayerDatabase.getDatabase(application).playerDao()
        repository = PlayerRepository(playerDao)
        fetchAllData = repository.fetchAllData
    }

    fun addPlayer(player: Player) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addPlayer(player)
        }
    }

    fun updatePlayer(updatePlayer: Player) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updatePlayer(updatePlayer)
        }
    }

    fun deletePlayer(player: Player) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deletePlayer(player)
        }
    }

    fun deleteAllPlayers() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteAllPlayers()
        }
    }

}