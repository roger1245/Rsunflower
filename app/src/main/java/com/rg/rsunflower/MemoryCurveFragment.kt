package com.rg.rsunflower

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.motion.widget.TransitionAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.rg.rsunflower.databinding.FragmentMemoryCurveBinding
import com.rg.rsunflower.utilities.InjectorUtils
import com.rg.rsunflower.viewmodels.HabitViewModel
import com.rg.rsunflower.viewmodels.MemoryCurveViewModel
import kotlinx.android.synthetic.main.fragment_memory_curve.*
import org.w3c.dom.Text

/**
 * Create by roger
 * on 2019/10/17
 */
class MemoryCurveFragment : Fragment() {


    private val viewModel: MemoryCurveViewModel by viewModels {
        InjectorUtils.provideMemoryCurveFactory(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMemoryCurveBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setInfinite()
        initSize()
        onUpDate(view)
    }

    fun onUpDate(view: View) {
        viewModel.topCardIndex.observe(this, Observer { it ->
            viewModel.habits.observe(this, Observer {   list ->
                view.findViewById<TextView>(R.id.tv_top).text = list[it].habitName
            })
            Log.d("roger", "viewMode内存地址： " + viewModel.habits.toString())
            Log.d("roger", "viewMode内存地址： " + viewModel.habits.value?.get(it).toString())
        })
        viewModel.bottomCardIndex.observe(this, Observer {
            viewModel.habits.observe(this, Observer {   list ->
                view.findViewById<TextView>(R.id.tv_bottom).text = list[it].habitName
            })
        })
    }



    private fun setInfinite() {
        motionLayout.setTransitionListener(object : TransitionAdapter() {
            override fun onTransitionCompleted(motionLayout: MotionLayout?, currentId: Int) {
                when (currentId) {
                    R.id.offscreenLike, R.id.offscreenPass -> {
                        motionLayout?.progress = 0f
                        motionLayout?.setTransition(R.id.rest, R.id.like)
                        viewModel.upDataIndex()
                    }
                }
            }
        })
    }

    private fun initSize() {
        viewModel.habits.observe(this, Observer {
            viewModel.size = it.size
        })
    }
}