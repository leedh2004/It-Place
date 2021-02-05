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
    /*
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            arguments?.let {
                param1 = it.getString(ARG_PARAM1)
                param2 = it.getString(ARG_PARAM2)
            }
        }*/
    val placeList: List<Place> = emptyList()
    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        //====================방목록 data에 List형태로 존재====================================
        val call = service.placeList()
        call.enqueue(object : Callback<List<Place>> {
            override fun onResponse(call: Call<List<Place>>, response: Response<List<Place>>) {
                val placeList:List<Place>? = response.body()    //placeList에 값 담겨있음 List형태
                Log.d("결과", "성공 : ${response.raw()}\n") //성공여부 로그
                placeList?.forEach { d ->
                    Log.d("결과", "name:${d?.name} | max_num:${d?.max_num} | tag:${d?.tag} | landscape_url:${d?.landscape_url}" +
                            " | profile_url:${d.profile_url} | current_num:${d.current_num}") //RESTAPI는 잘 받았는지 확인
                }
            }
            override fun onFailure(call: Call<List<Place>>, t: Throwable) {
                Log.d("결과", "실패 : ${t.message}")
            }
        })
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = PlaceListAdapter(placeList)
        place_list.adapter = adapter
        val layoutManager = LinearLayoutManager(view.context)
        place_list.layoutManager  = layoutManager
    }
    override fun onResume() {
        super.onResume()

    }

}