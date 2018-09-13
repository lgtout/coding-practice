package com.lagostout.bytebybyte.recursion

fun permutations(numbers: List<Int>): List<List<Int>> {
    data class State(val value: Int?, val availableNumbers: List<Int>) {
        fun nextStates(): List<State> {
            return availableNumbers.map {
                State(it, availableNumbers - it)
            }
        }
    }
    fun compute(state: State): List<List<Int>> {
        return if (state.nextStates().isEmpty())
            listOf(listOfNotNull(state.value))
        else state.nextStates().flatMap { nextState ->
            compute(nextState).map {
                listOfNotNull(state.value) + it
            }
        }
    }
    return compute(State(null, numbers))
}