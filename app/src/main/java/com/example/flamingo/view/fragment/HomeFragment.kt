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
import com.example.flamingo.data.model.Article
import com.example.flamingo.data.model.NewsMainModel
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

                Toast.makeText(View.context, "enter data ", Toast.LENGTH_SHORT).show()
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
                            override fun OnClickShareBtn(position: Int) {
                                sharenews()
                            }

                            override fun OnClickReadmoreBtn(position: Int, urlString: String) {
                                readmore(position, urlString)
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
                            override fun OnClickShareBtn(position: Int) {
                                sharenews()
                            }

                            override fun OnClickReadmoreBtn(position: Int, urlString: String) {
                                readmore(position, urlString)
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

    private fun sharenews() {
        val sendIntent = Intent()
        sendIntent.action = Intent.ACTION_SEND
        sendIntent.putExtra(
            Intent.EXTRA_TEXT,
            "Hey Check out this Great app:"
        )
        sendIntent.type = "text/plain"
        startActivity(sendIntent)
    }

    private fun readmore(position: Int, urlString: String) {

        context?.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(urlString)))


    }

}