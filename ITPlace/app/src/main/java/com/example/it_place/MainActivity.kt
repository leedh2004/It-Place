package com.example.it_place

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.webkit.WebView
import com.example.it_place.Fragment.*
import com.example.it_place.Model.Place
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_home -> {
                val transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.fragmentLayout, HomeFragment())
                transaction.commit()
                return true
            }
            R.id.action_friend -> {
                val transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.fragmentLayout, FriendFragment())
                transaction.commit()
                return true
            }
            R.id.action_create -> {
                val transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.fragmentLayout, CreateFragment())
                transaction.commit()
                return true
            }
            R.id.action_notification -> {
                val transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.fragmentLayout, NotificationFragment())
                transaction.commit()
                return true
            }
            R.id.action_setting -> {
                val transaction = supportFragmentManager.beginTransaction()
                transaction.replace(R.id.fragmentLayout, SettingFragment())
                transaction.commit()
                return true
            }
        }
        return false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val placeList: ArrayList<Place> =
            intent.getSerializableExtra("placeList") as ArrayList<Place>


        val navigation: BottomNavigationView = findViewById(R.id.navigation)
        navigation.setOnNavigationItemSelectedListener(this)

        val homeFragment = HomeFragment()
        val bundle = Bundle()
        bundle.putSerializable("placeList", placeList)
        homeFragment.arguments = bundle
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentLayout, homeFragment)
        transaction.commit()

    }
}