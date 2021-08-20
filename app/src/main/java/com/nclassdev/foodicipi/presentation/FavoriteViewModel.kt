package com.nclassdev.foodicipi.presentation

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.nclassdev.foodicipi.domain.model.FavoriteRecipe
import com.nclassdev.foodicipi.domain.model.usecase.LocalUseCase
import kotlinx.coroutines.launch

class FavoriteViewModel @ViewModelInject constructor(private val localUseCase: LocalUseCase): ViewModel() {

    private var currentSource: LiveData<List<FavoriteRecipe>> =
        localUseCase.getFavoriteRecipeGistList().asLiveData()

    val favoriteMediatorLiveData = MediatorLiveData<List<FavoriteRecipe>>()

    init {
        favoriteMediatorLiveData.addSource(currentSource){
            favoriteMediatorLiveData.value = it
        }
    }

    fun removeRecipeFromFavorite(recipeId: Int){
        viewModelScope.launch {
            localUseCase.removeFavoriteRecipeGist(recipeId)
        }
    }

}