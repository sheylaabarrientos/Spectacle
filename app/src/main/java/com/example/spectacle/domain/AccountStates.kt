package com.example.spectacle.domain

data class AccountStates(
    val favorite: Boolean,
    val id: Int,
    val rated: Rated,
    val watchlist: Boolean
)