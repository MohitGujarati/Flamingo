package com.example.flamingo.view.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flamingo.R
import com.example.flamingo.data.model.MovieMainModel
import com.example.flamingo.data.model.MovieMainModelItem
import com.example.flamingo.data.rest.Retrofit_object
import com.example.flamingo.view.adapter.MovieAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailFragment : Fragment() {

    lateinit var movierec: RecyclerView


    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val View = inflater.inflate(R.layout.fragment_detail, container, false)

        movierec = View.findViewById(R.id.moviewrec)

        loadmovie(View)
        return View
    }

    private fun loadmovie(view: View) {
        movierec.layoutManager = LinearLayoutManager(view.context)

        var movielist = ArrayList<MovieMainModelItem>()

        var retrofit = Retrofit_object.getMovieApi
        var result = retrofit.getmovie()

        result.enqueue(object : Callback<List<MovieMainModelItem>?> {
            override fun onResponse(
                call: Call<List<MovieMainModelItem>?>,
                response: Response<List<MovieMainModelItem>?>
            ) {
                var data = response.body()
                if (data != null) {
                    movielist.addAll(data)

                    var adapter = MovieAdapter(view.context, movielist)
                    movierec.adapter = adapter

                } else {
                    Toast.makeText(view.context, "empty", Toast.LENGTH_SHORT).show()

                }
            }

            override fun onFailure(call: Call<List<MovieMainModelItem>?>, t: Throwable) {
                Toast.makeText(view.context, "error", Toast.LENGTH_SHORT).show()

            }
        })

    }
}