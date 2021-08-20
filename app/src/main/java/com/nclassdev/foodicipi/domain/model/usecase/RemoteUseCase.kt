package com.nclassdev.foodicipi.domain.model.usecase

import com.nclassdev.foodicipi.data.Resource
import com.nclassdev.foodicipi.domain.model.RecipeByIntolerance
import com.nclassdev.foodicipi.domain.model.RecipeFull
import com.nclassdev.foodicipi.domain.model.RecipeGist
import kotlinx.coroutines.flow.Flow

interface RemoteUseCase {
   fun getRecipeGistList(): Flow<Resource<List<RecipeGist>>>

   fun getRecipesByIntolerances(query:String, intolerance:String): Flow<Resource<List<RecipeByIntolerance>>>

   fun getRecipeFull(recipeId: Int): Flow<Resource<RecipeFull>>

}