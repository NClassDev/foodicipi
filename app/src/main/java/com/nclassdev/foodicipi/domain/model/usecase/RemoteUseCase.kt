package com.nclassdev.foodicipi.domain.model.usecase

import com.nclassdev.foodicipi.data.Resource
import com.nclassdev.foodicipi.domain.model.RecipeGist
import kotlinx.coroutines.flow.Flow

interface RemoteUseCase {
   fun getRecipeGistList(): Flow<Resource<List<RecipeGist>>>

}