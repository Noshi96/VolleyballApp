package com.papplications.volleyballteam.app.player.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.papplications.volleyballteam.app.player.model.Player

@Dao
interface PlayerDao {
    @Query("Select * FROM player_table ORDER BY playerId ASC")
    fun getAllPlayers(): LiveData<List<Player>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addPlayer(player: Player)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addPlayers(players: List<Player>)

    @Update
    suspend fun updatePlayer(player: Player)

    @Delete
    suspend fun deletePlayer(player: Player)

    @Query("DELETE FROM player_table")
    suspend fun deleteAllPlayers()

    @Query("Select playerId FROM player_table WHERE name = :name")
    fun getPlayerIdByName(name: String): Int

    @Query("Select * FROM player_table WHERE name = :name")
    fun getPlayerByName(name: String): Player?
}