package com.nclassdev.foodicipi.presentation

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.nclassdev.foodicipi.data.Resource
import com.nclassdev.foodicipi.domain.model.RecipeFull
import com.nclassdev.foodicipi.domain.model.usecase.LocalUseCase
import com.nclassdev.foodicipi.domain.model.usecase.RemoteUseCase
import kotlinx.coroutines.launch

class DetailViewModel @ViewModelInject constructor (private val remoteUseCase: RemoteUseCase,
                                                    private val localUseCase: LocalUseCase
): ViewModel(){

    private var currentRecipeId = -1
    private var currentRemoteSource: LiveData<Resource<RecipeFull>>? = null
    private var currentRecipeData: RecipeFull? = null
    private var currentFavoriteStatusIsFavorite = false
    private var currentLocalSource: LiveData<Boolean>? = null


    val detailRemoteDataMediatorLiveData = MediatorLiveData<Resource<RecipeFull>>()
    val detailLocalDataMediatorLiveData = MediatorLiveData<Boolean>()

    fun getDetailRecipe(recipeId: Int){
        if(recipeId != currentRecipeId){
            currentRemoteSource?.run{
                detailRemoteDataMediatorLiveData.removeSource(this)
            }

            if (recipeId == -1){
                detailRemoteDataMediatorLiveData.value = Resource.Error("Error: Cannot Find Such Recipe")
                currentRecipeId = recipeId
            } else{
                getRecipeById(recipeId)
                getFavoriteStatusById(recipeId)
            }

        }
    }


    fun toggleFavorite(){
        viewModelScope.launch {
            currentRecipeData?.let{
                if (currentFavoriteStatusIsFavorite) {
                    localUseCase.removeFavoriteRecipeGist(it.dishId)
                } else {
                    localUseCase.setFavoriteRecipeGist(it)
                }
                updateFavoriteStatus()            }
        }
    }

    private fun getRecipeById(recipeId: Int) {
        currentRemoteSource = remoteUseCase.getRecipeFull(recipeId).asLiveData()
        currentRemoteSource?.run{
            detailRemoteDataMediatorLiveData.addSource(this){
                if(it is Resource.Success){
                    currentRecipeId = recipeId
                    currentRecipeData = it.data
                }
                detailRemoteDataMediatorLiveData.value =it
            }
        }
    }

    private fun getFavoriteStatusById(recipeId: Int){
        currentLocalSource = localUseCase.checkRecipeOnFavorite(recipeId).asLiveData()
        currentLocalSource?.run {
            detailLocalDataMediatorLiveData.addSource(this){
                detailLocalDataMediatorLiveData.value = it
            }
        }
    }


    private fun updateFavoriteStatus() {
        currentLocalSource?.run {
            detailLocalDataMediatorLiveData.removeSource(this)
        }
        currentRecipeData?.let {
            currentLocalSource = localUseCase.checkRecipeOnFavorite(it.dishId).asLiveData()
        }
        currentLocalSource?.run {
            detailLocalDataMediatorLiveData.addSource(this) {
                detailLocalDataMediatorLiveData.value = it
                currentFavoriteStatusIsFavorite = it
            }
        }
    }
}