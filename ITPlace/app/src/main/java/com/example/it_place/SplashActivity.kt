package com.example.it_place

import Retrofit.service
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import android.util.Log
import com.example.it_place.Model.Place
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SplashActivity : AppCompatActivity() {
    var list: ArrayList<Place> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)


        val uuid: String = Settings.Secure.getString(this.contentResolver, Settings.Secure.ANDROID_ID)

        //====================방목록 data에 List형태로 존재====================================
        val call = service.placeList()
        call.enqueue(object : Callback<List<Place>> {
            override fun onResponse(call: Call<List<Place>>, response: Response<List<Place>>) {
                val placeList: List<Place>? = response.body()    //placeList에 값 담겨있음 List형태
                // 플레이스 목록 복사
                for (p in placeList!!) {
                    list.add(p)
                }
                Log.d("결과", "성공 : ${response.raw()}\n") //성공여부 로그
                placeList?.forEach { d ->
                    Log.d(
                        "결과",
                        "name:${d?.name} | max_num:${d?.max_num} | tag:${d?.tag} | landscape_url:${d?.landscape_url}" +
                                " | profile_url:${d.profile_url} | current_num:${d.current_num}"
                    ) //RESTAPI는 잘 받았는지 확인
                }
            }
            override fun onFailure(call: Call<List<Place>>, t: Throwable) {
                Log.d("결과", "실패 : ${t.message}")
            }
        })


        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("placeList", list)
            startActivity(intent)
            finish()
        }, 2000) // 지연 2초
    }
}
