// Copyright (c) 2018 lagostout. All rights reserved.

import Foundation

/* EPIJ - Problem 17.7.1 page 326 */

class EPBedBathAndBeyondProblem {

    /*
     This implementation deviates from the others, and the problem
     requirements by requiring all dictionary words to appear in the
     domain.  If they do, the base case returns the full array
     containing the words.  To achieve this, we pass each word down
     to the base case.  Because the expected results conform to the
     problem requirements, this implementation fails two of the test
     cases.
    */
    static func computeWithBruteForceAndRecursion1(
            domain: String, dictionary: Set<String>) -> Array<Array<String>> {
        let dictionary = Array(dictionary)

        func compute(_ domain: Substring,
                     _ concatenation: Array<String>) -> Array<Array<String>> {
            if (domain.count == 0) {
                let concatenationContainsAllWordsInDictionary =
                        Set(concatenation) == Set(dictionary)
                if (concatenationContainsAllWordsInDictionary) {
                    return [concatenation]
                } else {
                    return []
                }
            } else {
                return dictionary.flatMap {
                    word -> Array<Array<String>> in
                    var concatenations: Array<Array<String>> = []
                    if domain.hasPrefix(word) {
                        concatenations += compute(domain.suffix(from: domain.index(
                                domain.startIndex, offsetBy: word.count)),
                                concatenation + [word])
                    }
                    return concatenations
                }
            }
        }

        if dictionary.isEmpty {
            return []
        } else {
            return compute(domain.suffix(domain.count), [])
        }
    }

    /*
     We take a different approach to brute force and recursion.  We ask: What
     are the concatenations possible in the substring that follows the current
     index, regardless of the dictionary words undiscovered so far?  Also, we
     don't pass words found down to the base case.  Instead, at each recursion
     level, we add any word that's a prefix of the current substring to the
     result of searching the remaining suffix of the substring.
    */
    static func computeWithBruteForceAndRecursion2(
            domain: String, dictionary: Set<String>) -> Array<Array<String>> {
        let dictionary = Array(dictionary)

        func compute(_ domain: Substring) -> Array<Array<String>> {
            if (domain.count == 0) {
                return [[]]
            } else {
                return dictionary.flatMap {
                    word -> Array<Array<String>> in
                    var concatenations: Array<Array<String>> = []
                    if domain.hasPrefix(word) {
                        concatenations += compute(domain.suffix(from: domain.index(
                                domain.startIndex, offsetBy: word.count))).map {
                            concatenation in
                            return [word] + concatenation
                        }
                    }
                    print("concatenations \(concatenations)")
                    return concatenations
                }
            }
        }

        if dictionary.isEmpty {
            return []
        } else {
            return compute(domain.suffix(domain.count))
        }
    }

    static func computeWithRecursionAndMemoization(
            domain: String, dictionary: Set<String>) -> Array<Array<String>> {
        let dictionary = Array(dictionary)
        var cache = Dictionary<String.Index, Array<Array<String>>>()

        func compute(_ domainSubstring: Substring) -> Array<Array<String>> {
            let key = domain.index(domainSubstring.startIndex, offsetBy: 0)
            if let concatenations = cache[key] {
                return concatenations
            } else {
                if (domainSubstring.count == 0) {
                    return [[]]
                } else {
                    let concatenationsForAllDictionaryWords = dictionary.flatMap {
                        word -> Array<Array<String>> in
                        var concatenationsForCurrentDictionaryWord: Array<Array<String>> = []
                        if domainSubstring.hasPrefix(word) {
                            concatenationsForCurrentDictionaryWord += compute(
                                    domainSubstring.suffix(from: domainSubstring.index(
                                            domainSubstring.startIndex, offsetBy: word.count)))
                                    .map { concatenation in
                                        return [word] + concatenation
                                    }
                        }
                        return concatenationsForCurrentDictionaryWord
                    }
                    cache[key] = (cache[key] ?? [[String]]()) +
                            concatenationsForAllDictionaryWords
                }
            }
            return cache[key]!
        }

        if dictionary.isEmpty {
            return []
        } else {
            let result = compute(domain.suffix(domain.count))
            print(cache)
            return result
        }
    }

    static func computeBottomUpWithMemoization(
            domain: String, dictionary: Set<String>) ->
            Array<Array<String>> {
        let dictionary = Array(dictionary)
        var cache = Dictionary<String.Index, Array<Array<String>>>()
        for index in domain.indices {
            for word in dictionary {
                guard domain[...index].count >= word.count else { continue }
                if domain[...index].hasSuffix(word) {
                    let wordStartIndex = domain.index(index, offsetBy: -(word.count - 1))
                    if wordStartIndex == domain.startIndex {
                        cache[index] = [[word]]
                    } else if let previousConcatenations = cache[
                        domain.index(wordStartIndex, offsetBy: -1)] {
                        cache[index] = (cache[index] ?? []) +
                            previousConcatenations.map {
                                $0 + [word]
                            }
                    }
                }
            }
        }
        return cache[domain.lastIndex] ?? []
    }

}

