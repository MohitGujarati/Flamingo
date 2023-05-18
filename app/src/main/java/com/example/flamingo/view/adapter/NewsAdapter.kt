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
import com.example.flamingo.data.model.Article
import com.example.flamingo.data.model.NewsMainModel
import com.google.android.material.button.MaterialButton

class NewsAdapter(
    var context: Context,
    var datalist: ArrayList<Article>

) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    //when you want to apply on click in activity
    interface Onclickbtn {
        fun OnClickShareBtn(position: Int)
        fun OnClickReadmoreBtn(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.newsitem_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var mymodel = datalist[position]
        Glide.with(context).load(mymodel.urlToImage).into(holder.newsImage)
        holder.txttile.text = mymodel.title
        holder.txtdes.text = mymodel.description

        holder.tv_publish.text=mymodel.source.name


        var urlString = mymodel.url

        holder.btn.setOnClickListener {
            context?.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(urlString)))

        }
    }

    override fun getItemCount(): Int {
        return datalist.size
    }


    class ViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        var txttile = itemView.findViewById<TextView>(R.id.tv_headline)
        var txtdes = itemView.findViewById<TextView>(R.id.tv_des)
        var tv_publish = itemView.findViewById<TextView>(R.id.tv_publish)
        var newsImage = itemView.findViewById<ImageView>(R.id.newsImage)
        var btn = itemView.findViewById<MaterialButton>(R.id.btnurl)
        var btn_share = itemView.findViewById<MaterialButton>(R.id.btnshare)

    }

}