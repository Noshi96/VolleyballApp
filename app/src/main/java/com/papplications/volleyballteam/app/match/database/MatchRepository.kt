package com.papplications.volleyballteam.app.match.database

import androidx.lifecycle.LiveData
import com.papplications.volleyballteam.app.match.model.*
import com.papplications.volleyballteam.app.match.model.Set
import com.papplications.volleyballteam.app.player.database.PlayerDao
import java.sql.Date

class MatchRepository(private val matchDao: MatchDao, private val playerDao: PlayerDao) {

    val getAllMatches: LiveData<List<Match>> = matchDao.getAllMatches()
    val getAllMatchesWithSets: LiveData<List<MatchWithSets>> = matchDao.getAllMatchesWithSets()
    val getAllSets: LiveData<List<Set>> = matchDao.getAllSets()
    val getMatchWithPlayers: LiveData<List<MatchWithPlayers>> = matchDao.getMatchWithPlayers()

    suspend fun addTeamA(teamA: String) {
        matchDao.addTeamA(teamA)
    }

    suspend fun addTeamB(teamB: String) {
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

    suspend fun addMatches(item: List<Match>) {
        matchDao.addMatches(item = item)
    }

    suspend fun addMatchPlayerCrossRef(item: List<MatchPlayerCrossRef>) {
        matchDao.addMatchPlayerCrossRef(item = item)
    }

    fun getMatchWithPlayers(matchId: Int): LiveData<List<MatchWithPlayers>> {
        return matchDao.getMatchWithPlayers(matchId = matchId)
    }

    fun getPlayerWithMatches(playerId: Int): LiveData<List<PlayerWithMatches>> {
        return matchDao.getPlayerWithMatches(playerId = playerId)
    }
}