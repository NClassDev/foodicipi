package com.nclassdev.foodicipi.domain.model.usecase

import com.nclassdev.foodicipi.domain.model.FavoriteRecipe
import com.nclassdev.foodicipi.domain.model.RecipeFull
import com.nclassdev.foodicipi.domain.model.RecipeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalInteractor @Inject constructor(private val repository: RecipeRepository) : LocalUseCase {

    override suspend fun setFavoriteRecipeGist(recipe: RecipeFull) = repository.setFavoriteRecipe(recipe)

    override suspend fun removeFavoriteRecipeGist(recipeId: Int) = repository.removeFavoriteRecipe(recipeId)

    override fun getFavoriteRecipeGistList(): Flow<List<FavoriteRecipe>> = repository.getFavoriteRecipeList()

    override fun checkRecipeOnFavorite(id: Int): Flow<Boolean> = repository.checkRecipeOnFavorite(id)

}