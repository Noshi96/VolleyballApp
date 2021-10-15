package com.papplications.volleyballteam.app.match.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.papplications.volleyballteam.app.match.model.Player

@Dao
interface PlayerDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addPlayer(player: Player)

    @Query("Select * FROM player_table ORDER BY id ASC")
    fun fetchAllData(): LiveData<List<Player>>
}