package com.nclassdev.foodicipi.domain.model

data class RecipeByIntolerance (
    val id: Int,
    val title: String,
    val imageUrl: String,
    val calories: Int
    )