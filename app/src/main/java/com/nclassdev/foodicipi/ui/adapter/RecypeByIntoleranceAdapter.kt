package com.nclassdev.foodicipi.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nclassdev.foodicipi.R
import com.nclassdev.foodicipi.core.BaseViewHolder
import com.nclassdev.foodicipi.domain.model.RecipeByIntolerance
import com.nclassdev.foodicipi.ui.MainFragmentDirections
import kotlinx.android.synthetic.main.recipes_item.view.*

class RecypeByIntoleranceAdapter (private val context: Context, private val recipeByIntoleranceList: List<RecipeByIntolerance>) :
RecyclerView.Adapter<BaseViewHolder<*>> ( ){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return MainViewHolderByIntolerance(LayoutInflater.from(context).inflate(R.layout.recipes_item, parent, false))
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder){
            is RecypeByIntoleranceAdapter.MainViewHolderByIntolerance -> holder.bind(recipeByIntoleranceList[position], position)
        }
    }

    override fun getItemCount(): Int {
        return recipeByIntoleranceList.size
    }

    inner class MainViewHolderByIntolerance(itemView:View): BaseViewHolder<RecipeByIntolerance> (itemView){
        override fun bind(recipe: RecipeByIntolerance, position: Int) {

            itemView.apply {

                itemView.recipes_item_name_text.text = recipe.title

                if(recipe.imageUrl.isNotBlank()){
                    Glide.with(context).load(recipe.imageUrl).into(itemView.recipes_item_dish_image)

                }else{
                    Glide.with(context).load(R.drawable.ic_launcher_background).into(itemView.recipes_item_dish_image)
                }

//                itemView.recipes_item_calories_text.text = recipe.calories.toString()

            }

            itemView.setOnClickListener {
                val action = MainFragmentDirections.actionMainFragmentToDetailFragment(recipe.id)
                itemView.findNavController().navigate(action)
            }

        }
    }

}