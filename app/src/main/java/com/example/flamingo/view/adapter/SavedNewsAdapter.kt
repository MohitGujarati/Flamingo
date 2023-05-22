package com.example.flamingo.view.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.flamingo.R
import com.example.flamingo.data.model.NewsSavedModel

class SavedNewsAdapter(var context: Context, var savedlist: ArrayList<NewsSavedModel>) :
    RecyclerView.Adapter<SavedNewsAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.saved_layout, parent, false)
        return SavedNewsAdapter.ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return savedlist.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var mymodel = savedlist[position]

        holder.txttile.text = mymodel.newstitle
        holder.description.text = mymodel.newsdescription
        holder.btnreadmore.setOnClickListener {
            context?.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(mymodel.newlink)))
        }

        Glide.with(context).load(mymodel.newsImage).into(holder.image)

    }

    class ViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        var image = itemView.findViewById<ImageView>(R.id.imageView)
        var txttile = itemView.findViewById<TextView>(R.id.tv_news_titile)
        var description = itemView.findViewById<TextView>(R.id.tv_news_description)
        var btnreadmore = itemView.findViewById<TextView>(R.id.btn_readmore)

    }
}