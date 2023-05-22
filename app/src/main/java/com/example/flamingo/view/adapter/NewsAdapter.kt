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
import com.example.flamingo.data.model.Article
import com.google.android.material.button.MaterialButton

class NewsAdapter(
    var context: Context,
    var datalist: ArrayList<Article>,
    var OnClickListine: Onclickbtn

) :
    RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    //when you want to apply on click in activity
    interface Onclickbtn {
        fun OnClickShareBtn(position: Int, articleLink: String, urlToImage: String)
        fun OnClickReadmoreBtn(position: Int, urlString: String)
        fun OnClickSaveBtn(title:String,description:String,url:String,urlToImage:String)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.newsitem_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var mymodel = datalist[position]
        if (mymodel.urlToImage.isNullOrBlank()) {
            holder.newsImage.setImageResource(R.drawable.card_image)
        } else {
            Glide.with(context).load(mymodel.urlToImage).into(holder.newsImage)
        }

        holder.txttile.text = mymodel.title
        holder.txtdes.text = mymodel.description

        holder.tv_publish.text = mymodel.source.name


        var urlString = mymodel.url

        holder.btn.setOnClickListener {

            OnClickListine.OnClickReadmoreBtn(position,mymodel.url.toString())

        }
        holder.btn_share.setOnClickListener {
            OnClickListine.OnClickShareBtn(position,mymodel.url,mymodel.urlToImage)

        }

        holder.btnsave.setOnClickListener {
            OnClickListine.OnClickSaveBtn(mymodel.title,mymodel.description,mymodel.url,mymodel.urlToImage)

        }

    }

    override fun getItemCount(): Int {
        return datalist.size
    }


    class ViewHolder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        var txttile = itemView.findViewById<TextView>(R.id.tv_headline)
        var txtdes = itemView.findViewById<TextView>(R.id.tv_des)
        var btnsave = itemView.findViewById<TextView>(R.id.btnsave)
        var tv_publish = itemView.findViewById<TextView>(R.id.tv_publish)
        var newsImage = itemView.findViewById<ImageView>(R.id.newsImage)
        var btn = itemView.findViewById<MaterialButton>(R.id.btnReadMore)
        var btn_share = itemView.findViewById<MaterialButton>(R.id.btnshare)

    }

}