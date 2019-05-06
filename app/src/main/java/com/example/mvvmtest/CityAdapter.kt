package com.example.mvvmtest

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class CityAdapter(private val clickListener: RecyclerViewClickListener, cities: ArrayList<CityDataModel>): RecyclerView.Adapter<CityAdapter.ViewHolder>() {

    var data: ArrayList<CityDataModel> = cities

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val root = LayoutInflater.from(parent.context).inflate(R.layout.item_city, parent, false)
        return ViewHolder(root)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(vh: ViewHolder, position: Int) {
        data[position].let {
            vh.cityNameTextView.text = it.title
        }
        vh.itemView.setOnClickListener {
            clickListener.onClick(position)
        }

    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val cityNameTextView: TextView = itemView.findViewById(R.id.cityTextView)
    }
}