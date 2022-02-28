package com.example.androidinterntask

import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

    /** Loads all data from the intent the activity has gotten to the corresponding activities' views. */
    private fun loadData() {
        val url: String? = intent.getStringExtra("url")
        if (url != null) {
            Glide.with(this)
                .load(url)
                .error(R.drawable.logo).centerCrop()
                .into(binding.imageViewDetailedAvatar)
        }
        val username: String? = intent.getStringExtra("username")
        if (username != null) {
            binding.textViewDetailedName.text = username
        }
        val title: String? = intent.getStringExtra("title")
        if (title != null) {
            binding.textViewDetailedTitle.text = title
        }
        val email: String? = intent.getStringExtra("email")
        if (email != null) {
            binding.textViewDetailedEmail.text = email
        }
        val created: String? = intent.getStringExtra("created")
        if (created != null) {
            binding.textViewDetailedCreated.text = created
        }
        val guid: String? = intent.getStringExtra("guid")
        if (guid != null) {
            binding.textViewDetailedGuid.text = guid
        }
        val description: String? = intent.getStringExtra("description")
        if (description != null) {
            binding.textViewDetailedDescription.text = description
        }
        val color: Int = intent.getIntExtra("color", R.color.default_grey)
        binding.constraintLayoutDetailed.background.setColorFilter(
            color,
            PorterDuff.Mode.DARKEN
        )

    }
}