package com.nclassdev.foodicipi.domain.model

import com.nclassdev.foodicipi.data.Resource
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {
   fun getRecipeGistList(): Flow<Resource<List<RecipeGist>>>

}