package com.example.flamingo.viewmodel

import android.content.Context
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flamingo.data.helper.SavednewsHelper
import com.example.flamingo.data.model.NewsSavedModel
import com.example.flamingo.view.adapter.SavedNewsAdapter

class MainViewModel(val context: Context) : ViewModel() {

    fun loadSavedData(savedrec:RecyclerView, View: View) {
        savedrec.layoutManager = LinearLayoutManager(View.context)

        var userlist: ArrayList<NewsSavedModel>
        var dbRetrivehelper = SavednewsHelper(View.context)
        userlist = dbRetrivehelper.retrieve() as ArrayList<NewsSavedModel>


        var adapter= SavedNewsAdapter(View.context,userlist)

        savedrec.adapter=adapter
    }
}

