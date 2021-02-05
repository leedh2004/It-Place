package com.example.it_place.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.it_place.Adapter.PlaceListAdapter
import com.example.it_place.Model.Place
import com.example.it_place.R
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {
    /*
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            arguments?.let {
                param1 = it.getString(ARG_PARAM1)
                param2 = it.getString(ARG_PARAM2)
            }
        }*/
    val placeList: List<Place> = listOf(Place(0, "테스트 제목1", "tag,tag2", 1, "https://"),
        Place(0, "테스트 제목2", "tag,tag2", 1, "https://"),
        Place(0, "테스트 제목3", "tag,tag2", 1, "https://"),
        Place(0, "테스트 제목4", "tag,tag2", 1, "https://"))

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
        place_list.adapter = adapter
        val layoutManager = LinearLayoutManager(view.context)
        place_list.layoutManager  = layoutManager
    }
    override fun onResume() {
        super.onResume()

    }

}