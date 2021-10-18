package com.papplications.volleyballteam.app.player.viewmodel

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.papplications.volleyballteam.app.api.DrawService
import com.papplications.volleyballteam.app.match.database.MatchDatabase
import com.papplications.volleyballteam.app.player.database.PlayerRepository
import com.papplications.volleyballteam.app.player.model.Player
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PlayerViewModel(application: Application, private val drawService: DrawService) : AndroidViewModel(application) {

    val getAllPlayers: LiveData<List<Player>>
    private val repository: PlayerRepository


    private val _updateAdapters = MutableLiveData<Boolean>()
    val updateAdapters: LiveData<Boolean> = _updateAdapters

    private val _playerByName = MutableLiveData<Player>()
    val playerByName: LiveData<Player> = _playerByName

    init {
        val matchDao = MatchDatabase.getDatabase(application).matchDao()
        val playerDao = MatchDatabase.getDatabase(application).playerDao()
        repository = PlayerRepository(matchDao = matchDao, playerDao = playerDao)
        getAllPlayers = repository.getAllPlayers
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

    fun getPlayerIdByName(name: String): Int {
        var id = 0
        viewModelScope.launch(Dispatchers.IO) {
            id = repository.getPlayerIdByName(name)
        }
        return id
    }

    fun getPlayerByName(name: String): Player? {
        _updateAdapters.postValue(updateAdapters.value != true)
        return repository.getPlayerByName(name)
    }

    fun drawOneTeam(names: List<String>): MutableList<String>{
        return drawService.drawOneTeam(names)
    }
}