package com.lagostout.elementsofprogramminginterviews.stacksandqueues

import java.util.*

/**
 * Problem 9.4 page 139
 */
fun shortestEquivalentPath(path: String): String {
    var shortestPath  = ""
    if (path.isEmpty()) return shortestPath

    // Break path into parts
    val stack = LinkedList<String>()
    val delimiters = Regex("(?<=/)|(?=/)|(?<=\\.\\.)|(?=\\.\\.)|(?<=\\.!\\.)")
    val pathParts = path.split(delimiters).filterNot { it.isEmpty() }
    pathParts.forEach {
        when (it) {
            "/" -> {
                if (stack.peek() != it)
                    stack.push(it)
            }
            "." -> Unit
            ".." -> {

            }
        }
    }

//    val stack = LinkedList<String>()
//    val locations = path.split("/").filterNot {
//        it.isEmpty()
//    }
//    locations.forEach {
//        when (it) {
//            "." -> {
//                if (stack.isEmpty())
//                    stack.push(it)
//            }
//            ".." -> {
//                if (stack.isEmpty() || stack.peek() == "/")
//                    stack.push(it)
//                else stack.pop()
//            }
//            else -> {
//                // Handles "./a" -> "a"
//                if (stack.peek() == ".")
//                    stack.pop()
//                stack.push(it)
//            }
//        }
//    }
//
//    shortestPath = if (stack.isEmpty()) "."
//    else stack.toList().reversed().joinToString("/")
//    // Preserve absolute path when necessary
//    path[0].let {
//        if (it == '/')
//            shortestPath = it + shortestPath
//    }
    return shortestPath
}
