package com.example.it_place

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import kotlinx.android.synthetic.main.activity_place.*

class PlaceActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_place)

        // 웹뷰 테스트 코드
        val webSettings = place.settings
        webSettings.setSupportMultipleWindows(false)
        webSettings.javaScriptEnabled = true
        webSettings.loadWithOverviewMode = true
        place.isVerticalScrollBarEnabled = false
        place.isHorizontalScrollBarEnabled = false
        place.isVerticalFadingEdgeEnabled = false
        place.isHorizontalFadingEdgeEnabled = false
        place.loadUrl("https://itplacewebcam.herokuapp.com/meeting/aaass")
    }
}