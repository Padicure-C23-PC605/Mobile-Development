package com.captsone.padicure.utils.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.captsone.padicure.data.ItemData
import com.captsone.padicure.databinding.ItemCardBinding

class RecyclerViewAdapter(
    private var dataList: List<ItemData>,
    private val onClickListener: (ItemData) -> Unit
) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ItemCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.bind(item)
        holder.itemView.setOnClickListener {
            onClickListener(item)
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun updateData(newDataList: List<ItemData>) {
        dataList = newDataList
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: ItemCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ItemData) {
            binding.itemName.text = item.name
            Glide.with(binding.root).load(item.photoUrl).into(binding.itemPicture)
        }
    }
}
