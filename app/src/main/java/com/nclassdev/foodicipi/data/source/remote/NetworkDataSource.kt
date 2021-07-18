package com.nclassdev.foodicipi.data.source.remote

import android.util.Log
import com.nclassdev.foodicipi.data.source.network.remote.response.GetRecipeInformationResponse
import com.nclassdev.foodicipi.data.source.remote.network.ApiResponse
import com.nclassdev.foodicipi.data.source.remote.network.SpoonacularApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NetworkDataSource @Inject constructor(
    private val spoonacularApiService: SpoonacularApiService
) {
//    suspend fun getRandomRecipes() : Flow<List<GetRecipeInformationResponse>> =
//        callbackFlow {
//            offer(
//                ApiResponse.Success(spoonacularApiService.getRandomRecipes(15)?.recipes) as List<GetRecipeInformationResponse>
//            )
//            awaitClose { close() }
//        }



    suspend fun getRandomRecipes(): Flow<ApiResponse<List<GetRecipeInformationResponse>>> {
        return flow {
            try {
                val response = spoonacularApiService.getRandomRecipes(15)
                val recipes = response.recipes
                if (recipes.isNotEmpty()) {
                    Log.d("RemoteDataSource: ", recipes.toString())
                    emit(ApiResponse.Success(recipes))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (exception: Exception) {
                emit(ApiResponse.Error("Something is wrong, Error: $exception"))
            }
        }.flowOn(Dispatchers.IO)
    }

//        suspend fun getRandomRecipes(): Flow<Resource.Success<GetRandomRecipesResponse>> =
//            callbackFlow {
//                offer(
//                    Resource.Success(
//                        spoonacularApiService.getRandomRecipes(15)
//                    )
//                )
//                awaitClose { close() }
//            }


}