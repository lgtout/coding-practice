package com.lagostout.elementsofprogramminginterviews.sorting

import java.util.*

object RemoveFirstNameDuplicates {
    data class Name (val first: String, val last: String) : Comparable<Name> {
        override fun compareTo(other: Name): Int {
            return first.compareTo(other.first)
        }
    }
    fun removeFirstNameDuplicates(array: MutableList<Name>) {
        Collections.sort(array)
        var lastUniqueFirstNameIndex = 0
        var currentNameIndex = 1
        while (currentNameIndex <= array.lastIndex) {
            val name = array[currentNameIndex]
            array[lastUniqueFirstNameIndex].let {
                (lastUniqueFirstName, _) ->
                if (name.first != lastUniqueFirstName) {
                    array[++lastUniqueFirstNameIndex] = name
                }
            }
            ++currentNameIndex
        }
        println(lastUniqueFirstNameIndex)
        if (lastUniqueFirstNameIndex < array.lastIndex) {
            array.subList((lastUniqueFirstNameIndex + 1),
                    array.lastIndex).clear()
        }
    }
}
