package com.example.spectacle.data.model.categories

import com.google.gson.annotations.SerializedName

data class CategoriesResponse(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val genreName: String
)