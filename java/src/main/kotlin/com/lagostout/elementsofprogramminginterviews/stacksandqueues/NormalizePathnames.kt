package com.lagostout.elementsofprogramminginterviews.stacksandqueues

import org.apache.commons.collections4.iterators.PeekingIterator
import java.util.*

/**
 * Problem 9.4 page 139
 */
fun shortestEquivalentPath(path: String): String {
    var shortestPath  = ""
    if (path.isEmpty()) return shortestPath
    // Break path into parts
    val stack = LinkedList<String>()
    val delimiters = Regex("(?<=/)|(?=/)|(?<=\\.\\.)|" +
            "(?=\\.\\.)|(?<=\\.!\\.)|(?=\\.!\\.)")
    val pathParts = path.split(delimiters).filterNot { it.isEmpty() }
    val pathPartsIterator = PeekingIterator(pathParts.iterator())
    println(pathParts)
    pathPartsIterator.forEach {
        when (it) {
            "/" -> {
                // TODO If the stack is empty, don't push "/" if it
                // isn't the first part in the path.
                if (stack.peek() != it)
                    stack.push(it)
            }
            "." -> {
                if (pathPartsIterator.peek() == "/")
                    pathPartsIterator.next()
            }
            ".." -> {
                if (stack.peek() == "/") {
                    if (stack.size == 1)
                        throw IllegalArgumentException()
                    stack.pop()
                }
                if (stack.isEmpty()) stack.push(it)
                else if (stack.isNotEmpty() && stack.peek() == "..") {
                    stack.push("/")
                    stack.push("..")
                } else stack.pop()
            }
            else -> stack.push(it)
        }
    }
    if (stack.isEmpty()) stack.push(".")
    else if (stack.peek() == "/" && stack.size > 1) stack.pop()
    shortestPath = StringBuilder().apply {
        stack.reversed().forEach {
            this.append(it)
        }
    }.toString()
    return shortestPath
}
