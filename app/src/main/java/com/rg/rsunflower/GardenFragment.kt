package com.rg.rsunflower

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.Navigation
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.rg.rsunflower.adapter.HabitAdapter
import com.rg.rsunflower.databinding.FragmentGardenBinding
import com.rg.rsunflower.utilities.InjectorUtils
import com.rg.rsunflower.utilities.RCallback
import com.rg.rsunflower.viewmodels.HabitViewModel

/**
 * Create by roger
 * on 2019/9/1
 */
class GardenFragment : Fragment() {

    private val viewModel: HabitViewModel by viewModels {
        InjectorUtils.provideGardenPlantingListViewModelFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentGardenBinding.inflate(inflater, container, false)
        val adapter = HabitAdapter(object : RCallback<Int> {
            override fun onResponse(data: Int) {
                viewModel.delete(data)
            }

        })
        binding.rvHabitList.adapter = adapter
        subscribeUi(adapter, binding)
        return binding.root
    }

    private fun subscribeUi(adapter: HabitAdapter, binding: FragmentGardenBinding) {
        viewModel.habits.observe(viewLifecycleOwner) { result ->
            adapter.submitList(result)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fab = view.findViewById<FloatingActionButton>(R.id.add_habit_fab)
        fab?.setOnClickListener(
            Navigation.createNavigateOnClickListener(
                R.id.action_garden_fragment_to_add_edit_habit_fragment,
                null
            )
        )
    }
}