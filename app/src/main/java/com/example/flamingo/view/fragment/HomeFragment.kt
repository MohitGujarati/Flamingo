package com.example.flamingo.view.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Gravity
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
import com.example.flamingo.data.helper.SavednewsHelper
import com.example.flamingo.data.model.Article
import com.example.flamingo.data.model.NewsMainModel
import com.example.flamingo.data.model.NewsSavedModel
import com.example.flamingo.data.rest.Retrofit_object
import com.example.flamingo.view.adapter.NewsAdapter
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
        ed_user.clearFocus();
        loadnews(View, recview)
        btnsearch.setOnClickListener {

            if (ed_user.text.isEmpty()) {
                loadnews(View, recview)

                Toast.makeText(View.context, "enter text ", Toast.LENGTH_SHORT).show()
            } else {
                ed_user.apply {
                    gravity = Gravity.CENTER
                    clearFocus();
                }
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

                    val adapter =
                        NewsAdapter(view.context, newslist, object : NewsAdapter.Onclickbtn {
                            override fun OnClickShareBtn(
                                position: Int,
                                urlString: String,
                                urlToImage: String
                            ) {
                                shareNews(urlString, urlToImage)
                            }

                            override fun OnClickReadmoreBtn(position: Int, urlString: String) {
                                readmore(position, urlString)
                            }

                            override fun OnClickSaveBtn(
                                title: String,
                                description: String,
                                url: String,
                                urlToImage: String
                            ) {

                               // Toast.makeText(view.context, "called", Toast.LENGTH_SHORT).show()
                                saveNews(view, title, description, url, urlToImage)
                            }
                        })
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

                    val adapter =
                        NewsAdapter(view.context, datalist, object : NewsAdapter.Onclickbtn {
                            override fun OnClickShareBtn(
                                position: Int,
                                urlString: String,
                                urlToImage: String
                            ) {
                                shareNews(urlString, urlToImage)
                            }

                            override fun OnClickReadmoreBtn(position: Int, urlString: String) {
                                readmore(position, urlString)
                            }

                            override fun OnClickSaveBtn(
                                title: String,
                                description: String,
                                url: String,
                                urlToImage: String
                            ) {
                             //   Toast.makeText(view.context, "called 2", Toast.LENGTH_SHORT).show()

                                saveNews(view, title, description, url, urlToImage)

                            }
                        })
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


    private fun readmore(position: Int, urlString: String) {

        context?.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(urlString)))


    }

    private fun shareNews(urlString: String, imageUrl: String) {

        val sendIntent = Intent()
        sendIntent.action = Intent.ACTION_SEND
        sendIntent.putExtra(
            Intent.EXTRA_TEXT,
            "Hey Check out this News :$urlString"
        )
        sendIntent.type = "text/plain"
        startActivity(sendIntent)


    }

    private fun saveNews(
        view: View,
        title: String,
        description: String,
        url: String,
        urlToImage: String
    ) {

        var urlToImage_var=urlToImage

        var dbhelper = SavednewsHelper(view.context)

        if (urlToImage.isNullOrBlank()){
            urlToImage_var=""
        }

        dbhelper.insert(
            NewsSavedModel(
               id, title, description, url, urlToImage_var
            )
        )

        Toast.makeText(view.context, "saved", Toast.LENGTH_SHORT).show()

    }


}