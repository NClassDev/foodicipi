package com.nclassdev.foodicipi.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.nclassdev.foodicipi.R
import com.nclassdev.foodicipi.data.Resource
import com.nclassdev.foodicipi.domain.model.RecipeGist
import com.nclassdev.foodicipi.presentation.MainViewModel
import com.nclassdev.foodicipi.ui.adapter.MainAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_main.*

@AndroidEntryPoint
class MainFragment : Fragment(), MainAdapter.OnRecipeClickListener {

    private val viewModel by activityViewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclearView()
        setupObservers()

    }

    private fun setupRecyclearView(){
        rcyclview_recipes.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }

    private fun setupObservers(){
        viewModel.homeMediatorLiveData.observe(viewLifecycleOwner, Observer {
            when(it){
                is Resource.Loading ->{
                    progressBar.visibility = View.VISIBLE
                }

                is Resource.Success->{
                    progressBar.visibility = View.GONE
                    rcyclview_recipes.adapter = MainAdapter(requireContext(), it.data!!, this)
                }
            }
        })
    }

    override fun onRecipeClick(recipe: RecipeGist, position: Int) {

    }


}