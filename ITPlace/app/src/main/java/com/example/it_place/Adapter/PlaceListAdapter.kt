package com.example.it_place.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.it_place.Model.Place
import com.example.it_place.R
import kotlinx.android.synthetic.main.item_place.view.*

class PlaceListAdapter(private val itemList : List<Place>) : RecyclerView.Adapter<PlaceViewHolder>()  {

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaceViewHolder {
        val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.item_place, parent, false)
        return PlaceViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: PlaceViewHolder, position: Int) {
        val item = itemList[position]
        holder.apply {
            bind(item)
        }
    }
}

class PlaceViewHolder(v: View) : RecyclerView.ViewHolder(v) {
    var view : View = v

    fun bind(item: Place) {
        view.place_title.text = item.name   //title을 name으로 바꿔놓음
    }
}
