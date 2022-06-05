package com.example.ourtable.adapter


import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ourtable.databinding.ItemCookBinding
import com.example.ourtable.model.Cook
import com.example.ourtable.model.Row


class CookAdapter(private val itemClickedListener: (Row) -> Unit) :
    ListAdapter<Row, CookAdapter.CookItemViewHolder>(diffUtil) {

    inner class CookItemViewHolder(private val binding: ItemCookBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(foodModel: Row) {
            binding.titleTextName.text = foodModel.rCPNM.toString()
            Log.d("CookAdapter", foodModel.rCPNM.toString())

            binding.descriptionTextView.text = foodModel.rCPPARTSDTLS.toString()
            Log.d("descriptionTextView", foodModel.rCPPARTSDTLS.toString())

            binding.root.setOnClickListener {
                itemClickedListener(foodModel)
            }

            Glide
                .with(binding.coverImageView.context)
                .load(foodModel.aTTFILENOMAIN)
                .into(binding.coverImageView)

        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CookAdapter.CookItemViewHolder {
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

            override fun areContentsTheSame(oldItem: Row, newItem: Row): Boolean {
                return oldItem.rCPNM == newItem.rCPNM
            }

        }
    }


}