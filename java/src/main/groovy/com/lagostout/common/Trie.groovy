package com.lagostout.common

import com.google.common.annotations.VisibleForTesting

class Trie {

    static final char PATTERN_END = '$'

    @VisibleForTesting
    final List<Map<Character, Integer>> allAdjacencies

    @VisibleForTesting
    final  Map<Integer, Integer> patternEndVertexToPatternMap

    Map<Character, Integer> getAdjList(int vertex) {
        allAdjacencies.get(vertex)
    }

    Trie(List<String> patterns) {
        allAdjacencies = new ArrayList<>()
        patternEndVertexToPatternMap = new HashMap<>()
        allAdjacencies.add(new HashMap<>())
        patterns.eachWithIndex { String pattern, int index ->
            pattern = pattern.concat(Character.toString(PATTERN_END))
            Integer vertex = 0
            for (Character ch : pattern.toCharArray()) {
                Map<Character, Integer> adjacents = allAdjacencies.get(vertex)
                vertex = adjacents.get(char)
                if (vertex == null) {
                    vertex = allAdjacencies.size()
                    adjacents.put(ch, vertex)
                    allAdjacencies.add(new HashMap<>())
                    if (ch == PATTERN_END) {
                        patternEndVertexToPatternMap.put(vertex, index)
                    }
                }
            }
        }
    }

    /**
     * Finds match locations for every pattern matched.
     * @param text
     * @param trie
     * @return
     */
    Map<Integer, List<Integer>> findMatches(String text) {
        Map<Integer, List<Integer>> patternToMatchPositionsMap = new HashMap<>()
        println "all adjacencies $allAdjacencies"
        text.toCharArray().eachWithIndex { char ch, int chIndex ->
            int vertex = 0
            println(Character.toString(ch) + " " + chIndex)
//            --chIndex
            while (true) {
                Map<Character, Integer> adjacencies = allAdjacencies.get(vertex)
                if (adjacencies.containsKey(PATTERN_END)) {
                    int patternEndVertex = adjacencies.get(PATTERN_END)
                    int patternIndex = patternEndVertexToPatternMap.get(patternEndVertex)
                    List<Integer> matchPositions =
                            patternToMatchPositionsMap.getOrDefault(
                                    patternIndex, new ArrayList<>())
                    matchPositions.add(chIndex)
                }
                if (++chIndex)
                if (adjacencies.containsKey(ch)) {
                    vertex = adjacencies.get(ch)
//                    ++chIndex
                }
//                if (!adjacencies.containsKey(ch)) break
//                vertex = adjacencies.get(ch)
                if (++chIndex == text.size()) {
                    break
                }
                ch = text.charAt(chIndex)
            }
        }
        return patternToMatchPositionsMap
    }

}
