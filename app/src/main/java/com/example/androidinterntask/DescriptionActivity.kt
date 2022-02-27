package com.example.androidinterntask

import android.graphics.Color
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.example.androidinterntask.databinding.ActivityDescriptionBinding

class DescriptionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDescriptionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDescriptionBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        loadData()
        binding.imageViewExitActivity.setOnClickListener {
            finish()
        }
    }

    private fun loadData() {
        val url: String? = intent.getStringExtra("url")
        if(url != null){
            if (url.isNotEmpty()) {
                Glide.with(this)
                    .load(url)
                    .placeholder(android.R.drawable.ic_delete)
                    .error(android.R.drawable.ic_delete)
                    .fallback(android.R.drawable.ic_delete)
                    .into(binding.imageViewDetailedAvatar)
            }
        } else Log.e("URL loading in details",
            "URL failed to load in details activity (got null value from given intent).")
        val username: String? = intent.getStringExtra("username")
        if(username != null){
            binding.textViewDetailedName.text = username
        }
        val title: String? = intent.getStringExtra("title")
        if(title != null){
            binding.textViewDetailedTitle.text = title
        }
        val email: String? = intent.getStringExtra("email")
        if(email != null){
            binding.textViewDetailedEmail.text = email
        }
        val created: String? = intent.getStringExtra("created")
        if(created != null){
            binding.textViewDetailedCreated.text = created
        }
        val guid: String? = intent.getStringExtra("guid")
        if(guid != null){
            binding.textViewDetailedGuid.text = guid
        }
        val description: String? = intent.getStringExtra("description")
        if(description != null){
            binding.textViewDetailedDescription.text = description
        }
        val color: Int? = intent.getIntExtra("color",Color.parseColor("#D3D3D3"))
        if(color != null){
            binding.constraintLayoutDetailed.background.setColorFilter(
                color,
                PorterDuff.Mode.DARKEN
            )
        }

    }
}