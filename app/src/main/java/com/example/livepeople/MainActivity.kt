package com.example.livepeople

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.livepeople.model.Data
import com.example.livepeople.model.People
import com.example.livepeople.utils.MaxLivePeopleCalculator
import com.google.gson.Gson
import java.io.IOException

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val txtYear: TextView = findViewById(R.id.txt_year)

        val peoples = getPeopleList()
        val peopleAlive = MaxLivePeopleCalculator(peoples)

        txtYear.text = getString(R.string.txt_max_year, peopleAlive.getYearWithMaxPeople().toString())
    }

    private fun getPeopleList(): List<People> {
        val gson = Gson()
        val json = readJsonData("data.json")
        val data = gson.fromJson(json, Data::class.java)

        return data.data?.toList() ?: emptyList()
    }

    private fun readJsonData(inFile: String): String {
        var tContents = ""

        try {
            val stream = assets.open(inFile)

            val size = stream.available()
            val buffer = ByteArray(size)
            stream.read(buffer)
            stream.close()
            tContents = String(buffer)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return tContents
    }

}