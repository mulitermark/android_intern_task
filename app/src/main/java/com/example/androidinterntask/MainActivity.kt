package com.example.androidinterntask

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.android.volley.Request
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonNewData = findViewById<Button>(R.id.buttonNewData)
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
                printToTextView(response)
            },
            {
                // error
            }
        )

        queue.add(jsonObjectRequest)
    }

    private fun printToTextView(response: JSONObject?) {
        if (response == null) return

        val textView = findViewById<TextView>(R.id.text_view_result)

        var text = ""
        val array = response.getJSONArray("playlist")
        for(i in 0 until array.length()) {
            text = text.plus(array.getJSONObject(i)).plus("\n")
        }
        textView.text = text
    }
}