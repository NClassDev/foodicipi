package com.nclassdev.foodicipi.domain.model

data class RecipeGist(
    val likesCount: Int,
    val dishImageUrl: String,
    val cookingTime: Int,
    val dishName: String,
    val dishId: Int,
    val creditText: String,
    val glutenFree: Boolean,
    val dairyFree: Boolean
)
