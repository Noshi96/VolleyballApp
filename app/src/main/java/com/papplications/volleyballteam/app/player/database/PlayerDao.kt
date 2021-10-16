package com.papplications.volleyballteam.app.player.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.papplications.volleyballteam.app.player.model.Player

@Dao
interface PlayerDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addPlayer(player: Player)

    @Query("Select * FROM player_table ORDER BY id ASC")
    fun fetchAllData(): LiveData<List<Player>>

    @Update
    suspend fun updatePlayer(player: Player)

    @Delete
    suspend fun deletePlayer(player: Player)

    @Query("DELETE FROM player_table")
    suspend fun deleteAllPlayers()

}