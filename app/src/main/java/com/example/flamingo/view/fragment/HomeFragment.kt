package com.example.flamingo.view.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flamingo.R
import com.example.flamingo.data.model.Article
import com.example.flamingo.data.model.NewsMainModel
import com.example.flamingo.data.rest.Retrofit_object
import com.example.flamingo.view.adapter.NewsAdapter
import com.google.android.material.button.MaterialButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {
    lateinit var recview: RecyclerView
    lateinit var ed_user: EditText
    lateinit var btnsearch: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val View = inflater.inflate(R.layout.fragment_home, container, false)

        recview = View.findViewById(R.id.newRec)
        ed_user = View.findViewById(R.id.ed_user)
        btnsearch = View.findViewById(R.id.btnsearch)

        loadnews(View, recview)

        btnsearch.setOnClickListener {

            if (ed_user.text.isEmpty()) {
                loadnews(View, recview)
                Toast.makeText(View.context, "enter data ", Toast.LENGTH_SHORT).show()
            } else {
                loadCountrynews(View, recview, ed_user.text.toString())
            }
        }


        return View

    }

    private fun loadCountrynews(view: View, recview: RecyclerView, usertext: String) {

        recview.layoutManager = LinearLayoutManager(view.context)


        var newslist = ArrayList<Article>()
        newslist.clear()
        val retrofit = Retrofit_object.getJob
        val result = retrofit.getTopHeadlines("$usertext", "6566c770e39349c1b7f924d0bc85a62f")
        result.enqueue(object : Callback<NewsMainModel?> {
            override fun onResponse(
                call: Call<NewsMainModel?>,
                response: Response<NewsMainModel?>
            ) {
                val data_response = response.body()
                if (data_response != null) {
                    newslist.addAll(data_response.articles)

                    val adapter = NewsAdapter(view.context, newslist)
                    recview.adapter = adapter
                    adapter.notifyDataSetChanged()

                } else {
                    Toast.makeText(view.context, "No data found", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<NewsMainModel?>, t: Throwable) {
                Toast.makeText(view.context, "error", Toast.LENGTH_SHORT).show()
            }
        })


    }

    private fun loadnews(view: View, recview: RecyclerView) {

        recview.layoutManager = LinearLayoutManager(view.context)

        var datalist = ArrayList<Article>()

        val retrofit = Retrofit_object.getNewsApi

        val result = retrofit.getnews()


        result.enqueue(object : Callback<NewsMainModel?> {
            override fun onResponse(
                call: Call<NewsMainModel?>,
                response: Response<NewsMainModel?>
            ) {
                val data_response = response.body()
                if (data_response != null) {
                    datalist.addAll(data_response.articles)

                    val adapter = NewsAdapter(view.context, datalist)
                    recview.adapter = adapter
                } else {
                    Toast.makeText(view.context, "No data found", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<NewsMainModel?>, t: Throwable) {
                Toast.makeText(view.context, "error", Toast.LENGTH_SHORT).show()
            }
        })


    }

}