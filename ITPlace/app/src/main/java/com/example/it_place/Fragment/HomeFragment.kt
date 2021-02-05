package com.example.it_place.Fragment


import Retrofit.service
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.Settings
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
import com.example.it_place.EntranceActivity

class HomeFragment : Fragment() {
    var list: ArrayList<Place> = ArrayList()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val call = service.placeList()
        call.enqueue(object : Callback<List<Place>> {
            override fun onResponse(call: Call<List<Place>>, response: Response<List<Place>>) {
                val placeList: List<Place>? = response.body()    //placeList에 값 담겨있음 List형태
                Log.d("결과", "성공 : ${response.raw()}\n") //성공여부 로그
                for (p in placeList!!) {
                    list.add(p)
                }
            }

            override fun onFailure(call: Call<List<Place>>, t: Throwable) {
                Log.d("결과", "실패 : ${t.message}")
            }
        })

        Handler(Looper.getMainLooper()).postDelayed({
            val adapter = PlaceListAdapter(list)
            place_list.adapter = adapter
            val layoutManager = LinearLayoutManager(view.context)
            place_list.layoutManager = layoutManager
            adapter.setItemClickListener(object : PlaceListAdapter.OnItemClickListener {
                override fun onClick(v: View, position: Int) {
                    val item = list[position]
//                item.title = item.title + "1"

                    activity?.let {
                        val intent = Intent(context, EntranceActivity::class.java)
                        intent.putExtra("place", item)
                        startActivity(intent)
                    }

                    adapter.notifyDataSetChanged()
                }
            })
        }, 1000)
    }
}



