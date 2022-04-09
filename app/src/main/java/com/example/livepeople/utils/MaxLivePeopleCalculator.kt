package com.example.livepeople.utils

import com.example.livepeople.model.People

class MaxLivePeopleCalculator (listPeoples: List<People>) {
    private val years: MutableList<Int> = mutableListOf()

    init {
        // Recorremos la lista de personas para obtener los datos
        // de cada persona individualmente.
        for(people in listPeoples){
            // Al tiempo que recorremos los datos individuales de cada persona,
            // agregamos los años entre la fecha de nacimiento y de fallecimiento a un array.
            for(year in people.birthYear..people.deathYear) {
                // La razón de agregar los años a una lista es para posteriormente
                // calcular la moda estadistica y encontrar el año en que más personas vivas existieron.
                years.add(year)
            }
        }
    }

    // Esta función agrupa y evalue los valores repetidos.
    // Una vez hecho ello, retorna el valor que más se repite,
    // es decir, el año en que más personas vivieron al mismo tiempo.
    fun getYearWithMaxPeople(): Int {
        val max = years.groupingBy { it }.eachCount().filter { it.value > 1}.maxByOrNull { it.value }
        return max?.key ?: 0
    }

}