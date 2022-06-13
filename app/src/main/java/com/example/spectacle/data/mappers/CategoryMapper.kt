package com.example.spectacle.data.mappers

import com.example.spectacle.data.model.categories.CategoriesResponse
import com.example.spectacle.domain.Category

class CategoryMapper {
    fun map(genresResponseList: List<CategoriesResponse>): List<Category> {
        val genres = mutableListOf<Category>()
        genresResponseList.forEach {
            val genre = Category(
                id = it.id,
                name = it.genreName
            )
            genres.add(genre)
        }
        return genres
    }
}
