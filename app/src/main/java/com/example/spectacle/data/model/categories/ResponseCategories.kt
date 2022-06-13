package com.example.spectacle.data.model.categories

import com.google.gson.annotations.SerializedName

data class ResponseCategories(
    @SerializedName("genres")
    val genres: List<CategoriesResponse>
)