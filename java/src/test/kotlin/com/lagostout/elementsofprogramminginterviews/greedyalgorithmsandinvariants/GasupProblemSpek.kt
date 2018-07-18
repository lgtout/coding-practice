package com.lagostout.elementsofprogramminginterviews.greedyalgorithmsandinvariants

import com.lagostout.common.rdg
import com.lagostout.elementsofprogramminginterviews.greedyalgorithmsandinvariants.GasupProblem.City
import com.lagostout.elementsofprogramminginterviews.greedyalgorithmsandinvariants.GasupProblem.findAmpleCity
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

object GasupProblemSpek : Spek({

    fun computeByBruteForce(cities: City, mpg: Double): List<City> {
        val ampleCities = mutableListOf<City>()
        var startCity = cities
        do {
            var startIndexIsAmpleCity = true
            var currentCity = startCity
            var gasRemaining = 0.0
            while (currentCity.next != startCity) {
                gasRemaining += currentCity.gas
                val gasToNextCity = cities.distanceToNextCity / mpg
                val nextCityIsReachable = gasToNextCity <= gasRemaining
                if (!nextCityIsReachable) {
                    startIndexIsAmpleCity = false
                    break
                }
                gasRemaining -= gasToNextCity
                currentCity = currentCity.next!!
            }
            if (startIndexIsAmpleCity) {
                ampleCities.add(startCity)
            }
            startCity = startCity.next!!
        } while (startCity != cities)
        return ampleCities
    }

    val data = run {
        listOfNotNull(
            Pair(listOf(City("a", 0, 0.0)), 1.0),
            null
        ).map { (cities, mpg) ->
            for ((index, city) in cities.withIndex()) {
                city.next = if (index == cities.lastIndex) cities.first()
                else cities[index + 1]
            }
            cities.first().let { data(it, mpg, computeByBruteForce(it, mpg)) }
        }.toTypedArray()
    }

    val randomData = run {
        val random = rdg()
        val caseCount = 10
//        val max
    }

    describe("findAmpleCity") {
        on("cities %s, mpg %s", with = *data) { cities, mpg, expected ->
            val ampleCity = findAmpleCity(cities, mpg)
            it("should return $expected") {
                assertThat(ampleCity).isIn(expected)
            }
        }
    }

})