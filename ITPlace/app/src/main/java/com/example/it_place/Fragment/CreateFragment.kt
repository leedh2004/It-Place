package com.example.it_place.Fragment

import Retrofit.IMAGE_URL
import android.annotation.SuppressLint
import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.it_place.Model.Place
import com.example.it_place.Model.PlaceForm
import com.example.it_place.Model.PlaceFormNoImg
import com.example.it_place.Model.PlaceFormRes
import com.example.it_place.R
import com.example.it_place.SelectGalleyDialog
import kotlinx.android.synthetic.main.fragment_create.*
import kotlinx.android.synthetic.main.item_place.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateFragment : Fragment() {
    private lateinit var place: Place
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_create, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        place = Place("",3,"","","",0)

        two.setOnClickListener(NumberSelectListener())
        three.setOnClickListener(NumberSelectListener())
        four.setOnClickListener(NumberSelectListener())

        thumbnail_setting.setOnClickListener {
            val dialog = SelectGalleyDialog(view.context)
            dialog.photoSelectDialogThumbnail()
        }
        cover_setting.setOnClickListener {
            val dialog = SelectGalleyDialog(view.context)
            dialog.photoSelectDialogPlace()
        }


        // 개설 버튼 누르면 서버에 등록 하는 코드 필요!!!
        create_button.setOnClickListener {
            Log.d("DEBUG", "개설 버튼 선택")
            place.name = edit_place_name.text.toString()
            place.tag = edit_tags.text.toString()

            //====================방 만들기===================================
            val uid: String = Settings.Secure.getString(context?.contentResolver, Settings.Secure.ANDROID_ID)

            // (방이름, 이미지URL(자동), 인원수, 태그)
            val call: Call<PlaceFormRes>
            //이미지가 있으면 image_url도 포함
            if(IMAGE_URL != "") call = Retrofit.service.createPlace("Bearer $uid", PlaceForm(place.name, IMAGE_URL, place.max_num, place.tag))
            else  call = Retrofit.service.createPlaceNoImg("Bearer $uid", PlaceFormNoImg(place.name, place.max_num, place.tag))

            Log.i("방설정 정보: " , "Bearer $uid | $IMAGE_URL")
            IMAGE_URL = ""  //이미지 URL 초기화

            call.enqueue(object : Callback<PlaceFormRes> {
                override fun onResponse(call: Call<PlaceFormRes>, response: Response<PlaceFormRes>) {
                    val body = response.body()
                    Log.d("결과", "방만들기 성공 : ${response.raw()}" + "| rid: "+body?.rid+"\n") //성공여부 로그
                }

                override fun onFailure(call: Call<PlaceFormRes>, t: Throwable) {
                    Log.d("결과 : ", "실패 ${t.message}, ${t.cause}")
                }
            })
//            place.landscape_url = 받아오셔서 넣어주세요!
//            place.profile_url = 받아오셔서 넣어주세요!
            Log.d("DATA", place.name + " | " + place.current_num + " | " + place.max_num +" | " + place.tag)

        }
    }

    inner class NumberSelectListener : View.OnClickListener {
        @RequiresApi(Build.VERSION_CODES.M)
        override fun onClick(v: View?) {
            when (v?.id) {
                R.id.two -> {
                    two.setTextColor(context!!.getColor(R.color.black))
                    three.setTextColor(context!!.getColor(R.color.brown_grey))
                    four.setTextColor(context!!.getColor(R.color.brown_grey))
                    place.max_num = 2;
                }
                R.id.three -> {
                    three.setTextColor(context!!.getColor(R.color.black))
                    two.setTextColor(context!!.getColor(R.color.brown_grey))
                    four.setTextColor(context!!.getColor(R.color.brown_grey))
                    place.max_num = 3;
                }
                R.id.four -> {
                    four.setTextColor(context!!.getColor(R.color.black))
                    three.setTextColor(context!!.getColor(R.color.brown_grey))
                    two.setTextColor(context!!.getColor(R.color.brown_grey))
                    place.max_num = 4;
                }
            }
        }
    }

}