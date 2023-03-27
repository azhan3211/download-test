package com.mizani.vidiotest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ActivityViewModel : ViewModel() {

    private val data = MutableLiveData<VidioState>()

    fun download(episode: Episode) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                delay(5000)
                launch(Dispatchers.Main) {
                    data.value = VidioState.Success(episode)
                }
            } catch (e: Exception) {
                launch(Dispatchers.Main) {
                    data.value = VidioState.Failed(episode)
                }
            }
        }
    }

    fun getData(): LiveData<VidioState> = data

}