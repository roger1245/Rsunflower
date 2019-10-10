package com.rg.rsunflower

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.rg.rsunflower.data.GardenPlantingRepository
import com.rg.rsunflower.databinding.FragmentGardenBinding
import com.rg.rsunflower.viewmodels.GardenPlantingListViewModel

/**
 * Create by roger
 * on 2019/9/1
 */
class GardenFragment : Fragment() {

    private val viewModel: GardenPlantingListViewModel = GardenPlantingListViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentGardenBinding.inflate(inflater, container, false)
        return binding.root
    }
}