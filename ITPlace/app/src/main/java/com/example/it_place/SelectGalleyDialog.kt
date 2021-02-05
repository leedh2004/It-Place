package com.example.it_place

import android.app.Dialog
import android.content.Context
import android.util.Log
import android.view.Gravity
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat

class SelectGalleyDialog(context: Context) {
    private val dialog = Dialog(context)

    fun photoSelectDialog() {
        dialog.setContentView(R.layout.select_gallery_dialog)
        dialog.window!!.setLayout(WindowManager.LayoutParams.WRAP_CONTENT,WindowManager.LayoutParams.WRAP_CONTENT)
        dialog.window!!.setGravity(Gravity.BOTTOM)
        dialog.window!!.setBackgroundDrawable(ContextCompat.getDrawable(dialog.context, R.drawable.round_background))
        dialog.setCanceledOnTouchOutside(true)
        dialog.setCancelable(true)
        dialog.show()

        val galleryButton = dialog.findViewById<TextView>(R.id.gallery_button)
        val randomButton = dialog.findViewById<TextView>(R.id.random_button)

        galleryButton.setOnClickListener {
            Log.d("DEBUG", "갤러리 버튼 선택")
            /*
            여기 갤러리 선택 시 사진 받는 코드..
             */
            dialog.dismiss() // 다이얼로그 제거 함수
        }
        randomButton.setOnClickListener {
            Log.d("DEBUG", "랜덤 버튼 선택")
            /*
            여기 랜덤 선택 시 사진 받는 코드..
             */
            dialog.dismiss() // 다이얼로그 제거 함수
        }

    }
}