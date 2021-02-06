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
        entrance_title.text = place.name
        entrance_tags.text = place.tag

        entrance_button.setOnClickListener {
            val intent = Intent(this, PlaceActivity::class.java)
            intent.putExtra("url", "https://itplacewebcam.herokuapp.com/meeting/aaass")
            startActivity(intent)
        }
    }
}