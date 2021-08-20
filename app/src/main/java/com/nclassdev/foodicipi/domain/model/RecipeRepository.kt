package com.nclassdev.foodicipi.domain.model

import com.nclassdev.foodicipi.data.Resource
import kotlinx.coroutines.flow.Flow

//Implementation
interface RecipeRepository {
   fun getRecipeGistList(): Flow<Resource<List<RecipeGist>>>

   fun getRecipesByIntolerances(query:String, intolerance:String): Flow<Resource<List<RecipeByIntolerance>>>

   fun getRecipeFull(id: Int): Flow<Resource<RecipeFull>>

   // For FavoriteFragment
   fun getFavoriteRecipeList(): Flow<List<FavoriteRecipe>>
   suspend fun setFavoriteRecipe(recipe: RecipeFull)
   suspend fun removeFavoriteRecipe(recipeId: Int)
   fun checkRecipeOnFavorite(id: Int): Flow<Boolean>



}