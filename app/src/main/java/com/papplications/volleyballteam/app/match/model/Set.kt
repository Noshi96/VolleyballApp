package com.papplications.volleyballteam.app.match.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "set_table")
data class Set(
    @PrimaryKey(autoGenerate = true)
    val setId: Int,
    val matchId: Int,
    val teamAScore: Int,
    val teamBScore: Int,
)