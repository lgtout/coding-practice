package com.lagostout.elementsofprogramminginterviews.greedyalgorithmsandinvariants

/* Problem 18.6.2 page 350 */

import com.lagostout.elementsofprogramminginterviews.greedyalgorithmsandinvariants.GasupProblem.City

object GasupProblemWhenAmpleCityMayNotExist {

    fun findAmpleCity(city: City, mpg: Double): City? {
        if (city.next == city) return city
        var startCity = city
        var currentCity = city
        var gasRemaining = city.gas
        var routeCityCount = 1
        var result: City? = null
        do {
            val distance = currentCity.distanceToNextCity
            val gasRequired = distance / mpg
            if (gasRemaining < gasRequired) {
                if (startCity != currentCity) {
                    val distanceToNextCityAfterStart =
                            startCity.distanceToNextCity
                    val gasUsedToGetToNextCityAfterStart =
                            distanceToNextCityAfterStart / mpg
                    gasRemaining += gasUsedToGetToNextCityAfterStart
                    startCity = startCity.next!!
                } else {
                    currentCity = currentCity.next!!
                    if (currentCity == startCity) break
                    startCity = currentCity
                    gasRemaining = currentCity.gas
                }
                routeCityCount -= 1
                if (startCity == city) break
            } else {
                currentCity = currentCity.next!!
                if (currentCity == startCity) {
                    result = startCity
                    break
                }
                gasRemaining += currentCity.gas - gasRequired
                routeCityCount += 1
            }
        } while (true)
        return result
    }

}

