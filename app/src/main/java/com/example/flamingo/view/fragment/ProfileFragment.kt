package com.example.flamingo.view.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.flamingo.R
import com.example.flamingo.data.helper.SavednewsHelper
import com.example.flamingo.data.model.NewsSavedModel
import com.example.flamingo.view.adapter.SavedNewsAdapter
import com.example.flamingo.viewmodel.MainViewModel
import com.example.flamingo.viewmodel.MainViewModelFactory


class ProfileFragment : Fragment() {

    lateinit var savedrec: RecyclerView
    lateinit var mainViewModel: MainViewModel

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mainViewModel =
            ViewModelProvider(this, MainViewModelFactory(requireActivity().application)).get(
                MainViewModel::class.java
            )

        // Inflate the layout for this fragment
        val View = inflater.inflate(R.layout.fragment_profile, container, false)

        savedrec = View.findViewById(R.id.savedrec)


        loadsaved(View)

        return View
    }

    private fun loadsaved(View: View) {
        mainViewModel.loadSavedData(savedrec, View)

    }


}