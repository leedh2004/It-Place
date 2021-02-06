package com.example.it_place.Model

import java.io.Serializable

class Place (
    var name: String ,
    var max_num: Int,
    var tag: String,
    var landscape_url: String,
    var profile_url: String,
    var current_num: Int) : Serializable{
        init {
            max_num = 3
            current_num = 0
        }
}