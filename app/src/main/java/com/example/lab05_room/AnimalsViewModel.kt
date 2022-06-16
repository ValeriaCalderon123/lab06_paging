package com.example.lab05_room

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.lab05_room.data.APIService

class AnimalsViewModel(private val api: APIService) : ViewModel() {
    val pager =
        Pager(PagingConfig(pageSize = 5, enablePlaceholders = false, prefetchDistance = 3)) {
            AnimalsPagingSource(api)
        }.flow.cachedIn(viewModelScope)

}
