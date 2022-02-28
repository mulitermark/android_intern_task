package com.example.androidinterntask

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.androidinterntask.databinding.ActivityMainBinding
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private lateinit var itemAdapter: ItemAdapter
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        itemAdapter = ItemAdapter(mutableListOf())
        binding.recyclerView.adapter = itemAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        val endpointUrl = "https://android-intern-homework.vercel.app/api"
        apiCall(endpointUrl)
        binding.buttonNewData.setOnClickListener {
            apiCall(endpointUrl)
        }
    }

    /**
     * Calls an API using the given [url] parameter, and handles the data.
     * Passes the data to [processResponse].
     */
    private fun apiCall(url: String) {
        val queue = Volley.newRequestQueue(this)
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            { response ->
                processResponse(response)
            },
            {
                Log.e("JsonObjectRequest", "JSON object request failed.")
            }
        )

        queue.add(jsonObjectRequest)
    }

    /**
     * Handles the [response] passed as a parameter.
     * Loads the data into an [Item] object and hands it to an [ItemAdapter].
     */
    private fun processResponse(response: JSONObject?) {
        if (response == null) return

        itemAdapter.deleteItems()

        val array = response.getJSONArray("playlist")
        for (i in 0 until array.length()) {
            val obj = array.getJSONObject(i)
            val userName = obj.optString("userName", "None")
            val email = obj.optString("email", "None")
            val title = obj.optString("title", "None")
            val color: Int = Color.parseColor(obj.optString("theme", "#D3D3D3"))
            val url = obj.optString("avatarURL")
            val created = obj.optString("created")
            val guid = obj.optString("guid")
            var description = obj.optString("description", "None")
            description = if (description == "null" || description == "") "-" else description

            val item = Item(userName, email, title, color, url, created, description, guid)

            itemAdapter.addItem(item)
        }
    }
}