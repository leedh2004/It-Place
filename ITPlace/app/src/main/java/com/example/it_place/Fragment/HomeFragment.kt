package com.example.it_place.Fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.it_place.R
import com.example.it_place.retrofit.Retrofit.service
import com.example.it_place.retrofit.dto.Dtos
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //====================방목록 data에 List형태로 존재====================================
        val call = service.roomList()
        call.enqueue(object : Callback<List<Dtos.RoomList>>{
            override fun onResponse(call: Call<List<Dtos.RoomList>>, response: Response<List<Dtos.RoomList>>) {
                val data:List<Dtos.RoomList>? = response.body()
                Log.d("결과", "성공 : ${response.raw()}\n")
                data?.forEach { d ->
                Log.d("결과", "rid:${d?.rid} | name:${d?.name} | max_num:${d?.max_num} | landscape_url:${d?.landscape_url}")
                }
            }

            override fun onFailure(call: Call<List<Dtos.RoomList>>, t: Throwable) {
                Log.d("결과", "실패 : ${t.message}")
            }
        })
        //======================================================================================

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}