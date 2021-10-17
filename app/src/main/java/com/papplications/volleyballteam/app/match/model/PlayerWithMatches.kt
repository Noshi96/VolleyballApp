package com.papplications.volleyballteam.app.match.model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class PlayerWithMatches (
    @Embedded
    private val player: Player,

    @Relation(
        parentColumn = "playerId",
        entityColumn = "matchId",
        associateBy = Junction(MatchPlayerCrossRef::class)
    )
    val matches: List<Match>
)