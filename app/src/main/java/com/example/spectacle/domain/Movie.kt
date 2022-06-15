package com.example.spectacle.domain

data class Movie(
    val imgHome: String? = null,
    val id: Int,
    val title: String? = null,
    val rating: Float,
    val categoryIds: List<Int>,
    var inFavoriteList: Boolean = false
)
