package com.lagostout.elementsofprogramminginterviews.greedyalgorithmsandinvariants

import com.lagostout.elementsofprogramminginterviews.greedyalgorithmsandinvariants.GasupProblem.City
import com.lagostout.elementsofprogramminginterviews.greedyalgorithmsandinvariants
        .GasupProblemWhenAmpleCityMayNotExist.findAmpleCityIfItExists
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.data_driven.data
import org.jetbrains.spek.data_driven.on

object GasupProblemWhenAmpleCityMayNotExistSpek : Spek({

    fun computeByBruteForce(cities: City, mpg: Double): List<City> {
        val ampleCities = mutableListOf<City>()
        var startCity = cities
        // We test each sequence of n cities starting at each city - O(n^2).
        do {
            var startIndexIsAmpleCity = true
            var currentCity = startCity
            var gasRemaining = 0.0
            do {
                gasRemaining += currentCity.gas
                val gasToNextCity = currentCity.distanceToNextCity / mpg
                val nextCityIsReachable = gasToNextCity <= gasRemaining
                if (!nextCityIsReachable) {
                    startIndexIsAmpleCity = false
                    break
                }
                gasRemaining -= gasToNextCity
                currentCity = currentCity.next!!
            } while (currentCity != startCity)
            if (startIndexIsAmpleCity) {
                ampleCities.add(startCity)
            }
            startCity = startCity.next!!
        } while (startCity != cities)
        return ampleCities
    }

    fun connect(cities: List<City>) {
        for ((index, city) in cities.withIndex()) {
            city.next = if (index == cities.lastIndex) cities.first()
            else cities[index + 1]
        }
    }

    val data = run {
        listOfNotNull(
            // Ample city exists
            // Exact amount of gas to visit all cities
            Pair(listOf(City("a", 0, 0.0)), 1.0),
            Pair(listOf(City("a", 1, 1.0), City("b", 1, 1.0)), 1.0),
            Pair(listOf(City("a", 1, 2.0), City("b", 1, 1.0)), 1.0),
            Pair(listOf(City("a", 1, 2.0), City("b", 1, 0.0)), 1.0),
            Pair(listOf(City("a", 1, 0.0), City("b", 1, 2.0)), 1.0),
            Pair(listOf(City("a", 1, 1.0), City("b", 1, 2.0)), 1.0),
            Pair(listOf(City("a", 2, 1.0), City("b", 2, 1.0), City("c", 2, 4.0), City("d", 1, 1.0)), 1.0),
            Pair(listOf(City("a", 4, 4.7), City("b", 1, 1.2), City("c", 3, 3.4)), 0.9),
            Pair(listOf(City("c",200,5.0), City("d",400,35.0), City("e",600,25.0),
                City("f",200,10.0), City("g",100,10.0), City("a",900,45.0),
                City("b",600,20.0)), 20.0),
            // Excess gas after all cities visited
            Pair(listOf(City("c",200,5.0), City("d",400,50.0), City("e",600,25.0),
                City("f",200,10.0), City("g",100,10.0), City("a",900,50.0),
                City("b",600,20.0)), 20.0),
            // No ample city exists
            Pair(listOf(City("a", 1, 1.0), City("b", 1, 0.0)), 1.0),
            Pair(listOf(City("a", 1, 1.0), City("b", 1, 1.0), City("c", 1, 0.0)), 1.0),
            Pair(listOf(City("a", 1, 0.0), City("b", 1, 1.0), City("c", 1, 1.0)), 1.0),
            Pair(listOf(City("a", 1, 1.0), City("b", 1, 0.0), City("c", 1, 1.0)), 1.0),
            Pair(listOf(City("a", 1, 1.0), City("b", 1, 0.5)), 1.0),
            Pair(listOf(City("a",200,5.0), City("b",400,35.0), City("c",600,25.0),
                City("d",200,10.0), City("e",100,5.0), City("f",900,45.0),
                City("g",600,20.0)), 20.0),
            null
        ).map { (cities, mpg) ->
            connect(cities)
            cities.first().let { data(it, mpg, computeByBruteForce(it, mpg)) }
        }.toTypedArray()
    }

    describe("findAmpleCityIfItExists") {
        on("cities %s, mpg %s", with = *data) { cities, mpg, expected ->
            val ampleCity = findAmpleCityIfItExists(cities, mpg)
            it("should return $expected") {
                assertThat(ampleCity).let {
                    if (expected.isEmpty()) it.isNull()
                    else it.isIn(expected)
                }
            }
        }
    }

})