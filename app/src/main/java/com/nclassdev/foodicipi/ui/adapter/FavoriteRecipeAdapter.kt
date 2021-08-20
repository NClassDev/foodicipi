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
import com.nclassdev.foodicipi.domain.model.FavoriteRecipe
import com.nclassdev.foodicipi.ui.FavoriteFragmentDirections
import kotlinx.android.synthetic.main.favorite_item.view.*

class FavoriteRecipeAdapter (private val context: Context, private val favoriteList: List<FavoriteRecipe>):
RecyclerView.Adapter<BaseViewHolder<*>>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        return FavoriteViewHolder(LayoutInflater.from(context).inflate(R.layout.favorite_item,parent, false ))
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when(holder){
            is FavoriteViewHolder -> holder.bind(favoriteList[position], position)
        }
    }

    override fun getItemCount(): Int {
        return  favoriteList.size
    }

    inner class FavoriteViewHolder(itemView: View): BaseViewHolder<FavoriteRecipe>(itemView){
        override fun bind(item: FavoriteRecipe, position: Int) {
            itemView.apply {
                favorite_recipe_title.text = item.dishName
                favorite_recipe_credit.text = item.creditText

                Glide.with(context).load(item.dishImageUrl)
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(favorite_recipe_image)

                itemView.setOnClickListener {
                    val action = FavoriteFragmentDirections.actionFavoriteFragmentToDetailFragment(item.dishId)
                    itemView.findNavController().navigate(action)
                }
            }
        }
    }





}