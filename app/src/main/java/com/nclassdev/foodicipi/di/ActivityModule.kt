package com.nclassdev.foodicipi.di

import android.content.Context
import androidx.room.Room
import com.nclassdev.foodicipi.data.DefaultRecipeRepository
import com.nclassdev.foodicipi.data.source.local.AppDatabase
import com.nclassdev.foodicipi.data.source.local.LocalDataSource
import com.nclassdev.foodicipi.domain.model.RecipeRepository
import com.nclassdev.foodicipi.domain.model.usecase.LocalInteractor
import com.nclassdev.foodicipi.domain.model.usecase.LocalUseCase
import com.nclassdev.foodicipi.domain.model.usecase.RemoteInteractor
import com.nclassdev.foodicipi.domain.model.usecase.RemoteUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class ActivityModule {

    @Binds
    abstract fun dataSource(impl: DefaultRecipeRepository) : RecipeRepository

    @Binds
    abstract fun bindRepoImpl(remoteInteractor: RemoteInteractor): RemoteUseCase

    @Binds
    abstract fun bindLocalDataSourceImpl(localInteractor: LocalInteractor): LocalUseCase



}