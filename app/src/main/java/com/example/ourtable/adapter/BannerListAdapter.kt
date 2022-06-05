package com.example.ourtable.adapter


import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ourtable.R


import com.example.ourtable.model.Row

class BannerListAdapter(private val itemClickedListener: (Row) -> Unit): ListAdapter<Row, BannerListAdapter.ItemViewHolder>(differ) {
    inner class ItemViewHolder(val view:View):RecyclerView.ViewHolder(view){
        fun bind(row:Row){
            val thumbnailImageView = view.findViewById<ImageView>(R.id.bannerImageView)

            view.rootView.setOnClickListener {
                itemClickedListener(row)
            }

            Glide
                .with(thumbnailImageView.context)
                .load(row.aTTFILENOMK)
                .into(thumbnailImageView)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ItemViewHolder(inflater.inflate(R.layout.activity_item_banner_viewpage2,parent,false))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    companion object{
        val differ = object :DiffUtil.ItemCallback<Row>(){
            override fun areItemsTheSame(oldItem: Row, newItem: Row): Boolean {
                return oldItem.aTTFILENOMAIN ==newItem.aTTFILENOMAIN
            }

            override fun areContentsTheSame(oldItem: Row, newItem: Row): Boolean {
                return oldItem == newItem
            }

        }
    }
}

