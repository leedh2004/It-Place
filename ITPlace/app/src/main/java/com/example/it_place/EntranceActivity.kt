package com.example.it_place

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.it_place.Model.Place
import kotlinx.android.synthetic.main.activity_entrance.*

class EntranceActivity : AppCompatActivity() {

    lateinit var place: Place
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entrance)

        place = intent.getSerializableExtra("place") as Place
        entrance_title.text = place.title
        entrance_tags.text = place.tag
        Log.d("DEBUG", place.title)

    }
}