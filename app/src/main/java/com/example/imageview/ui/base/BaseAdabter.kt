package com.example.imageview.ui.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.imageview.BR

interface BaseInteractionListener

abstract class BaseAdapter<T>(
    private val listener: BaseInteractionListener
) : RecyclerView.Adapter<BaseAdapter.BaseViewHolder>() {

    private var items = emptyList<T>()
    abstract val layoutId: Int

    open fun setItems(newItems: List<T>) {
        val diffResult = calculateDiffResult(newItems)
        items = newItems
        diffResult.dispatchUpdatesTo(this)
    }

    open fun areSameItem(oldItem: T, newItem: T): Boolean {
        return oldItem == newItem
    }

    open fun areSameContent(oldItem: T, newItem: T): Boolean {
        return true
    }

    private fun calculateDiffResult(newItems: List<T>): DiffUtil.DiffResult {
        return DiffUtil.calculateDiff(BaseDiffUtil(items, newItems, ::areSameItem, ::areSameContent))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        return createItemViewHolder(parent)
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val currentItem = items[position]
        (holder as? BaseAdapter<*>.ItemViewHolder)?.binding?.apply {
            setVariable(BR.item, currentItem)
            setVariable(BR.listener, listener)
        }
    }

    override fun getItemCount(): Int = items.size

    protected open fun createItemViewHolder(parent: ViewGroup): BaseViewHolder {
        return ItemViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                layoutId,
                parent,
                false
            )
        )
    }

    open inner class ItemViewHolder(val binding: ViewDataBinding) : BaseViewHolder(binding)

    abstract class BaseViewHolder(binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)
}

