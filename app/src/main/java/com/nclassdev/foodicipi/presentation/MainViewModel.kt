package com.nclassdev.foodicipi.presentation

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.nclassdev.foodicipi.data.Resource
import com.nclassdev.foodicipi.domain.model.RecipeGist
import com.nclassdev.foodicipi.domain.model.usecase.RemoteUseCase

class MainViewModel @ViewModelInject constructor(
//    private val repository: RecipeRepository
    private val remoteUseCase: RemoteUseCase
) : ViewModel(){

    val homeMediatorLiveData = MediatorLiveData<Resource<List<RecipeGist>>>()

    private var currentSource : LiveData<Resource<List<RecipeGist>>> =
    remoteUseCase.getRecipeGistList().asLiveData()


    init {
        homeMediatorLiveData.addSource(currentSource) {
            homeMediatorLiveData.postValue(it)
        }
    }


//    fun getRecipesRandom() =
//        liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
//            emit(Resource.Loading())
//            try {
//                remoteUseCase.getRecipeGistList().collect {
//                    emit(it)
//                    Log.d("MainViewmodel:", it.data.toString())
//                }
//            }catch (e:Exception){
//            }
//        }
}