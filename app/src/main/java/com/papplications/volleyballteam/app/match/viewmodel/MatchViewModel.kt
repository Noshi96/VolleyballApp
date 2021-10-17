package com.papplications.volleyballteam.app.match.viewmodel

import android.app.Application
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.papplications.volleyballteam.app.match.database.MatchDatabase
import com.papplications.volleyballteam.app.match.database.MatchRepository
import com.papplications.volleyballteam.app.match.model.Match
import com.papplications.volleyballteam.app.match.model.MatchWithPlayers
import com.papplications.volleyballteam.app.match.model.Set
import com.papplications.volleyballteam.app.match.model.MatchWithSets
import com.papplications.volleyballteam.app.match.model.Player
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MatchViewModel(application: Application) : AndroidViewModel(application)  {

    val getAllMatches: LiveData<List<Match>>
    val getAllMatchesWithSets: LiveData<List<MatchWithSets>>
    val getAllSets: LiveData<List<Set>>
    val getAllMatchWithPlayers: LiveData<List<MatchWithPlayers>>
    val getAllPlayers: LiveData<List<Player>>
    private val repository: MatchRepository

    private val _updateAdapters = MutableLiveData<Boolean>()
    val updateAdapters: LiveData<Boolean> = _updateAdapters

    private val _playerByName = MutableLiveData<Player>()
    val playerByName: LiveData<Player> = _playerByName

    init {
        val matchDao = MatchDatabase.getDatabase(application).matchDao()
        repository = MatchRepository(matchDao)
        getAllMatches = repository.getAllMatches
        getAllMatchesWithSets = repository.getAllMatchesWithSets
        getAllSets = repository.getAllSets
        getAllMatchWithPlayers = repository.getMatchWithPlayers
        getAllPlayers = repository.getAllPlayers
    }

    fun addMatch(match: Match) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addMatch(match)
        }
    }

    fun updateMatch(match: Match) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateMatch(match)
        }
    }

    fun deleteMatch(match: Match) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteMatch(match)
        }
    }

    fun addSet(set: Set) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addSet(set)
        }
    }

    fun updateSet(set: Set) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateSet(set)
        }
    }

    fun deleteSet(set: Set) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteSet(set)
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun drawOneTeam(names: List<String>): MutableList<String>{
        val list : MutableList<String> = ArrayList()
        names.toMutableSet().let { it1 -> list.addAll(it1) }
        val randomElements2 = list.asSequence().shuffled().take((names.size.div(2))).toList()
        list.removeIf { i -> randomElements2.contains(i) }
        return list
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

    fun getPlayerIdByName(name: String): Int{
        var id = 0
        viewModelScope.launch(Dispatchers.IO) {
            id = repository.getPlayerIdByName(name)
        }
        return id
    }

    fun getPlayerByName(name: String): Player?{
        _updateAdapters.postValue(updateAdapters.value != true)
        return repository.getPlayerByName(name)
    }

}