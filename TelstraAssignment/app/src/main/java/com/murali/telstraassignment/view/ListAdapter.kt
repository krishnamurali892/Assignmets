package com.murali.telstraassignment.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.murali.telstraassignment.BR
import com.murali.telstraassignment.R
import com.murali.telstraassignment.databinding.LayoutItemBinding
import com.murali.telstraassignment.model.Item

class ListAdapter(var context: Context, var itemList:List<Item>): RecyclerView.Adapter<ListAdapter.ItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding: LayoutItemBinding = DataBindingUtil.inflate(LayoutInflater.from(context),
            R.layout.layout_item, parent,false)
        return ItemViewHolder(binding)
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(itemList.get(position))
    }

    open class ItemViewHolder(val itemBinding: LayoutItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root){
        fun bind(data: Any){
            itemBinding.setVariable(BR.item, data)
            itemBinding.executePendingBindings()
        }
    }

}