package com.nclassdev.foodicipi.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.nclassdev.foodicipi.data.Resource
import com.nclassdev.foodicipi.databinding.FragmentMainBinding
import com.nclassdev.foodicipi.domain.model.RecipeByIntolerance
import com.nclassdev.foodicipi.presentation.ResultByIntolerancesViewModel
import com.nclassdev.foodicipi.ui.adapter.RecypeByIntoleranceAdapter
import com.nclassdev.foodicipi.utils.onQueryTextChanged
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_main.*

@AndroidEntryPoint
class MainFragment : Fragment()  {

//    private val viewModel by activityViewModels<MainViewModel>()
    private val viewModelByIntolerance by activityViewModels<ResultByIntolerancesViewModel>()

    private var _binding: FragmentMainBinding? = null
    private val binding get( ) = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclearView()
        setupSearchView()
        setupObservers()

        binding.fragmentMainSearchView.onQueryTextChanged{
            viewModelByIntolerance.setQuery(it)
            viewModelByIntolerance.getRecypeByIntolerance(it,"sesame")

        }


    }

    private fun setupRecyclearView(){
        val listRandom:  List<String> = listOf("salad", "Apple", "fish", "pasta")

        viewModelByIntolerance.getRecypeByIntolerance(listRandom[(listRandom.indices).random()],"sesame")
        binding.rcyclviewRecipes.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }

//    private fun setupObservers(){
//        viewModel.homeMediatorLiveData.observe(viewLifecycleOwner, Observer {
//            when(it){
//                is Resource.Loading ->{
//                    progressBar.visibility = View.VISIBLE
//                }
//
//                is Resource.Success->{
//                    progressBar.visibility = View.GONE
//                    rcyclview_recipes.adapter = MainAdapter(requireContext(), it.data!!, this)
//                }
//            }
//        })
//    }

    private fun setupObservers(){
        viewModelByIntolerance.resultMediatorLiveData.observe(viewLifecycleOwner, Observer {
            when(it){
                is Resource.Loading ->{
                    binding.progressBar.visibility = View.VISIBLE
                }

                is Resource.Success->{
                    binding.progressBar.visibility = View.GONE
//                    rcyclview_recipes.adapter = MainAdapter(requireContext(), it.data!!, this)
                    binding.rcyclviewRecipes.adapter = RecypeByIntoleranceAdapter(requireContext(), it.data!!)

                }
            }
        })
    }

    private fun setupSearchView(){
        binding.fragmentMainSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModelByIntolerance.setQuery(query!!)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }



}