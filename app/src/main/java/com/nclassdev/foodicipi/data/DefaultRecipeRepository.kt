package com.nclassdev.foodicipi.data

import android.util.Log
import com.nclassdev.foodicipi.data.source.local.LocalDataSource
import com.nclassdev.foodicipi.data.source.remote.network.ApiResponse
import com.nclassdev.foodicipi.data.source.remote.NetworkDataSource
import com.nclassdev.foodicipi.domain.model.*
import com.nclassdev.foodicipi.utils.DataMapper
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class DefaultRecipeRepository @Inject constructor(
    private val networkDataSource: NetworkDataSource,
    private val localDataSource: LocalDataSource
) : RecipeRepository {

    override fun getRecipeGistList(): Flow<Resource<List<RecipeGist>>> = flow{
        emit(Resource.Loading())
        when (val apiResponse = networkDataSource.getRandomRecipes().first()) {
            is ApiResponse.Success -> {
                val data = apiResponse.data
                Log.d("AnyRepo", data.toString())
                emit(Resource.Success(DataMapper.mapGetRecipeInformationResponseToRecipeGist(data)))
            }
            is ApiResponse.Empty -> {
                emit(Resource.Success<List<RecipeGist>>(listOf()))
            }
            is ApiResponse.Error -> {
                emit(Resource.Error<List<RecipeGist>>(apiResponse.errorMessage))
            }
        }
    }

    override fun getRecipesByIntolerances(query: String, intolerance:String): Flow<Resource<List<RecipeByIntolerance>>> = flow{
        emit(Resource.Loading())
        when( val apiResponse= networkDataSource.getRecipesByIntolerances(query, intolerance).first()){
            is ApiResponse.Success -> {
                val data = apiResponse.data
                emit(Resource.Success(DataMapper.mapGetRecipeByIntolerance(data)))
            }

            is ApiResponse.Empty -> {
                emit(Resource.Success<List<RecipeByIntolerance>>(listOf()))
            }

            is ApiResponse.Error -> {
                emit(Resource.Error<List<RecipeByIntolerance>> (apiResponse.errorMessage) )
            }
        }
    }

    override fun getRecipeFull(id: Int):  Flow<Resource<RecipeFull>> = flow{
        emit(Resource.Loading())
        when ( val apiResponse = networkDataSource.getSingleDetailRecipes(id).first()) {
            is ApiResponse.Success -> {
                val data = apiResponse.data
                emit(Resource.Success(DataMapper.changeGetRecipeInformationResponseToRecipeFull(data)))
            }

            is ApiResponse.Empty -> {
                // Never Expected To Reach This
                emit(Resource.Error<RecipeFull>("Something is wrong"))
            }
            is ApiResponse.Error -> {
                emit(Resource.Error<RecipeFull>(apiResponse.errorMessage))
            }
        }
    }

    override fun getFavoriteRecipeList(): Flow<List<FavoriteRecipe>> {
        return localDataSource.getAllFavoriteRecipe().map {
            DataMapper.mapFavoriteRecipeEntityToFavoriteRecipe(it)
        }
    }

    override suspend fun setFavoriteRecipe(recipe: RecipeFull) {
        localDataSource.setFavoriteRecipe(DataMapper.changeRecipeFullToFavoriteRecipeEntity(recipe))
    }

    override suspend fun removeFavoriteRecipe(recipeId: Int) {
        localDataSource.removeFavoriteRecipe(recipeId)
    }

    override fun checkRecipeOnFavorite(id: Int): Flow<Boolean> = flow {
        val data = localDataSource.getSingleFavoriteRecipe(id).first()
        if (data != null) {
            emit(true)
        } else {
            emit(false)
        }
    }

}