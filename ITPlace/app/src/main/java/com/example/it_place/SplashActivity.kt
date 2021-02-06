package com.example.it_place

import Retrofit.service
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import android.util.Log
import com.example.it_place.Model.EnterApp
import com.example.it_place.Model.Place
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        //============어플 처음 시작할때 회원 가입=================
        val uid: String = Settings.Secure.getString(this.contentResolver, Settings.Secure.ANDROID_ID)

        val call = service.enterApp(EnterApp(uid, "홍길동"))
        call.enqueue(object : Callback<EnterApp> {
            override fun onResponse(call: Call<EnterApp>, response: Response<EnterApp>) {
                Log.d("결과", "회원 로그 성공 : ${response.raw()}" + "| uid: "+uid+"\n") //성공여부 로그
            }

            override fun onFailure(call: Call<EnterApp>, t: Throwable) {
                Log.d("결과 : ", "실패 ${t.message}, ${t.cause}")
            }
        })

        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 2000) // 지연 2초
    }
}
