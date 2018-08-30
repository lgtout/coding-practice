package com.lagostout.bytebybyte.recursion

fun findAllSubstringsOfString(s: String): List<String> {
    return findAllSubstringsOfString(s, 0, 1)
}

private fun findAllSubstringsOfString(s: String, startIndex: Int, endIndex: Int): List<String> {
    return when {
        startIndex > s.lastIndex -> return emptyList()
        endIndex > s.length -> findAllSubstringsOfString(s, startIndex + 1, startIndex + 2)
        else -> listOf(s.substring(startIndex, endIndex)) +
                findAllSubstringsOfString(s, startIndex, endIndex + 1)
    }
}
