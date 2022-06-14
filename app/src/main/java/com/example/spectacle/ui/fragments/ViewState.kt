package com.example.spectacle.ui.fragments

sealed class ViewState {
    object MovieNotFound: ViewState()
    object GeneralError: ViewState()
}
