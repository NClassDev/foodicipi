package com.nclassdev.foodicipi.data.source.local

import androidx.lifecycle.LiveData
import com.nclassdev.foodicipi.data.source.local.entity.FavoriteRecipeEntity
import com.nclassdev.foodicipi.domain.model.FavoriteRecipe
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val favoriteRecipeDao: FavoriteRecipeDao) {

    fun getAllFavoriteRecipe(): Flow<List<FavoriteRecipeEntity>> = favoriteRecipeDao.getAllFavoriteRecipes()

    suspend fun setFavoriteRecipe(favoriteRecipe: FavoriteRecipeEntity) = favoriteRecipeDao.setFavoriteRecipe(favoriteRecipe)

    fun getSingleFavoriteRecipe(recipeId: Int): Flow<FavoriteRecipeEntity?> = favoriteRecipeDao.getSingleFavoriteRecipe(recipeId)

    suspend fun removeFavoriteRecipe(recipeId: Int) = favoriteRecipeDao.removeFavoriteRecipe(recipeId)


//    suspend fun saveFavoriteRecipe(recipe: FavoriteRecipe){
//        favoriteRecipeDao.setFavoriteRecipe(recipe)
//    }
//
//    fun getFavoritesRecipes(): LiveData<List<FavoriteRecipe>>{
//        return favoriteRecipeDao.getAllFavoriteRecipes()
//    }

}