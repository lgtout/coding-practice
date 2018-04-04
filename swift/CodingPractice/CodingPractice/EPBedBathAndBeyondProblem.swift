// Copyright (c) 2018 lagostout. All rights reserved.

import Foundation

/* EPIJ - Problem 17.7.1 page 326 */

class EPBedBathAndBeyondProblem {

    /*
    At each character in url, we'll check if either we can
    match it with the urlIndex of the currentWord, or with
    the first character of any word in the dictionary.  As
    we unwind the recursion, we'll select for results that
    contain the most distinct words in the dictionary.  The
    results will be distinct words found from the current
    point in the recursion, down to the base case.
     */
    static func computeWithBruteForceAndRecursion(
            url: String, dictionary: Set<String>) -> Array<Array<String>> {
//        print("\(url) \(dictionary)")
        let dictionary = Array(dictionary)
        func compute(_ url: Substring,
                     _ concatenation: Array<String>) -> Array<Array<String>> {
//            print("url \(url) concatenation \(concatenation)")
            if (url.count == 0) {
//                print("url.count \(url.count)")
                let concatenationContainsAllWordsInDictionary =
                        Set(concatenation) == Set(dictionary)
//                print("concatenationContainsAllWordsInDictionary " +
//                        "\(concatenationContainsAllWordsInDictionary)")
                if (concatenationContainsAllWordsInDictionary) {
                    return [concatenation]
                } else {
                    return []
                }
            } else {
                return dictionary.flatMap {
                    word -> Array<Array<String>> in
//                    print("word \(word)")
                    var concatenations: Array<Array<String>>
                    if url.hasPrefix(word) {
                        concatenations = compute(url.suffix(from: url.index(
                                url.startIndex, offsetBy: word.count)),
                                concatenation + [word])
                    } else {
                        concatenations = []
                    }
//                    print("concatenations \(concatenations)")
                    return concatenations
                }
            }
        }
        if dictionary.isEmpty {
            return []
        } else {
           return compute(url.suffix(url.count), [])
        }
    }

}

