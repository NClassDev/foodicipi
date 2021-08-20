package com.nclassdev.foodicipi.ui

import android.os.Bundle
import android.text.Html
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.VerifiedInputEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.text.HtmlCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.nclassdev.foodicipi.R
import com.nclassdev.foodicipi.data.Resource
import com.nclassdev.foodicipi.databinding.FragmentDetailBinding
import com.nclassdev.foodicipi.domain.model.RecipeFull
import com.nclassdev.foodicipi.presentation.DetailViewModel
import com.nclassdev.foodicipi.ui.adapter.IngredientAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_detail.*

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private val args: DetailFragmentArgs by navArgs()

    private val detailViewModel: DetailViewModel by activityViewModels<DetailViewModel>()

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()
        setupView()

        binding.fragmentDetailBackIcon.setOnClickListener {
            findNavController().navigate(R.id.action_detailFragment_to_mainFragment)
        }
        
    }

    private fun observeData() {
        detailViewModel.detailRemoteDataMediatorLiveData.observe(viewLifecycleOwner) { resource ->
            if(resource != null){
                when (resource){
                    is Resource.Loading ->{
                        bindRemoteData(resource.data)
                        binding.fragmentDetailProgressbar.visibility = View.VISIBLE
                    }

                    is Resource.Success -> {
                        binding.fragmentDetailProgressbar.visibility = View.GONE
                        binding.fragmentDetailConstrainWrapper.visibility = View.VISIBLE
                        binding.fragmentDetailLlayout.visibility = View.VISIBLE

                        bindRemoteData(resource.data)
                        binding.fragmentDetailRcyviewIntolerances.adapter =
                            resource.data?.let { IngredientAdapter(requireContext(), it.ingredients) }
                    }

                    is Resource.Error -> {
                        Toast.makeText(requireContext(), "${resource.message}", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }

        detailViewModel.detailLocalDataMediatorLiveData.observe(viewLifecycleOwner){favoriteStatus ->
            handleLocalData(favoriteStatus)
        }
    }

    private fun bindRemoteData(recipe: RecipeFull?) {
        recipe?.let {

            fragment_detail_summary.text = HtmlCompat.fromHtml(it.dishInstructions, HtmlCompat.FROM_HTML_MODE_LEGACY)
            fragment_detail_recipe_name.text = it.dishName
            fragment_detail_recipe_author.text = it.creditText

            if (it.dishImageUrl.isNotBlank()) {
                Glide.with(requireContext())
                    .load(it.dishImageUrl)
                    .into(binding.fragmentDetailDishImage)
            } else {
                Glide.with(requireContext())
                    .load(R.drawable.ic_launcher_background)
                    .into(binding.fragmentDetailDishImage)
            }

            if(it.isGlutenFree){
                fragment_detail_gluten_icon.setImageResource(R.drawable.gluten_free)
            }else{
                fragment_detail_gluten_icon.setImageResource(R.drawable.gluten)
            }
        }
    }

    private fun handleLocalData(favoriteStatus: Boolean?) {
        favoriteStatus?.let { isFavorite ->
            if (isFavorite) {
                Glide.with(requireContext())
                    .load(R.drawable.ic_bookmark)
                    .into(binding.fragmentDetailFavoriteIcon)
            } else {
                Glide.with(requireContext())
                    .load(R.drawable.ic_bookmark_border)
                    .into(binding.fragmentDetailFavoriteIcon)
            }
        }
    }



    private fun setupView(){
        val id = args.recipeId
        detailViewModel.getDetailRecipe(id)

        binding.fragmentDetailRcyviewIntolerances.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        binding.fragmentDetailFavoriteIcon.setOnClickListener {
            detailViewModel.toggleFavorite()
        }
    }

}