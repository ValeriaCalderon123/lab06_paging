package com.example.lab05_room

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.lab05_room.data.APIService

class AnimalsViewModelFactory(private val apiService: APIService) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AnimalsViewModel(apiService) as T
    }
}
