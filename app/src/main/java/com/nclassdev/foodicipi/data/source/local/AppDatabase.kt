package com.nclassdev.foodicipi.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nclassdev.foodicipi.data.source.local.entity.FavoriteRecipeEntity

@Database( entities = [FavoriteRecipeEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun favoriteRecipeDao(): FavoriteRecipeDao
}