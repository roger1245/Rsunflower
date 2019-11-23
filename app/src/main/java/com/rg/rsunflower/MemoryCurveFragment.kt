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

    private var topIndex: Int? = null
    private var bottomIndex: Int? = null


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
//        initSize()
//        onUpDate(view)
        viewModel.habits.observe(this, Observer {
            topIndex = if (it.isNotEmpty()) {
                0;
            }  else {
                -1;
            }
            bottomIndex = if (it.size > 1) {
                1;
            } else {
                -1;
            }
            upDateUI()
        })
    }

    private fun upDateUI() {
        if (topIndex!! >= 0 && bottomIndex!! >= 1) {
            tv_top.text = viewModel.habits.value?.get(topIndex!!)?.habitName
            tv_bottom.text = viewModel.habits.value?.get(bottomIndex!!)?.habitName
            motionLayout.setTransition(R.id.rest, R.id.pass)
            motionLayout.progress = 0f
        } else if (topIndex!! >= 0) {
            tv_top.text = viewModel.habits.value?.get(topIndex!!)?.habitName
            motionLayout.progress = 0f
            motionLayout.setTransition(R.id.lastRest, R.id.lastLike)
        } else {
            motionLayout.setTransition(R.id.finish_empty_state, R.id.finish_empty_state)
            motionLayout.progress = 0f
        }
    }

//    private fun onUpDate(view: View) {
//        viewModel.topCardIndex.observe(this, Observer { it ->
//            view.findViewById<TextView>(R.id.tv_top).text = viewModel.habits.value?.get(it)!!.habitName
//        })
//        viewModel.bottomCardIndex.observe(this, Observer {
//            view.findViewById<TextView>(R.id.tv_bottom).text = viewModel.habits.value?.get(it)!!.habitName
//        })
//    }



    private fun setInfinite() {
        motionLayout.setTransitionListener(object : TransitionAdapter() {
            override fun onTransitionCompleted(motionLayout: MotionLayout?, currentId: Int) {
                when (currentId) {
                    R.id.offscreenLike, R.id.offscreenPass -> {
                        upDateIndex()
                    }
                }
            }
        })
    }

    private fun upDateIndex() {
        if (topIndex == null || bottomIndex == null) return ;
        viewModel.habits.value?.let {
            if (it.size - 1 > bottomIndex!! && bottomIndex!! >= 0) {
                val temp = bottomIndex
                bottomIndex = temp?.plus(1)
            } else if (it.size - 1 <= bottomIndex!!) {
                bottomIndex =  -1;
            }
            if (it.size - 1 > topIndex!! && topIndex!! >= 0) {
                val temp = topIndex
                topIndex = temp?.plus(1)
            } else if (it.size - 1 <= topIndex!!) {
                topIndex =  -1;
            }
        }
        upDateUI()
    }

//    private fun initSize() {
//        viewModel.habits.observe(this, Observer {
//            viewModel.size = it.size
//            viewModel.upDataIndex()
//        })
//    }
}