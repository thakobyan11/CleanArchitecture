package com.practics.practics.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.practics.practics.R
import com.practics.practics.domain.ShopItem

class ShopListAdapter : RecyclerView.Adapter<ShopListAdapter.ShopItemViewHolder>() {


    private val list = listOf<ShopItem>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        return ShopItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.shop_list_item,parent, false)
        )
    }

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        holder.name.text = list[position].name
        holder.count.text = list[position].count.toString()
        holder.view.setOnLongClickListener {
            true
        }
    }

    override fun getItemCount() = list.size

    class ShopItemViewHolder (val view : View) :RecyclerView.ViewHolder(view) {
        val name :TextView = view.findViewById(R.id.name)
        val count :TextView = view.findViewById(R.id.count)
    }
}