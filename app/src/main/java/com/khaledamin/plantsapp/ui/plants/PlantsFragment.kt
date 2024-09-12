package com.khaledamin.plantsapp.ui.plants

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.khaledamin.plantsapp.BuildConfig
import com.khaledamin.plantsapp.R
import com.khaledamin.plantsapp.databinding.FragmentPlantsBinding
import com.khaledamin.plantsapp.datasource.Api
import com.khaledamin.plantsapp.datasource.RepoImpl
import com.khaledamin.plantsapp.model.response.Plant
import com.khaledamin.plantsapp.ui.base.BaseFragment
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Suppress("UNCHECKED_CAST")
class PlantsFragment : BaseFragment<FragmentPlantsBinding>(), PlantsCallback {
    override val layout: Int
        get() = R.layout.fragment_plants

    private lateinit var plantsAdapter: PlantsAdapter

    private val viewModel: PlantsViewModel by viewModels(factoryProducer = {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return PlantsViewModel(
                    RepoImpl(
                        Retrofit.Builder().baseUrl(Api.BASE_URL)
                            .addConverterFactory(GsonConverterFactory.create()).build()
                            .create(Api::class.java)
                    ), BuildConfig.API_KEY
                ) as T
            }
        }

    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        plantsAdapter = PlantsAdapter(ArrayList(), this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.plantsList.adapter = plantsAdapter
        viewBinding.plantsList.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun setupObservers() {
        viewModel.showProgress.observe(viewLifecycleOwner) { progress ->
            Log.i("TAGGG", BuildConfig.API_KEY)
            if (progress) {
                viewBinding.progress.visibility = View.VISIBLE
            } else {
                viewBinding.progress.visibility = View.GONE
            }
        }

        viewModel.showToastMessage.observe(viewLifecycleOwner) { showToast ->
            if (showToast) {
                Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show()
            }
        }

        viewModel.getPlantsLiveData.observe(viewLifecycleOwner) {
            plantsAdapter.updateDataSet(it)
        }
    }

    override fun onPlantClicked(plant: Plant) {
        findNavController().navigate(
            PlantsFragmentDirections.actionPlantsFragmentToPlantDetailsFragment(
                plant
            )
        )
    }
}