package com.example.jjobstarbucks.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.jjobstarbucks.databinding.ItemMenuBinding
import com.example.jjobstarbucks.databinding.ItemMenuRowBinding
import com.example.jjobstarbucks.model.MenuItem

class MenuAdapter : ListAdapter<MenuItem, MenuAdapter.ViewHolder>(diffUtil) {
    inner class ViewHolder(private val binding: ItemMenuRowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MenuItem) {
            binding.tvName.text = item.name
            Glide.with(binding.iv).load(item.image).circleCrop().into(binding.iv)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemMenuRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<MenuItem>() {
            override fun areItemsTheSame(oldItem: MenuItem, newItem: MenuItem): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: MenuItem, newItem: MenuItem): Boolean {
                return oldItem == newItem
            }
        }
    }
}