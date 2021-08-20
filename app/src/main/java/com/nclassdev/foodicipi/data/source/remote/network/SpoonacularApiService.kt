package com.nclassdev.foodicipi.data.source.remote.network

import com.nclassdev.foodicipi.data.source.network.remote.response.GetRecipeInformationResponse
import com.nclassdev.foodicipi.data.source.remote.response.GetRandomRecipesResponse
import com.nclassdev.foodicipi.data.source.remote.response.GetRecipesByIntolerancesResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SpoonacularApiService {
    @GET("random")
    suspend fun getRandomRecipes(
        @Query("number") resultLimit: Int
    ): GetRandomRecipesResponse

    @GET("complexSearch")
    suspend fun getRecipesByIntolerances(
        @Query("query") query: String?,
        @Query("intolerances") intolerances: String,
    ): GetRecipesByIntolerancesResponse

    @GET("{id}/information")
    suspend fun getRecipeInformation(
        @Path("id") id: Int,
        @Query("includeNutrition") isIncludeNutrition: Boolean
    ): GetRecipeInformationResponse
}