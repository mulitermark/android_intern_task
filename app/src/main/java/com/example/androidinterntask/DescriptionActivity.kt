package com.example.androidinterntask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_description.*

class DescriptionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_description)

        loadData()
    }

    private fun loadData() {
        val url: String? = intent.getStringExtra("url")
        if(url != null){
            if (url.isNotEmpty()) {
                Glide.with(this)
                    .load(url)
                    .placeholder(android.R.drawable.ic_delete)
                    .error(android.R.drawable.ic_delete)
                    .into(image_view_detailed_avatar)
            }
        } else Log.e("URL loading in details",
            "URL failed to load in details activity (got null value from given intent).")
        val username: String? = intent.getStringExtra("username")
        if(username != null){
            text_view_detailed_name.text = username
        }
        val title: String? = intent.getStringExtra("title")
        if(title != null){
            text_view_detailed_title.text = title
        }
        val email: String? = intent.getStringExtra("email")
        if(email != null){
            text_view_detailed_email.text = email
        }
        val created: String? = intent.getStringExtra("created")
        if(created != null){
            text_view_detailed_created.text = created
        }
        val guid: String? = intent.getStringExtra("guid")
        if(guid != null){
            text_view_detailed_guid.text = guid
        }
        val description: String? = intent.getStringExtra("description")
        if(description != null){
            text_view_detailed_description.text = description
        }

    }
}