package com.papplications.volleyballteam.app.match.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Date

@Entity(tableName = "match_table")
data class Match(
    @PrimaryKey(autoGenerate = true)
    val matchId: Int,
    val teamA: String,
    val teamB: String,
    val matchDate: Date?
)