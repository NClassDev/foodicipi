package com.nclassdev.foodicipi.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nclassdev.foodicipi.R
import com.nclassdev.foodicipi.core.BaseViewHolder
import com.nclassdev.foodicipi.domain.model.Ingredient
import kotlinx.android.synthetic.main.ingredients_item.view.*

class IngredientAdapter (private val context: Context, private val ingredientsList: List<Ingredient> )
    : RecyclerView.Adapter<BaseViewHolder<*>> ( ){


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return  MainViewHolderIngredients(LayoutInflater.from(context).inflate(R.layout.ingredients_item, parent, false))

    }

    override fun getItemCount(): Int {
        return ingredientsList.size
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder){
            is IngredientAdapter.MainViewHolderIngredients -> holder.bind(ingredientsList[position], position)
        }
    }

    inner class MainViewHolderIngredients(itemView : View) : BaseViewHolder<Ingredient> (itemView){
        override fun bind(ingredient: Ingredient, position: Int) {
            itemView.apply {

                itemView.ingredient_title.text = ingredient.name

                itemView.ingredient_cuantity.text = ingredient.desc

                var patchIngredient = "https://spoonacular.com/cdn/ingredients_100x100/"
                if(ingredient.imageHalfPath.isNotBlank()){
                    Glide.with(context).load(patchIngredient+ingredient.imageHalfPath).into(itemView.ingredient_image)
                }
            }
        }
    }


}