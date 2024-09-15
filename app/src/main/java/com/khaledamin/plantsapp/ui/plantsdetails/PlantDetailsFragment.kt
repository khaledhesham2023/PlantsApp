package com.khaledamin.plantsapp.ui.plantsdetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.khaledamin.plantsapp.R
import com.khaledamin.plantsapp.databinding.FragmentPlantDetailsBinding
import com.khaledamin.plantsapp.datasource.local.PlantEntity
import com.khaledamin.plantsapp.datasource.remote.Api

class PlantDetailsFragment : Fragment() {

    private lateinit var plant: PlantEntity
    private lateinit var viewBinding: FragmentPlantDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        viewBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_plant_details, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        plant = PlantDetailsFragmentArgs.fromBundle(requireArguments()).plant
        viewBinding.plant = plant
        viewBinding.moreButton.setOnClickListener {
            viewBinding.webView.webViewClient = WebViewClient()
            viewBinding.webView.loadUrl(Api.WIKIPEDIA_URL + plant.scientificName)
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,object :OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        })
    }

}