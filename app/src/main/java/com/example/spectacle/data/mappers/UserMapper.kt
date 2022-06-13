package com.example.spectacle.data.mappers

import com.example.spectacle.data.model.user.UserResponse
import com.example.spectacle.domain.AccountStates

class UserMapper {
    fun map(userResponseList: List<UserResponse>): List<AccountStates> {
        val profiles = mutableListOf<AccountStates>()
        userResponseList?.let {
            userResponseList.forEach {
                val profile = AccountStates(
                    id = it.id,
                    favorite = it.favorite,
                    rated = it.rated,
                    watchlist = it.watchlist
                )
                profiles.add(profile)
            }
        }
        return profiles
    }
}
