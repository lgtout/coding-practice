package com.lagostout.elementsofprogramminginterviews.stacksandqueues

import org.apache.commons.collections4.iterators.PeekingIterator
import java.util.*

/**
 * Problem 9.4 page 139
 */
fun shortestEquivalentPath(path: String): String {
    // We'll assume the paths are well-formed because
    // validating well-formedness is non-trivial.
    // But we'll throw an exception if you try to go up
    // a directory when you're at the root: "/..".
    var shortestPath  = ""
    if (path.isEmpty()) return shortestPath
    // Break path into parts
    val stack = LinkedList<String>()
    val delimiters = Regex("(?<=/)|(?=/)|(?<=\\.\\.)|" +
            "(?=\\.\\.)|(?<=\\.!\\.)|(?=\\.!\\.)")
    val pathParts = path.split(delimiters).filterNot { it.isEmpty() }
    val pathPartsIterator = PeekingIterator(pathParts.withIndex().iterator())
//    println(pathParts)
    pathPartsIterator.forEach { (index, pathPart) ->
        when (pathPart) {
            "/" -> {
                if (stack.peek() == ".") stack.pop()
                if (index == 0 || (stack.isNotEmpty() && stack.peek() != pathPart))
                    stack.push(pathPart)
            }
            "." -> {
                if (stack.isEmpty())
                    stack.push(pathPart)
            }
            ".." -> {
                if (stack.peek() == "/") {
                    if (stack.size == 1)
                        throw IllegalArgumentException()
                    stack.pop()
                }
                if (stack.isEmpty()) stack.push(pathPart)
                else if (stack.isNotEmpty() && stack.peek() == "..") {
                    stack.push("/")
                    stack.push("..")
                } else stack.pop()
            }
            else -> stack.push(pathPart)
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
