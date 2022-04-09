package com.example.livepeople.utils

import com.example.livepeople.model.People

class MaxLivePeopleCalculator (listPeoples: List<People>) {
    private val years: MutableList<Int> = mutableListOf()

    init {
        for(people in listPeoples){
            for(year in people.birthYear..people.deathYear) {
                years.add(year)
            }
        }
    }

    fun getYearWithMaxPeople(): Int {
        val max = years.groupingBy { it }.eachCount().filter { it.value > 1}.maxByOrNull { it.value }
        return max?.key ?: 0
    }

}