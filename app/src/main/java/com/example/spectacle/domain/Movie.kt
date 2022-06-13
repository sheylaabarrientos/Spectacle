package com.example.spectacle.domain

import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val imgHome: String? = null,
    val id: Int,
    val title: String? = null,
    val rating: Float,
    val categoryIds: List<Int>,
    var inWatchList: Boolean = false,
    var watchedMovie: Boolean = false
)
