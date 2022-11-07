package com.example.themeownetapp.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.example.themeownetapp.core.Constants.IMAGE_TYPE_BOTH
import com.example.themeownetapp.domain.repositories.CatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

//Also DI here so we can call the Repository
@HiltViewModel
class CatGalleryViewModel @Inject constructor(private val repository: CatRepository) : ViewModel() {

    //this mutable live data contains the current (initial) query
    private val currentQuery = MutableLiveData(IMAGE_TYPE_BOTH)

    //SwitchMap will helps us change the value of the query
    val images = currentQuery.switchMap { queryString ->
        repository.getCatImages(queryString)
    }

    //Here we can change the image type by changing the query
    fun changeImageType(mimeType: String) {
        currentQuery.value = mimeType
    }
}