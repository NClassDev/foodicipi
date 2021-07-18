package com.nclassdev.foodicipi.data

import android.util.Log
import com.nclassdev.foodicipi.data.source.remote.network.ApiResponse
import com.nclassdev.foodicipi.data.source.remote.NetworkDataSource
import com.nclassdev.foodicipi.domain.model.RecipeGist
import com.nclassdev.foodicipi.domain.model.RecipeRepository
import com.nclassdev.foodicipi.utils.DataMapper
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class DefaultRecipeRepository @Inject constructor(
    private val networkDataSource: NetworkDataSource
) : RecipeRepository {
//    override suspend fun getRecipeGistList(): Flow<Resource<List<RecipeGist>>> = flow{
//        emit(Resource.Loading())
//        when (val apiResponse = networkDataSource.getRandomRecipes().first()) {
//            is ApiResponse.Success -> {
//                val data = apiResponse.data
//                Log.d("DefaultRepo", data.toString())
//                emit(Resource.Success(DataMapper.mapGetRecipeInformationResponseToRecipeGist(data)))
//            }
//            is ApiResponse.Empty -> {
//                emit(Resource.Success<List<RecipeGist>>(listOf()))
//            }
//            is ApiResponse.Error -> {
//                emit(Resource.Error<List<RecipeGist>>(apiResponse.errorMessage))
//            }
//        }
//    }

    override fun getRecipeGistList(): Flow<Resource<List<RecipeGist>>> = flow{
        emit(Resource.Loading())
        when (val apiResponse = networkDataSource.getRandomRecipes().first()) {
            is ApiResponse.Success -> {
                val data = apiResponse.data
                Log.d("AnyRepo", data.toString())
                emit(Resource.Success(DataMapper.mapGetRecipeInformationResponseToRecipeGist(data)))
            }
            is ApiResponse.Empty -> {
                emit(Resource.Success<List<RecipeGist>>(listOf()))
            }
            is ApiResponse.Error -> {
                emit(Resource.Error<List<RecipeGist>>(apiResponse.errorMessage))
            }
        }
    }
}