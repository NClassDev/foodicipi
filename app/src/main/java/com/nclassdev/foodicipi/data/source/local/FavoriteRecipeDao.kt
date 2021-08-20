package com.nclassdev.foodicipi.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nclassdev.foodicipi.data.source.local.entity.FavoriteRecipeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteRecipeDao {
    @Query("SELECT * FROM favorite_recipe")
    fun getAllFavoriteRecipes(): Flow<List<FavoriteRecipeEntity>>

    @Query("SELECT * FROM favorite_recipe WHERE recipeId = :recipeId")
    fun getSingleFavoriteRecipe(recipeId: Int): Flow<FavoriteRecipeEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setFavoriteRecipe(favoriteRecipe: FavoriteRecipeEntity)

    @Query("DELETE FROM favorite_recipe WHERE recipeId = :recipeId")
    suspend fun removeFavoriteRecipe(recipeId: Int)
}