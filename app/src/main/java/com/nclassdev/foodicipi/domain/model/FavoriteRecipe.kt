package com.nclassdev.foodicipi.domain.model

data class FavoriteRecipe(
    val dishImageUrl: String,
    val dishName: String,
    val dishId: Int,
    val creditText: String
)