package com.rg.rsunflower

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rg.rsunflower.databinding.FragmentPlantListBinding

/**
 * Create by roger
 * on 2019/10/11
 */
class PlantListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentPlantListBinding.inflate(inflater, container, false)
        return super.onCreateView(inflater, container, savedInstanceState)
    }


}