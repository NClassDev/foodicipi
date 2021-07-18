package com.nclassdev.foodicipi.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nclassdev.foodicipi.R
import com.nclassdev.foodicipi.core.BaseViewHolder
import com.nclassdev.foodicipi.domain.model.RecipeGist
import kotlinx.android.synthetic.main.recipes_item.view.*

class MainAdapter (private val context: Context, private val recipeList: List<RecipeGist>, private val itemClickListener: OnRecipeClickListener) :
    RecyclerView.Adapter<BaseViewHolder<*>>(){

    interface OnRecipeClickListener{
        fun onRecipeClick(recipe: RecipeGist, position: Int)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return MainViewHolder(LayoutInflater.from(context).inflate(R.layout.recipes_item, parent, false))
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder){
            is MainViewHolder -> holder.bind(recipeList[position], position)
        }
    }

    override fun getItemCount(): Int {
        return recipeList.size
    }

    inner class MainViewHolder(itemView: View): BaseViewHolder<RecipeGist>( itemView){
        override fun bind(item: RecipeGist, position: Int) {
            Glide.with(context).load(item.dishImageUrl).into(itemView.recipes_item_dish_image)
            itemView.recipes_item_author_text.text = item.dishName
            itemView.recipes_item_like_count_text.text = item.glutenFree.toString()
//            itemView.recipes_item_like_count_text.text = item.likesCount.toString()
            itemView.recipes_item_time_count_text.text = item.cookingTime.toString()
            itemView.setOnClickListener {
                itemClickListener.onRecipeClick(item, position)
            }
        }
    }
}