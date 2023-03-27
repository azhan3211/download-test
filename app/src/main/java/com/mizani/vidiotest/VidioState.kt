package com.mizani.vidiotest

sealed class VidioState {
    data class Success(val episode: Episode): VidioState()
    data class Failed(val episode: Episode): VidioState()
}