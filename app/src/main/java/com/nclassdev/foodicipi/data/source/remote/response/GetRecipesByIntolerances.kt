package com.nclassdev.foodicipi.data.source.remote.response

import com.google.gson.annotations.SerializedName
import com.nclassdev.foodicipi.data.source.network.remote.response.Ingredient
import com.nclassdev.foodicipi.data.source.network.remote.response.Instruction

data class GetRecipesByIntolerancesResponse (
    @SerializedName(value = "results")
    val results: List<GetRecipesByIntoleranceResponse>
    )