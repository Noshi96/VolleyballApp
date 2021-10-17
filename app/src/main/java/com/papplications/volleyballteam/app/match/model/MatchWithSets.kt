package com.papplications.volleyballteam.app.match.model

import androidx.room.Embedded
import androidx.room.Relation

data class MatchWithSets(
    @Embedded
    val match: Match,
    @Relation(
        parentColumn = "matchId",
        entityColumn = "setId"
    )
    val sets: List<Set>
)