package com.rg.rsunflower.adapter

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rg.rsunflower.R
import com.rg.rsunflower.data.Habit
import com.rg.rsunflower.databinding.ListItemHabitBinding
import com.rg.rsunflower.utilities.RCallback
import java.util.*

/**
 * Create by roger
 * on 2019/10/12
 */
class HabitAdapter constructor(val deleteCallback: RCallback<Int>) :
        ListAdapter<Habit, HabitAdapter.ViewHolder>(HabitDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ListItemHabitBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val habit = getItem(position)
        holder.apply {
            bind(createOnClickListener(habit.habitId), habit)
            itemView.tag = habit
        }
        holder.itemView.findViewById<TextView>(R.id.tv_last_watering_day).text = ("" + habit.lastWateringDate.get(
            Calendar.MONTH) + "/" +  habit.lastWateringDate.get(Calendar.DATE))
        holder.itemView.findViewById<TextView>(R.id.tv_current_state).text = habit.level.toString()
        holder.itemView.findViewById<ImageView>(R.id.iv_delete).setOnClickListener {
            deleteCallback.onResponse(habit.habitId)
        }
    }

    private fun createOnClickListener(plantId: Int): View.OnClickListener {
        return View.OnClickListener {

        }
    }

    class ViewHolder(
        private val binding: ListItemHabitBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(listener: View.OnClickListener, cHabit: Habit) {
            with(binding) {
                clickListener = listener
                habit = cHabit
            }
        }
    }
}

private class HabitDiffCallback : DiffUtil.ItemCallback<Habit>() {
    override fun areItemsTheSame(oldItem: Habit, newItem: Habit): Boolean {
        return oldItem.habitId == newItem.habitId
    }

    override fun areContentsTheSame(oldItem: Habit, newItem: Habit): Boolean {
        return oldItem.habitName == newItem.habitName
    }

}