package com.example.it_place.Fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.it_place.Adapter.PlaceListAdapter
import com.example.it_place.EntranceActivity
import com.example.it_place.MainActivity
import com.example.it_place.Model.Place
import com.example.it_place.R
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    val placeList: List<Place> = listOf(
        Place(0, "테스트 제목1", "#테스트1 #잇플", 1, "https://"),
        Place(0, "테스트 제목2", "#테스트2 #잇플", 1, "https://"),
        Place(0, "테스트 제목3", "#테스트3 #잇플", 1, "https://"),
        Place(0, "테스트 제목4", "#테스트4 #잇플", 1, "https://")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = PlaceListAdapter(placeList)
        val layoutManager = LinearLayoutManager(view.context)
        place_list.layoutManager = layoutManager

        adapter.setItemClickListener(object : PlaceListAdapter.OnItemClickListener {
            override fun onClick(v: View, position: Int) {
                val item = placeList[position]
//                item.title = item.title + "1"

                activity?.let {
                    val intent = Intent(context, EntranceActivity::class.java)
                    intent.putExtra("place", item)
                    startActivity(intent)
                }

                adapter.notifyDataSetChanged()
            }
        })

        place_list.adapter = adapter
    }
}

