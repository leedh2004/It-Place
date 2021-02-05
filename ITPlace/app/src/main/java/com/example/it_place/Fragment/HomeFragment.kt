package com.example.it_place.Fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.it_place.R
import com.example.it_place.retrofit.dto.Dtos
import com.example.it_place.retrofit.service.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

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
    //TEST BASE URL
    val BASE_URL_TEST = "https://jsonplaceholder.typicode.com/"

    //api 통신
    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL_TEST)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

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
        //API RESPONSE
        val api = retrofit.create(RetrofitService::class.java)
        val call = api.getTests(1)
        call.enqueue(object : Callback<Dtos.TestGetDto>{
            override fun onResponse(call: Call<Dtos.TestGetDto>, response: Response<Dtos.TestGetDto>) {
                val data = response.body()
                Log.d("결과", "성공 : ${response.raw()}")
                Log.d("결과", "userId:${data?.userId} | id:${data?.id} | title:${data?.title} | completed:${data?.completed}")
            }
            override fun onFailure(call: Call<Dtos.TestGetDto>, t: Throwable) {
                Log.d("결과", "실패 : ${t.message}")
            }
        })

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