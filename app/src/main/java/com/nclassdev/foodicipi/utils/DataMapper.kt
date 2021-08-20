package com.nclassdev.foodicipi.utils

import com.nclassdev.foodicipi.data.source.local.entity.FavoriteRecipeEntity
import com.nclassdev.foodicipi.data.source.network.remote.response.GetRecipeInformationResponse
import com.nclassdev.foodicipi.data.source.remote.response.GetRecipesByIntoleranceResponse
import com.nclassdev.foodicipi.domain.model.RecipeByIntolerance
import com.nclassdev.foodicipi.domain.model.*

object DataMapper {
    fun mapGetRecipeInformationResponseToRecipeGist(
        input: List<GetRecipeInformationResponse>
    ): List<RecipeGist> {
        return input.map {
            RecipeGist(
                it.likesCount ?: 0,
                it.dishImageUrl ?: "",
                it.readyInMinutes ?: -1,
                it.dishName ?: "",
                it.dishId ?: -1,
                it.creditText ?: "Anonymous",
                it.glutenFree?: false,
                it.dairyFree?: false

            )
        }
    }

    fun mapGetRecipeByIntolerance(
        input: List<GetRecipesByIntoleranceResponse>
    ): List<RecipeByIntolerance>{
        return input.map {
            RecipeByIntolerance(
                it.id ?: 0 ,
            it.title ?: "",
            it.imageUrl ?: "",
                it.calories ?: 0
            )
        }
    }


    fun changeGetRecipeInformationResponseToRecipeFull(
        input: GetRecipeInformationResponse
    ) : RecipeFull {

        val instruction = input.instruction.valueOrEmpty()
        val steps = if (instruction.isNotEmpty()) {
            instruction[0].stepList.map {
                Step(
                    it.number,
                    it.step
                )
            }
        } else {
            listOf()
        }

        val ingredients = input.ingredientList.valueOrEmpty().map {
            Ingredient(
                it.ingredientId,
                it.ingredientImageUrl ?: "",
                it.ingredientName ?: "Not Available",
                it.description ?: "Not Available"
            )
        }

        val nutrition = input.nutrition
        val nutrientList = nutrition?.nutrients?.map {
            Nutrient(
                it.title ?: "Not Available",
                it.amount ?: 0.0,
                it.unit ?: "",
                it.percentOfDailyNeeds ?: 0.0
            )
        }
            ?: listOf()

        return RecipeFull(
            input.likesCount ?: -1,
            input.dishImageUrl ?: "",
            input.readyInMinutes ?: -1,
            input.dishName ?: "",
            input.dishId ?: -1,
            input.creditText ?: "Anonymous",
            input.instructions ?: "",
            steps,
            nutrientList,
            ingredients,
            input.glutenFree ?: false,
            input.dairyFree ?: false,
            input.vegetarian ?: false,
            input.instructions?: ""

        )
    }

    fun mapFavoriteRecipeEntityToFavoriteRecipe(
        input: List<FavoriteRecipeEntity>
    ): List<FavoriteRecipe> {
        return input.map {
            FavoriteRecipe(
                it.recipeImageUrl,
                it.recipeName,
                it.recipeId,
                it.authorCredit
            )
        }
    }

    fun changeRecipeFullToFavoriteRecipeEntity(
        input: RecipeFull
    ): FavoriteRecipeEntity {
        return FavoriteRecipeEntity(
            input.dishId,
            input.dishName,
            input.creditText,
            input.dishImageUrl
        )
    }


}