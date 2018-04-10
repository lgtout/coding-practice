// Copyright (c) 2018 lagostout. All rights reserved.

import Foundation

/* Problem 17.7.2 page 328 */

class EPMinimumPalindromicDecomposition {

    static func computeWithBruteForceAndAndRecursion(str: String) -> Array<String> {

        func isPalindrome(_ substring: Substring) -> Bool {
            var result = false
            if substring.count <= 1 {
                result = true
            } else {
                var leftIndex = substring.startIndex
                var rightIndex = substring.lastIndex
                while leftIndex != rightIndex {
                    if substring[leftIndex] != substring[rightIndex] {
                        result = false
                        break
                    }
                    leftIndex = substring.index(after: leftIndex)
                    rightIndex = substring.index(before: rightIndex)
                }
                result = true
            }
            return result
        }

        func compute(_ substring: Substring) -> Array<Array<Substring>> {
            if substring.count <= 1 || isPalindrome(substring) {
                return [[substring]]
            } else {
                substring.indices.flatMap { index -> Array<Array<Substring>> in
                    let leftSubstring = substring[...index]
                    let rightSubstring = substring[substring.index(after: index)...]
                    let leftSubstringPalindromes = compute(leftSubstring)
                    let rightSubstringPalindromes = compute(rightSubstring)
                    var minimalDecompositions = [[Substring]]()
                    if !leftSubstringPalindromes.isEmpty &&
                            !rightSubstringPalindromes.isEmpty {
                        // Combine every left substring decomposition with every
                        // right substring decomposition and sort by length of
                        // decomposition.
                        let decompositionsFound: [[Substring]] = leftSubstringPalindromes
                                .flatMap { (leftPalindrome: [Substring]) in
                                    rightSubstringPalindromes.map { (rightPalindrome: [Substring]) in
                                        leftPalindrome + rightPalindrome
                                    }
                                }.sorted(by: { $0.count < $1.count })
                        let maximumAllowedSizeOfAMinimalDecomposition =
                                minimalDecompositions.first?.count
                        let sizeOfShortestDecompositionsFound =
                                decompositionsFound.first!.count
                        let replaceMinimalDecompositions =
                                minimalDecompositions.isEmpty ||
                                        sizeOfShortestDecompositionsFound <
                                                maximumAllowedSizeOfAMinimalDecomposition!
                        let addToMinimalDecompositions = sizeOfShortestDecompositionsFound ==
                                maximumAllowedSizeOfAMinimalDecomposition
                        if replaceMinimalDecompositions || addToMinimalDecompositions {
                            let shortestDecompositionsFound = decompositionsFound.filter {
                                $0.count == sizeOfShortestDecompositionsFound
                            }
                            if replaceMinimalDecompositions {
                                minimalDecompositions = shortestDecompositionsFound
                            } else {
                                minimalDecompositions += shortestDecompositionsFound
                            }
                        }
                    }
                    return minimalDecompositions
                }.sorted(by: { (p1, p2) in p1.count < p2.count }).first!

            }
        }

        return compute(str[str.startIndex...]).map { String($0) }
    }

}
