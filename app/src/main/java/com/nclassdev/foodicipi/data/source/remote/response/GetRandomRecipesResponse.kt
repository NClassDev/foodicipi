package com.nclassdev.foodicipi.data.source.remote.response

import com.google.gson.annotations.SerializedName
import com.nclassdev.foodicipi.data.source.network.remote.response.GetRecipeInformationResponse

data class GetRandomRecipesResponse (
    @SerializedName("recipes")
    val recipes: List<GetRecipeInformationResponse>)
