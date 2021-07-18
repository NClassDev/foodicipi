package com.nclassdev.foodicipi.utils

import com.nclassdev.foodicipi.data.source.network.remote.response.GetRecipeInformationResponse
import com.nclassdev.foodicipi.domain.model.RecipeGist

object DataMapper {
    fun mapGetRecipeInformationResponseToRecipeGist(
        input: List<GetRecipeInformationResponse>
    ): List<RecipeGist> {
        return input.map {
            RecipeGist(
                it.likesCount ?: 0,
                it.dishImageUrl ?: "",
                it.readyInMinutes ?: -1,
                it.dishName ?: "",
                it.dishId ?: -1,
                it.creditText ?: "Anonymous",
                it.glutenFree?: false
            )
        }
    }

}