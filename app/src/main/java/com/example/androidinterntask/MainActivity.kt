package com.example.androidinterntask

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : AppCompatActivity() {

    private lateinit var itemAdapter: ItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        itemAdapter = ItemAdapter(mutableListOf())

        recycler_view.adapter = itemAdapter
        recycler_view.layoutManager = LinearLayoutManager(this)

        // val buttonNewData = findViewById<Button>(R.id.buttonNewData)
        buttonNewData.setOnClickListener {
            apiCall()
        }
    }

    private fun apiCall() {
        val endpoint = "https://android-intern-homework.vercel.app/api"
        val queue = Volley.newRequestQueue(this)
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, endpoint, null,
            { response ->
                processResponse(response)
            },
            {
                // error
            }
        )

        queue.add(jsonObjectRequest)
    }

    private fun processResponse(response: JSONObject?) {
        if (response == null) return

        itemAdapter.deleteItems()

        val array = response.getJSONArray("playlist") //?
        for (i in 0 until array.length()) {
            val obj = array.getJSONObject(i)
            val userName = obj.optString("userName", "None")
            val email = obj.optString("email", "None")
            val title = obj.optString("title", "None")
            val color: Int = Color.parseColor(obj.optString("theme", "#D3D3D3"))
            val item = Item(userName, email, title, color)

            //TODO Place all data in the item object
            //TODO Picture
            itemAdapter.addItem(item)
        }
    }
}