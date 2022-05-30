package com.example.ourtable.adapter


import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.ourtable.databinding.ItemCookBinding
import com.example.ourtable.model.Row


class CookAdapter : ListAdapter<Row, CookAdapter.CookItemViewHolder>(diffUtil) {

    inner class CookItemViewHolder(private val binding: ItemCookBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(foodModel: Row) {
            binding.titleTextName.text =foodModel.rCPNM.toString()
            Log.d("CookAdapter", foodModel.rCPNM.toString())


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CookAdapter.CookItemViewHolder {
        return CookItemViewHolder(
            ItemCookBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: CookItemViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Row>() {
            override fun areItemsTheSame(oldItem: Row, newItem: Row): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem:Row, newItem: Row): Boolean {
                return oldItem.rCPNM == newItem.rCPNM
            }

        }
    }


}