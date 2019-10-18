package com.rg.rsunflower

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.rg.rsunflower.databinding.FragmentMemoryCurveBinding

/**
 * Create by roger
 * on 2019/10/17
 */
class MemoryCurveFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMemoryCurveBinding.inflate(inflater, container, false)
        return binding.root
    }
}