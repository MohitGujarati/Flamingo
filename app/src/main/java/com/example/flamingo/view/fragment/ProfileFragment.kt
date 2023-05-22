package com.example.flamingo.view.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flamingo.R
import com.example.flamingo.data.helper.SavednewsHelper
import com.example.flamingo.data.model.NewsSavedModel
import com.example.flamingo.view.adapter.SavedNewsAdapter

class ProfileFragment : Fragment() {

    lateinit var savedrec: RecyclerView

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val View = inflater.inflate(R.layout.fragment_profile, container, false)

        savedrec = View.findViewById(R.id.savedrec)


        loadsaved(View)

        return View
    }

    private fun loadsaved(View: View) {
        savedrec.layoutManager = LinearLayoutManager(View.context)

        var userlist: ArrayList<NewsSavedModel>
        var dbRetrivehelper = SavednewsHelper(View.context)
        userlist = dbRetrivehelper.retrieve() as ArrayList<NewsSavedModel>


        var adapter=SavedNewsAdapter(View.context,userlist)

        savedrec.adapter=adapter

    }


}