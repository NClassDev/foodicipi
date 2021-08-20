package com.nclassdev.foodicipi.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class GetRecipesByIntoleranceResponse(
    @SerializedName(value = "id")
    val id: Int,

    @SerializedName(value = "title")
    val title: String?,

    @SerializedName(value = "image")
    val imageUrl: String?,

    @SerializedName(value = "calories")
    val calories: Int
)