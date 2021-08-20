package com.nclassdev.foodicipi.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.FragmentFactory
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.nclassdev.foodicipi.R
import com.nclassdev.foodicipi.databinding.FragmentFavoriteBinding
import com.nclassdev.foodicipi.presentation.FavoriteViewModel
import com.nclassdev.foodicipi.ui.adapter.FavoriteRecipeAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private val favoriteViewModel by activityViewModels<FavoriteViewModel>()
    private lateinit var favoriteRecipeAdapter: FavoriteRecipeAdapter

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get () = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observerData()
        setupRecyclearView()
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)

        return binding.root
    }

    private fun observerData() {
        favoriteViewModel.favoriteMediatorLiveData.observe(viewLifecycleOwner){ favoriteList ->
            favoriteList?.let {
                if(favoriteList.isNotEmpty()){
                    binding.progressBar.visibility = View.GONE
                    binding.rcyclviewFavorites.adapter = FavoriteRecipeAdapter(requireContext(), it)
                }else{
                    binding.progressBar.visibility = View.VISIBLE
                }
            }
        }
    }

    private fun setupRecyclearView(){
        binding.rcyclviewFavorites.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }


}