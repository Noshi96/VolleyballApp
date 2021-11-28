package com.papplications.volleyballteam.app.player.viewmodel

import android.app.Application
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

    fun makeUpdateOnAdapters() {
        _updateAdapters.postValue(updateAdapters.value != true)
    }

    fun drawOneTeam(names: List<String>): MutableList<String> {
        return drawService.drawOneTeam(names)
    }

    fun drawTwoPlayers(names: List<String>): MutableList<String> {
        return drawService.drawTwoPlayers(names)
    }

    fun deleteChosenPlayersAndReturnListWithRestPlayers(
        names: List<String>,
        chosenPlayers: MutableList<String>
    ): MutableList<String> {
        return drawService.deleteChosenPlayersAndReturnListWithRestPlayers(names, chosenPlayers)
    }

    fun fillDataBase() {
        deleteAllPlayers()

        val playersNames = listOf(
            "Ania",
            "Bartek",
            "Damian",
            "Jędrzej",
            "Kikis",
            "Klaudia",
            "Koala",
            "Łukasz",
            "Mateusz",
            "Oliwia",
            "Pati",
            "Patison",
            "Paweł",
            "Rafał",
            "Sandra",
            "Sara",
            "Oskar"
        )
        viewModelScope.launch(Dispatchers.IO) {
            playersNames.forEach { playerName ->
                val player = Player(playerId = 0, name = playerName, img = "")
                addPlayer(player)
            }
        }
    }

}