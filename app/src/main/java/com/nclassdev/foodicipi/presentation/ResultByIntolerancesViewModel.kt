package com.nclassdev.foodicipi.presentation

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.nclassdev.foodicipi.data.Resource
import com.nclassdev.foodicipi.domain.model.RecipeByIntolerance
import com.nclassdev.foodicipi.domain.model.usecase.RemoteUseCase
import kotlinx.coroutines.Dispatchers
import kotlin.random.Random

class ResultByIntolerancesViewModel @ViewModelInject constructor (
    private val remoteUseCase: RemoteUseCase,
    @Assisted private val savedStateHandle: SavedStateHandle

) : ViewModel() {

    private var currentIntolerance = ""
    private var query = ""

    val resultMediatorLiveData = MediatorLiveData<Resource<List<RecipeByIntolerance>>>()

    val listRandom:  List<String> = listOf("salad", "Apple", "fish")


    private val currentQuery = savedStateHandle.getLiveData<String> ( "query",
        listRandom[(listRandom.indices).random()]
    )

    private var currentSource: LiveData<Resource<List<RecipeByIntolerance>>> =
        remoteUseCase.getRecipesByIntolerances(query, currentIntolerance).asLiveData()

    fun setQuery(query: String){
        currentQuery.value = query
    }

    init {
        resultMediatorLiveData.addSource(currentSource){
            resultMediatorLiveData.postValue(it)
        }


    }

    fun getRecypeByIntolerance(query: String, intolerance:String){
        if(currentIntolerance != intolerance){
            currentSource.run {
                resultMediatorLiveData.removeSource(this)
            }
        }
        currentSource = remoteUseCase.getRecipesByIntolerances(query, intolerance).asLiveData()

        currentSource?.run {
            resultMediatorLiveData.addSource(this){
                if(it is Resource.Success){
                    currentIntolerance = intolerance
                }
                resultMediatorLiveData.value = it
            }
        }
    }

}