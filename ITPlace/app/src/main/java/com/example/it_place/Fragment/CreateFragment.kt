package com.example.it_place.Fragment

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.it_place.Model.Place
import com.example.it_place.R
import com.example.it_place.SelectGalleyDialog
import kotlinx.android.synthetic.main.fragment_create.*
import kotlinx.android.synthetic.main.item_place.*

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
            dialog.photoSelectDialog()
        }
        cover_setting.setOnClickListener {
            val dialog = SelectGalleyDialog(view.context)
            dialog.photoSelectDialog()
        }

        // 개설 버튼 누르면 서버에 등록 하는 코드 필요!!!
        create_button.setOnClickListener {
            place.name = edit_place_name.text.toString()
            place.tag = edit_tags.text.toString()

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