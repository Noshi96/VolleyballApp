package com.papplications.volleyballteam.app.match.database

import androidx.lifecycle.LiveData
import com.papplications.volleyballteam.app.match.model.*
import com.papplications.volleyballteam.app.match.model.Set
import com.papplications.volleyballteam.app.match.model.Player
import java.sql.Date

class MatchRepository(private val matchDao: MatchDao) {

    val getAllPlayers: LiveData<List<Player>> = matchDao.getAllPlayers()
    val getAllMatches: LiveData<List<Match>> = matchDao.getAllMatches()
    val getAllMatchesWithSets: LiveData<List<MatchWithSets>> = matchDao.getAllMatchesWithSets()
    val getAllSets: LiveData<List<Set>> = matchDao.getAllSets()
    val getMatchWithPlayers: LiveData<List<MatchWithPlayers>> = matchDao.getMatchWithPlayers()

    suspend fun addTeamA(teamA: String){
        matchDao.addTeamA(teamA)
    }

    suspend fun addTeamB(teamB: String){
        matchDao.addTeamB(teamB)
    }

    fun getMatchesWithSetsTookPartOnDate(date: Date): LiveData<List<MatchWithSets>> {
        return matchDao.getMatchesWithSetsTookPartOnDate(date)
    }

    fun getMatchesTookPartOnDate(date: Date): LiveData<List<Match>> {
        return matchDao.getMatchesTookPartOnDate(date)
    }

    suspend fun addMatch(match: Match) {
        matchDao.addMatch(match)
    }

    suspend fun updateMatch(match: Match) {
        matchDao.updateMatch(match)
    }

    suspend fun deleteMatch(match: Match) {
        matchDao.deleteMatch(match)
    }

    suspend fun addSet(set: Set) {
        matchDao.addSet(set)
    }

    suspend fun updateSet(set: Set) {
        matchDao.updateSet(set)
    }

    suspend fun deleteSet(set: Set) {
        matchDao.deleteSet(set)
    }

    suspend fun addPlayers(players: List<Player>) {
        matchDao.addPlayers(item = players)
    }

    suspend fun addMatches(item: List<Match>) {
        matchDao.addMatches(item = item)
    }

    suspend fun addMatchPlayerCrossRef(item: List<MatchPlayerCrossRef>) {
        matchDao.addMatchPlayerCrossRef(item = item)
    }

    fun getMatchWithPlayers(userId: Int): LiveData<List<MatchWithPlayers>> {
        return matchDao.getMatchWithPlayers(userId)
    }

    fun getPlayerWithMatches(id: Int): LiveData<List<PlayerWithMatches>> {
        return matchDao.getPlayerWithMatches(id)
    }

    suspend fun addPlayer(player: Player) {
        matchDao.addPlayer(player)
    }

    suspend fun updatePlayer(player: Player) {
        matchDao.updatePlayer(player)
    }

    suspend fun deletePlayer(player: Player) {
        matchDao.deletePlayer(player)
    }

    suspend fun deleteAllPlayers() {
        matchDao.deleteAllPlayers()
    }

    fun getPlayerIdByName(name: String): Int {
        return matchDao.getPlayerIdByName(name)
    }

    fun getPlayerByName(name: String): Player? {
        return matchDao.getPlayerByName(name)
    }

}