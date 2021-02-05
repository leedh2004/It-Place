package com.example.it_place.Fragment

import Retrofit.service
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.it_place.Adapter.PlaceListAdapter
import com.example.it_place.Model.Place
import com.example.it_place.R
import kotlinx.android.synthetic.main.fragment_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {
    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 플레이스 목록
        val bundle: Bundle? = arguments
        val placeList = bundle!!.getSerializable("placeList") as List<Place>

        val adapter = PlaceListAdapter(placeList)
        place_list.adapter = adapter
        val layoutManager = LinearLayoutManager(view.context)
        place_list.layoutManager = layoutManager
    }

}