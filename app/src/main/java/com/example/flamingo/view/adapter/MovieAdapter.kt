package com.example.flamingo.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.flamingo.R
import com.example.flamingo.data.model.MovieMainModelItem

class MovieAdapter(
    var context: Context,
    var datalist: ArrayList<MovieMainModelItem>,
) : RecyclerView.Adapter<MovieAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.moviewitem, parent, false)
        return ViewHolder(view)


    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var mymodel = datalist[position]
        holder.txttile.text = mymodel.title
        holder.overview.text = mymodel.overview
        holder.type.text = mymodel.genres.toString()
        holder.ratting.text = mymodel.rating.toString()


        Glide.with(context).load(mymodel.poster).into(holder.poster_image)


    }

    override fun getItemCount(): Int {
        return datalist.size
    }


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var poster_image = itemView.findViewById<ImageView>(R.id.poster_image)
        var txttile = itemView.findViewById<TextView>(R.id.mTitle)
        var ratting = itemView.findViewById<TextView>(R.id.mRating)
        var type = itemView.findViewById<TextView>(R.id.tyoe_tv)
        var overview = itemView.findViewById<TextView>(R.id.movervie_tv)


    }
}