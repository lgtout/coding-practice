package com.lagostout.elementsofprogramminginterviews.stacksandqueues

import java.util.*

/**
 * Problem 9.4 page 139
 */
fun shortestEquivalentPath(path: String): String {
    var shortestPath: String = ""
    if (path.isEmpty()) return shortestPath
    val stack = LinkedList<String>()
    val locations = path.split("/").filter {
        it.isNotEmpty()
    }
    // Preserve absolute path when necessary
    path[0].let {
        if (it == '/') stack.push(it.toString())
    }
    locations.forEach {
        when (it) {
            "." -> {
                if (stack.isEmpty())
                    stack.push(it)
            }
            ".." -> {
                if (stack.isEmpty() || stack.peek() == "/")
                    stack.push(it)
                else stack.pop()
            }
            else -> {
                // Handles "./a" -> "a"
                if (stack.peek() == ".")
                    stack.pop()
                stack.push(it)
            }
        }
    }

    shortestPath = if (stack.isEmpty()) "."
    else stack.toList().reversed().joinToString("/")

    return shortestPath
}
