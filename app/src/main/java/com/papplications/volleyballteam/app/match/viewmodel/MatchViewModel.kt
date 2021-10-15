package com.papplications.volleyballteam.app.match.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.papplications.volleyballteam.app.match.database.PlayerDatabase
import com.papplications.volleyballteam.app.match.database.PlayerRepository
import com.papplications.volleyballteam.app.match.model.Player
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MatchViewModel(application: Application): AndroidViewModel(application) {

    private val fetchAllData: LiveData<List<Player>>
    private val repository: PlayerRepository

    init {
        val playerDao = PlayerDatabase.getDatabase(application).playerDao()
        repository = PlayerRepository(playerDao)
        fetchAllData = repository.fetchAllData
    }

    fun addPlayer(player: Player){
        viewModelScope.launch(Dispatchers.IO){
            repository.addPlayer(player)
        }
    }
}