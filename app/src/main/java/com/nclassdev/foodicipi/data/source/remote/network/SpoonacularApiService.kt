package com.nclassdev.foodicipi.data.source.remote.network

import com.nclassdev.foodicipi.data.source.remote.response.GetRandomRecipesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface SpoonacularApiService {
    @GET("random")
    suspend fun getRandomRecipes(
        @Query("number") resultLimit: Int
    ): GetRandomRecipesResponse

}