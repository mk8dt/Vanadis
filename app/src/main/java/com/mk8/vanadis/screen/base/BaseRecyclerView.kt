package com.mk8.vanadis.screen.base

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerView<T, VH : BaseViewHolder<T>>(private var list: List<T> = listOf()) : RecyclerView.Adapter<VH>() {

    fun setList(currentList: List<T>) {
        list = currentList
        notifyDataSetChanged()
    }

    abstract fun getViewHolder(parent: ViewGroup): VH

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH = getViewHolder(parent)

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bindData(list[position])
    }
}