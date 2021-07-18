package com.nclassdev.foodicipi.domain.model.usecase

import com.nclassdev.foodicipi.data.Resource
import com.nclassdev.foodicipi.domain.model.RecipeGist
import com.nclassdev.foodicipi.domain.model.RecipeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RemoteInteractor @Inject constructor (private val repository: RecipeRepository) :RemoteUseCase{
    override fun getRecipeGistList(): Flow<Resource<List<RecipeGist>>> = repository.getRecipeGistList()
}