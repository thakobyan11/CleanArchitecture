package com.practics.practics.presentation

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.practics.practics.R

class ShopItemViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
    val name: TextView = view.findViewById(R.id.name)
    val count: TextView = view.findViewById(R.id.count)
}