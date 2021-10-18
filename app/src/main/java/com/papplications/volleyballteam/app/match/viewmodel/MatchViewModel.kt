package com.papplications.volleyballteam.app.match.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.papplications.volleyballteam.app.match.database.MatchDatabase
import com.papplications.volleyballteam.app.match.database.MatchRepository
import com.papplications.volleyballteam.app.match.model.Match
import com.papplications.volleyballteam.app.match.model.MatchWithPlayers
import com.papplications.volleyballteam.app.match.model.MatchWithSets
import com.papplications.volleyballteam.app.match.model.Set
import com.papplications.volleyballteam.app.player.model.Player
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MatchViewModel(application: Application) : AndroidViewModel(application) {

    val getAllMatches: LiveData<List<Match>>
    val getAllMatchesWithSets: LiveData<List<MatchWithSets>>
    val getAllSets: LiveData<List<Set>>
    val getAllMatchWithPlayers: LiveData<List<MatchWithPlayers>>
    private val repository: MatchRepository

    private val _updateAdapters = MutableLiveData<Boolean>()
    val updateAdapters: LiveData<Boolean> = _updateAdapters

    private val _playerByName = MutableLiveData<Player>()
    val playerByName: LiveData<Player> = _playerByName

    init {
        val matchDao = MatchDatabase.getDatabase(application).matchDao()
        val playerDao = MatchDatabase.getDatabase(application).playerDao()
        repository = MatchRepository(matchDao = matchDao, playerDao = playerDao)
        getAllMatches = repository.getAllMatches
        getAllMatchesWithSets = repository.getAllMatchesWithSets
        getAllSets = repository.getAllSets
        getAllMatchWithPlayers = repository.getMatchWithPlayers
    }

    fun addMatch(match: Match) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addMatch(match = match)
        }
    }

    fun updateMatch(match: Match) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateMatch(match = match)
        }
    }

    fun deleteMatch(match: Match) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteMatch(match = match)
        }
    }

    fun addSet(set: Set) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addSet(set = set)
        }
    }

    fun updateSet(set: Set) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateSet(set = set)
        }
    }

    fun deleteSet(set: Set) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteSet(set = set)
        }
    }

}