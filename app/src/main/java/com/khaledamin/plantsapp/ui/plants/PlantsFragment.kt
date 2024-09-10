package com.khaledamin.plantsapp.ui.plants

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.khaledamin.plantsapp.R
import com.khaledamin.plantsapp.databinding.FragmentPlantsBinding
import com.khaledamin.plantsapp.ui.base.BaseFragment


class PlantsFragment : BaseFragment<FragmentPlantsBinding>() {
    override val layout: Int
        get() = R.layout.fragment_plants

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.plants.setOnClickListener {
            findNavController().navigate(PlantsFragmentDirections.actionPlantsFragmentToPlantDetailsFragment())
        }
    }

}