package com.khaledamin.plantsapp.ui.plants

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.annotation.RequiresExtension
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.khaledamin.plantsapp.R
import com.khaledamin.plantsapp.databinding.FragmentPlantsBinding
import com.khaledamin.plantsapp.datasource.local.PlantDatabase
import com.khaledamin.plantsapp.datasource.local.PlantEntity
import com.khaledamin.plantsapp.datasource.local.PlantRepo
import com.khaledamin.plantsapp.datasource.remote.Api
import com.khaledamin.plantsapp.datasource.remote.RepoImpl
import com.khaledamin.plantsapp.datasource.remote.UseCases
import com.khaledamin.plantsapp.ui.base.BaseFragment
import com.khaledamin.plantsapp.util.provideRetrofitInstance


@Suppress("UNCHECKED_CAST")
class PlantsFragment : BaseFragment<FragmentPlantsBinding>(), PlantsCallback, TabCallback {
    override val layout: Int
        get() = R.layout.fragment_plants

    private lateinit var plantsAdapter: PlantsAdapter
    private lateinit var tabsAdapter: TabsAdapter
    private lateinit var layoutManager: LinearLayoutManager
    private val map = mutableMapOf(
        "All" to "all",
        "Palestine" to "pal",
        "Sudan" to "sud",
        "Myanmar" to "mya",
        "Transcaucasia" to "tcs",
        "Uzbekistan" to "uzb",
    )
    private var zone = "all"
    private var page = 1


    private val viewModel: PlantsViewModel by viewModels(factoryProducer = {
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return PlantsViewModel(
                    useCases = UseCases(
                        RepoImpl(provideRetrofitInstance().create(Api::class.java)),
                        PlantRepo(PlantDatabase.getDatabase(requireContext()).plantDao())
                    ),
                    plantDatabase = PlantDatabase.getDatabase(requireContext())
                ) as T
            }
        }
    })

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        plantsAdapter = PlantsAdapter(ArrayList(), this)
        tabsAdapter = TabsAdapter(
            data = map, this
        )
        layoutManager = LinearLayoutManager(requireContext())
    }

    @RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.plantsList.adapter = plantsAdapter
        viewBinding.plantsList.layoutManager = LinearLayoutManager(requireContext())
        viewBinding.tabs.adapter = tabsAdapter
        viewBinding.tabs.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        viewModel.getPlantsByZone(zone, page)
//        viewBinding.plantsList.addOnScrollListener(object : OnScrollListener() {
//            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                super.onScrolled(recyclerView, dx, dy)
//                itemCount = layoutManager.itemCount
//                lastItemPosition = layoutManager.findLastVisibleItemPosition()
//                if (lastItemPosition == itemCount - 1){
//                    viewModel.getPlantsByZone(zone, page++)
//                }
//            }
//        })
    }

    override fun setupObservers() {
        viewModel.showProgress.observe(viewLifecycleOwner) { progress ->
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

    override fun onPlantClicked(plant: PlantEntity) {
        findNavController().navigate(
            PlantsFragmentDirections.actionPlantsFragmentToPlantDetailsFragment(
                plant
            )
        )
    }

    @SuppressLint("NewApi")
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onTabClicked(initialZone: String) {
        viewBinding.tabs.scrollToPosition(map.keys.stream().toList().indexOf(zone))
        zone = initialZone
        viewModel.getPlantsByZone(zone, page)
    }
}