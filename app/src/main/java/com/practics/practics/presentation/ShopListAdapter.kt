package com.practics.practics.presentation

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.practics.practics.R
import com.practics.practics.domain.ShopItem

class ShopListAdapter : RecyclerView.Adapter<ShopListAdapter.ShopItemViewHolder>() {


    var shopList = listOf<ShopItem>()
    var count = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopItemViewHolder {
        Log.d("TAG","onCreateViewHolder ${++count}")
        return if (viewType == VIEW_TYPE_IS_ACTIVE){
            ShopItemViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.shop_list_item,parent, false)
            )
        }else{
            ShopItemViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.shop_list_item_light,parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: ShopItemViewHolder, position: Int) {
        holder.name.text = "${shopList[position].name} "
        holder.count.text = shopList[position].count.toString()
        holder.view.setOnLongClickListener {
            true
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (shopList[position].isActive){
            VIEW_TYPE_IS_ACTIVE
        }else{
            VIEW_TYPE_IS_NOT_ACTIVE
        }
    }

    override fun getItemCount() = shopList.size

    class ShopItemViewHolder (val view : View) :RecyclerView.ViewHolder(view) {
        val name :TextView = view.findViewById(R.id.name)
        val count :TextView = view.findViewById(R.id.count)
    }

    companion object{
        const val VIEW_TYPE_IS_ACTIVE = 1
        const val VIEW_TYPE_IS_NOT_ACTIVE = 2
        const val MAX_POOL_COUNT = 15
    }
}