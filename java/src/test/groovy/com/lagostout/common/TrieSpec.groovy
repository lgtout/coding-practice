package com.lagostout.common

import spock.lang.Specification
import spock.lang.Unroll

class TrieSpec extends Specification {

    @Unroll("#data")
    'builds trie - manual cases'(List<String> trieStrings) {

        when:
        def trie = new Trie(trieStrings)

        then:
        toSourceStrings(trie) - trieStrings == []

        where:
        trieStrings << [
                ['A'],
                ['AA'],
                ['A','B'],
                ['A','AB'],
                ['AB','AB'],
                ['AB','AC'],
                ['AB','AC','BC'],
                ['ABC','ABDE','ABCF'],
        ]

    }

    private static List<String> toSourceStrings(Trie trie) {
        final stack = new LinkedList<Frame>()
        final path = new LinkedList<Character>()
        def vertex = 0
        stack.push(new Frame(vertex))
        final stringsFound = []
        final char[] ignoredChars = ['\0','$']
        while (!stack.isEmpty()) {
            Frame frame = stack.peekLast()
            switch (frame.step) {
                case 1:
                    path.push(frame.edge)
                    Map<Character, Integer> adjList =
                            trie.getAdjList(frame.vertex)
                    // End of string
                    if (adjList.values().isEmpty()) {
                        def sb = new StringBuilder()
                        path.each {
                            if (!(ignoredChars.contains(it))) {
                                sb.append(it)
                            }
                        }
                        stringsFound << sb.toString()
                    } else {
                        adjList.each { key, value ->
                            stack.push(new Frame(value, key))
                        }
                    }
                    frame.nextStep()
                    break
                case 2:
                    stack.pop()
                    if (!path.isEmpty()) path.pop()
                    break
            }
        }
        stringsFound
    }

    private static class Frame {
        int vertex
        int step = 1
        char edge = '\0'

        Frame(int vertex) {
            this.vertex = vertex
        }
        Frame(int vertex, char edge) {
            this(vertex)
            this.edge = edge
        }
        void nextStep() {
            if (step == 1) step++
        }
    }

    @Unroll("text:#text patterns:#patterns expected:#matches")
    'finds matches - manual cases'(String text,
                                   List<String> patterns,
                                   Map<Integer, List<Integer>> matches) {

        when:
        def trie = new Trie(patterns)
//        println "adjacencies $trie.adjacencies"

        then:
        trie.findMatches(text) == matches

        where:
        [text, patterns, matches] << [
                // Let's assume that there's at least one
                // pattern and one character in the string we search.
                ['A', ['A'], [0:[0]]],
//                ['A', ['B'], [[]]],
//                ['BA', ['A'], [0:[1]]],
//                ['BA', ['A'], [0:[1]]],
//                ['AA', ['A'], [0:[0,1]]],
//                ['ABA', ['A'], [0:[0,2]]],
//                ['AA', ['AA'], [0:[0]]],
//                ['AA', ['AA','B'], [0:[0]]],
//                ['AA', ['AA','A'], [0:[0], 1:[0,1]]],
//                ['AAA', ['AA','A'], [0:[0,1], 1:[0,1,2]]],
//                ['AA', ['B'], []],
        ]

    }

}
