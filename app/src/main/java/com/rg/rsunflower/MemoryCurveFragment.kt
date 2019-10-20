package com.rg.rsunflower

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.constraintlayout.motion.widget.TransitionAdapter
import androidx.fragment.app.Fragment
import com.rg.rsunflower.databinding.FragmentMemoryCurveBinding
import kotlinx.android.synthetic.main.fragment_memory_curve.*

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setInfinite()
    }

    private fun setInfinite() {
        motionLayout.setTransitionListener(object : TransitionAdapter() {
            override fun onTransitionCompleted(motionLayout: MotionLayout?, currentId: Int) {
                when (currentId) {

                    R.id.offscreenLike, R.id.offscreenPass -> {
                        motionLayout?.progress = 0f
                        motionLayout?.setTransition(R.id.rest, R.id.like)
                    }
                }
            }
        })
    }
}