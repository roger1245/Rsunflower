package com.rg.rsunflower

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.rg.rsunflower.databinding.FragmentAddEditHabitBinding
import com.rg.rsunflower.utilities.InjectorUtils
import com.rg.rsunflower.viewmodels.AddEditHabitViewModel


/**
 * Create by roger
 * on 2019/10/13
 */
class AddEditHabitFragment : Fragment() {


    private val viewModel: AddEditHabitViewModel by viewModels {
        InjectorUtils.provideAddEditHabitFactory(requireContext())
    }




    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentAddEditHabitBinding.inflate(inflater, container, false)
        binding.apply {
            this.viewmodel = viewModel
        }
        binding.lifecycleOwner = this.viewLifecycleOwner

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setUpNavigation()

        val arg = arguments?.getInt("habitId")
        viewModel.start(arg)
    }


    private fun setUpNavigation() {
        viewModel.habitUpdatedEvent.observe(this, Observer<Int> { it ->
            //一个标志位
            if (it == -1) {
                findNavController().popBackStack()
            }
        })
    }


    private fun closeKeyboard(activity: Activity) {
        val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(activity.window.decorView.windowToken, 0)
    }

    override fun onDestroyView() {
        //关闭软键盘
        activity?.let { closeKeyboard(it) }
        super.onDestroyView()
    }
}