package com.example.it_place
import retrofit2.Retrofit
import Retrofit.IMAGE_URL
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.MediaStore
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

class SelectGalleyDialog(context: Context) : Fragment(){
    private val dialog = Dialog(context)
    private var GALLERY_CODE: Int =1112;

    /**
     * 썸네일 이미지 설정
     */
    fun photoSelectDialogThumbnail() {
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

    /**
     * 사진 눌렀을 때 경로 가져오는 메서드
     *
     */
    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == GALLERY_CODE){
            if(resultCode == Activity.RESULT_OK){
                val realPathFromURI = ImageUpload.getRealPathFromURI(context!!, data?.data!!)
                val uploadImage = ImageUpload.uploadImage(realPathFromURI!!)
                Log.i("image", IMAGE_URL)
            }
        }

    }

    /**
     * 플레이스 이미지 설정
     */
    fun photoSelectDialogPlace() {
        dialog.setContentView(R.layout.select_gallery_dialog)
        dialog.window!!.setLayout(
            WindowManager.LayoutParams.WRAP_CONTENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
        dialog.window!!.setGravity(Gravity.BOTTOM)
        dialog.window!!.setBackgroundDrawable(
            ContextCompat.getDrawable(
                dialog.context,
                R.drawable.round_background
            )
        )
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
            val intent = Intent(Intent.ACTION_PICK)
            intent.data = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            intent.type = "image/*"
            startActivityForResult(Intent.createChooser(intent, "사진 불러오기"), GALLERY_CODE)
        }



        randomButton.setOnClickListener {
            Log.d("DEBUG", "랜덤 버튼 선택")
            /*
            여기 랜덤 선택 시 사진 받는 코드..
             */
            //처리해놓았습니다
            dialog.dismiss() // 다이얼로그 제거 함수
        }

    }
}