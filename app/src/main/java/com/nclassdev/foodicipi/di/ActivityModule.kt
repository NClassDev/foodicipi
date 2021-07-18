package com.nclassdev.foodicipi.di

import com.nclassdev.foodicipi.data.DefaultRecipeRepository
import com.nclassdev.foodicipi.domain.model.RecipeRepository
import com.nclassdev.foodicipi.domain.model.usecase.RemoteInteractor
import com.nclassdev.foodicipi.domain.model.usecase.RemoteUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent

@Module
@InstallIn(ActivityRetainedComponent::class)
abstract class ActivityModule {
    @Binds
    abstract fun dataSource(impl: DefaultRecipeRepository) : RecipeRepository

    @Binds
    abstract fun bindRepoImpl(remoteInteractor: RemoteInteractor): RemoteUseCase
}