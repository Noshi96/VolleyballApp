package com.papplications.volleyballteam.app.match.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.papplications.volleyballteam.app.match.model.*
import com.papplications.volleyballteam.app.match.model.Set
import java.sql.Date

@Dao
interface MatchDao {

    @Transaction
    @Query("SELECT * FROM match_table ORDER BY matchId ASC")
    fun getAllMatchesWithSets(): LiveData<List<MatchWithSets>>

    @Query("SELECT * FROM match_table WHERE matchDate = :targetDate")
    fun getMatchesTookPartOnDate(targetDate: Date): LiveData<List<Match>>

    @Query("SELECT * FROM match_table WHERE matchDate = :targetDate")
    fun getMatchesWithSetsTookPartOnDate(targetDate: Date): LiveData<List<MatchWithSets>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addMatch(match: Match)

    @Query("Select * FROM match_table ORDER BY matchId ASC")
    fun getAllMatches(): LiveData<List<Match>>

    @Update
    suspend fun updateMatch(match: Match)

    @Delete
    suspend fun deleteMatch(match: Match)


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addSet(set: Set)

    @Query("Select * FROM set_table ORDER BY setId ASC")
    fun getAllSets(): LiveData<List<Set>>

    @Update
    suspend fun updateSet(set: Set)

    @Delete
    suspend fun deleteSet(set: Set)

    @Transaction
    @Query("SELECT * FROM match_table")
    fun getMatchWithPlayers(): LiveData<List<MatchWithPlayers>>

    @Query("INSERT INTO match_table (teamA) VALUES (:teamA)")
    suspend fun addTeamA(teamA: String)

    @Query("INSERT INTO match_table (teamB) VALUES (:teamB)")
    suspend fun addTeamB(teamB: String)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMatches(item: List<Match>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMatchPlayerCrossRef(item: List<MatchPlayerCrossRef>)

    @Transaction
    @Query("SELECT * FROM match_table WHERE matchId = :matchId")
    fun getMatchWithPlayers(matchId: Int): LiveData<List<MatchWithPlayers>>

    @Transaction
    @Query("SELECT * FROM player_table WHERE playerId = :playerId")
    fun getPlayerWithMatches(playerId: Int): LiveData<List<PlayerWithMatches>>

}