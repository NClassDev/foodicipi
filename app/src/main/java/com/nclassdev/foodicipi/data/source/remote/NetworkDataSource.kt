package com.nclassdev.foodicipi.data.source.remote

import com.nclassdev.foodicipi.data.source.network.remote.response.GetRecipeInformationResponse
import com.nclassdev.foodicipi.data.source.remote.network.ApiResponse
import com.nclassdev.foodicipi.data.source.remote.network.SpoonacularApiService
import com.nclassdev.foodicipi.data.source.remote.response.GetRecipesByIntoleranceResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NetworkDataSource @Inject constructor(
    private val spoonacularApiService: SpoonacularApiService
) {

    suspend fun getRandomRecipes(): Flow<ApiResponse<List<GetRecipeInformationResponse>>> {
        return flow {
            try {
                val response = spoonacularApiService.getRandomRecipes(15)
                val recipes = response.recipes
                if (recipes.isNotEmpty()) {
                    emit(ApiResponse.Success(recipes))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (exception: Exception) {
                emit(ApiResponse.Error("Something is wrong, Error: $exception"))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getRecipesByIntolerances(query: String, intolerances: String) : Flow<ApiResponse<List<GetRecipesByIntoleranceResponse>>>{
        return flow {
            try {
                val response = spoonacularApiService.getRecipesByIntolerances(query, intolerances)
                val recipes = response.results
                if(recipes.isNotEmpty()){
                    emit(ApiResponse.Success(recipes))
                }else{
                    emit(ApiResponse.Empty)
                }
            }catch ( exception: java.lang.Exception){
                emit(ApiResponse.Error("Something is wrong, Error: $exception"))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getSingleDetailRecipes(id: Int): Flow<ApiResponse<GetRecipeInformationResponse>> {
        return flow {
            try {
                val response = spoonacularApiService.getRecipeInformation(id, true)
                emit(ApiResponse.Success(response))
            }catch (exception: Exception){
                emit(ApiResponse.Error("Something is wrong, Error: $exception"))
            }
        }.flowOn(Dispatchers.IO)
    }

}