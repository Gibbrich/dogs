package com.github.gibbrich.dogs.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class ConstantValueAdapter<T, VH : RecyclerView.ViewHolder>(
    val items: MutableList<T>
) : RecyclerView.Adapter<VH>() {

    final override fun getItemCount() = items.size

    final override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        return createHolder(LayoutInflater.from(parent.context).inflate(lineResourceId, parent, false))
    }

    abstract fun createHolder(view: View): VH

    abstract val lineResourceId: Int

    override fun onBindViewHolder(holder: VH, position: Int) {
        bind(holder, items[position],position)
    }

    abstract fun bind(holder: VH, item: T, position: Int)

    fun addDataToStart(data: List<T>) {
        items.addAll(0, data)
        notifyItemRangeChanged(0, data.size)
    }

    fun setData(data: List<T>) {
        items.clear()
        items.addAll(data)
    }
}