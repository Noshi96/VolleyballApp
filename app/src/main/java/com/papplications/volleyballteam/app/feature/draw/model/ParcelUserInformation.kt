package com.papplications.volleyballteam.app.feature.draw.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ParcelUserInformation(
    val names: List<String>,
    val tempNames: List<String>
) : Parcelable