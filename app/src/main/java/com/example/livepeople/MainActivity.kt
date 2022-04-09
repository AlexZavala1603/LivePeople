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

        // Obtenemos la lista de personas y sus respectivos años de nacimiento y fallecimiento.
        val peoples = getPeopleList()

        // Usamos esa lista para calcular el año con mayor cantidad de personas.
        // Esto se hace mediante la clase MaxLivePeopleCalculator.
        val peopleAlive = MaxLivePeopleCalculator(peoples)

        txtYear.text = getString(R.string.txt_max_year, peopleAlive.getYearWithMaxPeople().toString())
    }

    // La función retorna la lista de personas
    // con base en lo que retorna el método readJsonData()
    private fun getPeopleList(): List<People> {
        val gson = Gson()
        val json = readJsonData("data.json")
        val data = gson.fromJson(json, Data::class.java)

        return data.data?.toList() ?: emptyList()
    }

    // Este método es unicamente para leer el contenido del Json
    // a través de un objeto stream.
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