package com.khaledamin.plantsapp.ui.plants

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.khaledamin.plantsapp.BuildConfig
import com.khaledamin.plantsapp.R
import com.khaledamin.plantsapp.databinding.FragmentPlantsBinding
import com.khaledamin.plantsapp.datasource.Api
import com.khaledamin.plantsapp.datasource.RepoImpl
import com.khaledamin.plantsapp.ui.base.BaseFragment
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class PlantsFragment : BaseFragment<FragmentPlantsBinding>() {
    override val layout: Int
        get() = R.layout.fragment_plants

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

    override fun setupObservers() {
        viewModel.showProgress.observe(viewLifecycleOwner) { progress ->
            if (progress) {
                viewBinding.progress.visibility = View.VISIBLE
            } else {
                viewBinding.progress.visibility = View.GONE
            }
        }
    }
}