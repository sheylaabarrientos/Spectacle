package com.example.spectacle.data.model.user

import com.example.spectacle.domain.Rated
import com.google.gson.annotations.SerializedName

class UserResponse(
    @SerializedName("id")
    val id: Int,
    var favorite: Boolean = false,
    val rated: Rated,
    @SerializedName("watchlist")
    var watchlist: Boolean
)