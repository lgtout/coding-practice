// Copyright (c) 2018 lagostout. All rights reserved.

import Foundation

/* Problem 17.7.2 page 328 */

class EPMinimumPalindromicDecomposition {

    /* It's possible for there to be more than one minimum decomposition.
    For example "abab" decomposes to "a" + "bab" and "aba" + "b", both of
    length 2.  So, for this brute force solution, we return all decompositions
    of minimum length. */
    static func computeWithBruteForceAndRecursion_allMinimumDecompositions
            (_ str: String) -> Array<Array<String>> {

        func isPalindrome(_ substring: Substring) -> Bool {
            let leftIndex = substring.startIndex
            let rightIndex = substring.lastIndex
            if substring[leftIndex] == substring[rightIndex] {
                return true
            } else {
                return substring[leftIndex] == substring[rightIndex] &&
                        isPalindrome(substring[
                                substring.index(after: leftIndex)..<rightIndex])
            }
        }

        func compute(_ substring: Substring) -> Array<Array<Substring>> {
            if substring.count <= 1 || isPalindrome(substring) {
                return [[substring]]
            } else {
                let decompositions = substring.indices.dropLast().flatMap {
                    index -> Array<Array<Substring>> in
                    let leftSubstring = substring[...index]
                    let rightSubstring = substring[substring.index(after: index)...]
                    let leftSubstringPalindromes = compute(leftSubstring)
                    let rightSubstringPalindromes = compute(rightSubstring)
                    var decompositions = [[Substring]]()
                    if !leftSubstringPalindromes.isEmpty &&
                            !rightSubstringPalindromes.isEmpty {
                        // Combine every left substring decomposition with every
                        // right substring decomposition.
                        decompositions += leftSubstringPalindromes
                                .flatMap { (leftPalindrome: [Substring]) in
                                    rightSubstringPalindromes.map { (rightPalindrome: [Substring]) in
                                        leftPalindrome + rightPalindrome
                                    }
                                }
                    }
                    return decompositions
                }.sorted(by: { (p1, p2) in p1.count < p2.count })
                let minimumDecomposition = decompositions.prefix(while: {
                    $0.count == decompositions.first!.count })
                return Array(minimumDecomposition)
            }
        }
        let decompositions = compute(str[str.startIndex...])
        print(decompositions)
        return decompositions.map { $0.map { String($0) } }
    }

    /* Here, in contrast to the previous brute force implementation,
    we return only a single minimum decomposition per string */
    static func computeWithBruteForceAndRecursion(_ str: String) -> Array<String> {

        func compute(_ substring: Substring) -> Array<Substring> {
            if substring.count <= 1 {
                return [substring]
            } else {
                var decompositions = substring.indices.dropLast().map {
                    index -> Array<Substring> in
                    let leftSubstring = substring[...index]
                    let rightSubstring = substring[substring.index(after: index)...]
                    let leftSubstringPalindromes = compute(leftSubstring)
                    let rightSubstringPalindromes = compute(rightSubstring)
                    let decompositions = leftSubstringPalindromes +
                            rightSubstringPalindromes
                    return decompositions
                }
                decompositions = decompositions.map { (decomposition: [Substring]) ->
                    [Substring] in
                    // If the first half of the decomposition is a mirror image of
                    // the second half, the substring is a palindrome, and we join
                    // the decomposition.
                    if ([2,3].contains(decomposition.count) &&
                        decomposition.first!.count == decomposition.last!.count &&
                        String(decomposition.first!.reversed()) == String(decomposition.last!)) {
                        return [Substring(decomposition.joined())]
                    } else {
                        return decomposition
                    }
                }.sorted(by: { (p1, p2) in p1.count < p2.count })
                return decompositions.first!
            }
        }

        return compute(str[str.startIndex...]).map { String($0) }
    }

    static func computeWithRecursionAndMemoization(_ str: String) -> Array<String> {
        var cache = [Substring:Array<Substring>]()

        func compute(_ substring: Substring) -> Array<Substring> {
            if let result = cache[substring] {
//                print("cache hit key: \(substring) decomp: \(result)")
                return result
            } else {
                let result: Array<Substring>
                if substring.count <= 1 {
                    result = [substring]
                } else {
                    var decompositions = substring.indices.dropLast().map {
                        index -> Array<Substring> in
                        let leftSubstring = substring[...index]
                        let rightSubstring = substring[substring.index(after: index)...]
                        let leftSubstringPalindromes = compute(leftSubstring)
                        let rightSubstringPalindromes = compute(rightSubstring)
                        let decompositions = leftSubstringPalindromes +
                                rightSubstringPalindromes
                        return decompositions
                    }
                    decompositions = decompositions.map { (decomposition: [Substring]) -> [Substring] in
                        // If the first half of the decomposition is a mirror image of
                        // the second half, the substring is a palindrome, and we unite
                        // the members of the decomposition.
                        if ([2,3].contains(decomposition.count) &&
                                decomposition.first!.count == decomposition.last!.count &&
                                String(decomposition.first!.reversed()) == String(decomposition.last!)) {
                            return [Substring(decomposition.joined())]
                        } else {
                            return decomposition
                        }
                    }.sorted(by: { (p1, p2) in p1.count < p2.count })
                    result = decompositions.first!
                }
                cache[substring] = result
                return result
            }
        }
        return compute(str[str.startIndex...]).map { String($0) }
    }

    static func computeBottomUpWithMemoization(_ str: String) -> Array<String> {
        guard str.count > 1 else {
            return [str]
        }
        var cache = [Substring : Array<Array<Substring>>]()
        for index in str.indices {
            let substring = str[index...index]
            cache[substring] = [[substring]]
        }
        for substringLength in 2...str.count {
            var substring = str[str.startIndex..<str.index(str.startIndex, offsetBy: substringLength)]
            while true {
                var decompositions = [[Substring]]()
                var leftSubstring = substring[substring.startIndex...substring.startIndex]
                var rightSubstring = substring[leftSubstring.endIndex...]
                while true {
                    decompositions += cache[leftSubstring]?
                        .flatMap { (leftDecomposition: [Substring]) -> [[Substring]] in
                            cache[rightSubstring]?.map { rightDecomposition in
                                leftDecomposition + rightDecomposition } ?? [leftDecomposition]
                        }.map { decomposition in
                            if ([2,3].contains(decomposition.count) &&
                                decomposition.first!.count == decomposition.last!.count &&
                                String(decomposition.first!.reversed()) == String(decomposition.last!)) {
                                return [Substring(decomposition.joined())]
                            } else {
                                return decomposition
                            }
                        } ?? [[Substring]]()
                    guard rightSubstring.count > 1 else {
                        break
                    }
                    leftSubstring = substring[substring.startIndex..<substring.index(after: leftSubstring.endIndex)]
                    rightSubstring = substring[leftSubstring.endIndex...]
                }
                let minDecompositionCount = decompositions.min(
                    by: { $0.count <= $1.count })!.count
                cache[substring] = decompositions.filter(
                    { $0.count == minDecompositionCount })
                guard substring.endIndex != str.endIndex else {
                    break
                }
                substring = str[substring.index(after: substring.startIndex)..<str.index(after: substring.endIndex)]
            }
        }
        // We computed all the minimum decompositions, but we return only one
        // since that's what our tests expect.
        return cache[str[str.startIndex...]]!.first!.map { String($0) }
    }
}
