package com.tps.challenge.features.storefeed

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tps.challenge.R
import com.tps.challenge.features.storefeed.model.StoreFeedAdapterItemModel

/**
 * A RecyclerView.Adapter to populate the screen with a store feed.
 */
class StoreFeedAdapter(val data: List<StoreFeedAdapterItemModel>): RecyclerView.Adapter<StoreItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreItemViewHolder {
        return StoreItemViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_store, parent, false)
        )
    }

    override fun onBindViewHolder(holder: StoreItemViewHolder, position: Int) {
        with(holder.itemView) {
            findViewById<TextView>(R.id.name).text = data.get(position).storeName
            findViewById<TextView>(R.id.description).text = data.get(position).storeDescription
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}

/**
 * Holds the view for the Store item.
 */
class StoreItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
