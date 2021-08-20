package com.nclassdev.foodicipi.domain.model.usecase

import com.nclassdev.foodicipi.domain.model.FavoriteRecipe
import com.nclassdev.foodicipi.domain.model.RecipeFull
import kotlinx.coroutines.flow.Flow

interface LocalUseCase {

    suspend fun setFavoriteRecipeGist(recipeFull: RecipeFull)
    suspend fun removeFavoriteRecipeGist(recipeId: Int)

    fun getFavoriteRecipeGistList(): Flow<List<FavoriteRecipe>>

    fun checkRecipeOnFavorite(id: Int): Flow<Boolean>
}