package com.example.spectacle.data.mappers

import com.example.spectacle.data.model.user.UserResponse
import com.example.spectacle.domain.AccountStates

class UserResponseMapper {
    fun map(account: AccountStates): UserResponse {
        return UserResponse(
            id = account.id,
            favorite = account.favorite,
            rated = account.rated,
            watchlist = account.watchlist
        )
    }
}
