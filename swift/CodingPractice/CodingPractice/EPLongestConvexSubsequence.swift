// Copyright (c) 2018 lagostout. All rights reserved.

import Foundation

/* Problem 17.12.5 page 337 */

class EPLongestConvexSubsequence {

    static func computeWithBruteForceWithoutRecursion(sequence: [Int]) -> [[Int]] {
        if sequence.isEmpty {
            return []
        }
        var cache: [[(index: Int, value: Int)]] = []
        var currIndex = 0
        while currIndex < sequence.endIndex {
            let currValue = sequence[currIndex]
            for subsequence in cache {
                var appendCurrValue = true
                if subsequence.count >= 2 {
                    let firstValue = subsequence[
                            subsequence.endIndex - 2].value
                    let secondValue = subsequence[
                            subsequence.endIndex - 1].value
                    appendCurrValue = Double(secondValue)
                            < Double(firstValue + currValue) / 2.0
                }
                if appendCurrValue {
                    let newSubsequence = subsequence + [(currIndex, currValue)]
                    cache.append(newSubsequence)
                }
            }
            cache.append([(currIndex, currValue)])
            currIndex += 1
        }
        let sortedCache = cache.filter { $0.count >= 3 }
                .sorted { $0.count >= $1.count }
        let result = sortedCache.prefix {
            $0.count == sortedCache.first!.count
        }.map { $0.map { $0.value } }
        return result
    }

    /* Compute processes 3 numbers at a time.  But we call compute once per
     pair of numbers. */

    static func computeByBruteForceWithRecursion1(sequence: [Int]) -> [[Int]] {
        if sequence.count < 3 {
            return []
        }
        let lastIndex = sequence.endIndex - 1
        func compute(_ firstNumberIndex: Int,
                     _ secondNumberIndex: Int,
                     _ currentNumberIndex: Int) -> [[Int]] {

            if currentNumberIndex > lastIndex {
                return [[]]
            }

            let firstNumber = sequence[firstNumberIndex]
            let secondNumber = sequence[secondNumberIndex]
            let suffixStartIndex = currentNumberIndex + 1

            // Include current number
            var subsequencesByIncludingCurrentNumber: [[Int]] = [[]]
            let currentNumber = sequence[currentNumberIndex]
            let minThirdNumber = 2 * secondNumber - firstNumber
            if currentNumber > minThirdNumber {
                subsequencesByIncludingCurrentNumber =
                        compute(secondNumberIndex, currentNumberIndex,
                                suffixStartIndex).map {
                            [currentNumber] + $0
                        }
            }

            // Exclude current number
            let subsequencesByExcludingCurrentNumber =
                    compute(firstNumberIndex, secondNumberIndex, suffixStartIndex)

            // Select optimal result
            let subsequencesByExcludingCurrentNumberCount =
                    subsequencesByExcludingCurrentNumber.first?.count ?? 0
            let subsequencesByIncludingCurrentNumberCount =
                    subsequencesByIncludingCurrentNumber.first?.count ?? 0
            var result: [[Int]] = subsequencesByIncludingCurrentNumber
            if subsequencesByExcludingCurrentNumberCount ==
                    subsequencesByIncludingCurrentNumberCount {
                result += subsequencesByExcludingCurrentNumber
            } else if subsequencesByExcludingCurrentNumberCount >
                    subsequencesByIncludingCurrentNumberCount {
                result = subsequencesByExcludingCurrentNumber
            }

            return result
        }

        var longestSubsequenceCount = 0
        var result: [[Int]] = []
        for firstNumberIndex in 0...(lastIndex - 2) {
            let firstNumber = sequence[firstNumberIndex]
            for secondNumberIndex in (firstNumberIndex + 1)...(lastIndex - 1) {
                let secondNumber = sequence[secondNumberIndex]
                let thirdNumberIndex = secondNumberIndex + 1
                let subsequences = compute(
                        firstNumberIndex, secondNumberIndex, thirdNumberIndex
                ).filter {
                    !$0.isEmpty
                }.map {
                    [firstNumber, secondNumber] + $0
                }
                let subsequenceCount = subsequences.first?.count ?? 0
                if subsequenceCount > longestSubsequenceCount {
                    longestSubsequenceCount = subsequenceCount
                    result = subsequences
                } else if subsequenceCount == longestSubsequenceCount {
                    result += subsequences
                }
            }
        }

        return result.distinct()
    }

    /* Processes 3 numbers at a time.  We use recursion to iterate over all possible
     starting pairs of numbers, calling compute() with each pair and the first possible
     third number of the pair. compute() uses recurses to explore all possible third
     numbers of a given first and second number pair. */

    static func computeByBruteForceWithRecursion2(sequence: [Int]) -> [[Int]] {
        if sequence.count < 3 {
            return []
        }

        let lastIndex = sequence.endIndex - 1

        func compute(_ firstNumberIndex: Int,
                     _ secondNumberIndex: Int,
                     _ currentNumberIndex: Int) -> [[Int]] {

            if currentNumberIndex > lastIndex {
                return [[]]
            }

            let firstNumber = sequence[firstNumberIndex]
            let secondNumber = sequence[secondNumberIndex]
            let suffixStartIndex = currentNumberIndex + 1

            // Include current number
            var subsequencesByIncludingCurrentNumber: [[Int]] = [[]]
            let currentNumber = sequence[currentNumberIndex]
            let minThirdNumber = 2 * secondNumber - firstNumber
            if currentNumber > minThirdNumber {
                subsequencesByIncludingCurrentNumber =
                        compute(secondNumberIndex, currentNumberIndex,
                                suffixStartIndex).map {
                            [currentNumber] + $0
                        }
            }

            // Exclude current number
            let subsequencesByExcludingCurrentNumber =
                    compute(firstNumberIndex, secondNumberIndex, suffixStartIndex)

            // Select optimal result
            let subsequencesByExcludingCurrentNumberCount =
                    subsequencesByExcludingCurrentNumber.first?.count ?? 0
            let subsequencesByIncludingCurrentNumberCount =
                    subsequencesByIncludingCurrentNumber.first?.count ?? 0
            var result: [[Int]] = subsequencesByIncludingCurrentNumber
            if subsequencesByExcludingCurrentNumberCount ==
                       subsequencesByIncludingCurrentNumberCount {
                result += subsequencesByExcludingCurrentNumber
            } else if subsequencesByExcludingCurrentNumberCount >
                              subsequencesByIncludingCurrentNumberCount {
                result = subsequencesByExcludingCurrentNumber
            }

            return result
        }

        func seconds(_ firstNumberIndex: Int, _ secondNumberIndex: Int,
                     _ longestSubsequences: [[Int]]) -> [[Int]] {
            if secondNumberIndex > lastIndex - 1 {
                return longestSubsequences
            }
            let firstNumber = sequence[firstNumberIndex]
            let secondNumber = sequence[secondNumberIndex]
            let thirdNumberIndex = secondNumberIndex + 1
            let subsequences = compute(
                    firstNumberIndex, secondNumberIndex, thirdNumberIndex
            ).filter {
                !$0.isEmpty
            }.map {
                [firstNumber, secondNumber] + $0
            }
            let subsequenceCount = subsequences.first?.count ?? 0
            let longestSubsequenceCount = longestSubsequences.first?.count ?? 0
            var result: [[Int]] = longestSubsequences
            if subsequenceCount > longestSubsequenceCount {
                result = subsequences
            } else if subsequenceCount == longestSubsequenceCount {
                result += subsequences
            }
            return seconds(firstNumberIndex, secondNumberIndex + 1, result)
        }

        func firsts(_ firstIndex: Int, _ longestSubsequences: [[Int]]) -> [[Int]] {
            if firstIndex > lastIndex - 2 {
                return longestSubsequences
            }
            let result = seconds(firstIndex, firstIndex + 1, longestSubsequences)
            return firsts(firstIndex + 1, result)
        }

        return firsts(0, [])
    }

    static func selectLongest(_ arrays: [[Int]]) -> [[Int]] {
        return arrays.reduce([[Int]]()) { acc, curr in
            if acc.isEmpty {
                return [curr]
            } else {
                let subsequenceCount = acc.first?.count ?? 0
                let currCount = curr.count
                if currCount > subsequenceCount {
                    return [curr]
                } else if currCount == subsequenceCount {
                    return acc + [curr]
                }
                return acc
            }
        }
    }

    /* Processes 2 numbers at a time. Uses iteration to iterate over the start
     index of all possible suffixes in the initial sequence. It doesn't provide
     a seed minDistance for each suffix.  Instead, the recursive compute()
     computes a value for minDistance when one isn't supplied. */

    static func computeByBruteForceWithRecursion3(sequence: [Int]) -> [[Int]] {
        if sequence.count <= 2 {
            return []
        }

        let lastIndex = sequence.endIndex - 1

        func compute(_ firstNumberIndex: Int,
                     _ secondNumberIndex: Int,
                     _ minDistance: Int?) -> [[Int]] {

            // print("compute(\(firstNumberIndex), \(secondNumberIndex), \(minDistance))")

            if secondNumberIndex > lastIndex {
                return [[]]
            }

            let firstNumber = sequence[firstNumberIndex]
            let secondNumber = sequence[secondNumberIndex]
            let nextNumberIndex = secondNumberIndex + 1

            // Keep current number
            var subsequencesBySelectingSecondNumber: [[Int]] = []

            // Starting a new subsequence
            let distanceBetweenFirstAndSecondNumbers = secondNumber - firstNumber
            if minDistance == nil || distanceBetweenFirstAndSecondNumbers > minDistance! {
                subsequencesBySelectingSecondNumber = compute(
                        secondNumberIndex, nextNumberIndex,
                        distanceBetweenFirstAndSecondNumbers).map {
                    let prefix = minDistance == nil ? [firstNumber, secondNumber]
                            : [secondNumber]
                    return prefix + $0
                }
            }

            // Skip current number
            let subsequencesBySelectingNextNumber = compute(
                    firstNumberIndex, nextNumberIndex, minDistance)

            // Select optimal result
            let subsequences = (subsequencesBySelectingSecondNumber +
                    subsequencesBySelectingNextNumber).distinct()

            let result = selectLongest(subsequences)

            // print("compute(\(firstNumberIndex), \(secondNumberIndex), \(minDistance)) = \(result)")

            return result
        }

        let subsequences: [[Int]] = (0...(lastIndex - 2)).flatMap { index -> [[Int]] in
            let nextIndex = index + 1
            // print("compute subsequences for suffix starting at \(index)")
            let result = compute(index, nextIndex, nil)
                    .filter {
                        $0.count >= 3
                    }
            // print("result \(result)")
            return result
        }.filter { !$0.isEmpty }.withoutDuplicates()

        let result = selectLongest(subsequences)

        return result
    }

    /* Processes 2 numbers at a time.  The recursive compute() is always provided with a
     non-nil minDistance argument. This makes its compute() a little simpler than when
     minDistance is an optional. A trivial difference is that we use flatMap instead of
     looping to iterate all possible subsequence starting pairs. */

    static func computeByBruteForceWithRecursion4(sequence: [Int]) -> [[Int]] {
        if sequence.count <= 2 {
            return []
        }

        let lastIndex = sequence.endIndex - 1

        func compute(_ firstNumberIndex: Int,
                     _ secondNumberIndex: Int,
                     _ minDistance: Int) -> [[Int]] {

            // print("compute(\(firstNumberIndex), \(secondNumberIndex), \(minDistance))")

            if secondNumberIndex > lastIndex {
                return [[]]
            }

            let firstNumber = sequence[firstNumberIndex]
            let secondNumber = sequence[secondNumberIndex]
            let nextNumberIndex = secondNumberIndex + 1

            // Include second number if possible.
            var subsequencesByIncludingSecondNumber: [[Int]] = []
            let distanceWhenIncludingSecondNumber = secondNumber - firstNumber
            if distanceWhenIncludingSecondNumber > minDistance {
                subsequencesByIncludingSecondNumber =
                        compute(secondNumberIndex, nextNumberIndex,
                                distanceWhenIncludingSecondNumber).map {
                            [secondNumber] + $0
                        }
            }

            // Skip number at suffix start index and explore next suffix instead.
            let subsequencesBySkippingSecondNumber =
                    compute(firstNumberIndex, nextNumberIndex, minDistance)

            // Select optimal result
            let subsequences = (subsequencesByIncludingSecondNumber +
                    subsequencesBySkippingSecondNumber).withoutDuplicates()
            let result = selectLongest(subsequences)

            // print("compute(\(firstNumberIndex), \(secondNumberIndex), \(minDistance)) = \(result)")

            return result
        }

        /* Iterate over every possible subsequence starting pair, calling compute() with each one.
         On a trivial note, we halt at the second to last possible pair (indices (n-3, n-2)) that
         can be generated from the sequence.  This is the same as iterating to the last possible pair
         (indices: (n-2, n-1)) because the latter case yields a subsequence shorter than the
         minimum length of 3, so this subsequence gets excluded either way. */

        let subsequences: [[Int]] = (0...lastIndex - 2).flatMap { firstNumberIndex -> [[Int]] in
            let firstNumber = sequence[firstNumberIndex]
            let result = (firstNumberIndex + 1...lastIndex - 1).flatMap {
                secondNumberIndex -> [[Int]] in
                let secondNumber = sequence[secondNumberIndex]
                let nextNumberIndex = secondNumberIndex + 1
                let minDistance = secondNumber - firstNumber
                let result = compute(secondNumberIndex, nextNumberIndex, minDistance)
                        .filter {
                            $0.count >= 1
                        }
                        .map { subsequence in
                            [firstNumber, secondNumber] + subsequence
                        }
                return result
            }
            return result
        }.withoutDuplicates()

        let result = selectLongest(subsequences)

        return result
    }

    /* Processes 2 numbers at a time.  We iterate over every possible subsequence
    starting pair using recursion. */

    static func computeByBruteForceWithRecursion5(sequence: [Int]) -> [[Int]] {
        if sequence.count <= 2 {
            return []
        }

        let lastIndex = sequence.endIndex - 1

        func compute(_ firstNumberIndex: Int,
                     _ secondNumberIndex: Int,
                     _ minDistance: Int) -> [[Int]] {

            // print("compute(\(firstNumberIndex), \(secondNumberIndex), \(minDistance))")

            if secondNumberIndex > lastIndex {
                return [[]]
            }

            let firstNumber = sequence[firstNumberIndex]
            let secondNumber = sequence[secondNumberIndex]
            let nextNumberIndex = secondNumberIndex + 1

            // Include second number if possible.
            var subsequencesByIncludingSecondNumber: [[Int]] = []
            let distanceWhenIncludingSecondNumber = secondNumber - firstNumber
            if distanceWhenIncludingSecondNumber > minDistance {
                subsequencesByIncludingSecondNumber =
                        compute(secondNumberIndex, nextNumberIndex,
                                distanceWhenIncludingSecondNumber).map {
                            [secondNumber] + $0
                        }
            }

            // Skip number at suffix start index and explore next suffix instead.
            let subsequencesBySkippingSecondNumber =
                    compute(firstNumberIndex, nextNumberIndex, minDistance)

            // Select optimal result
            let subsequences = (subsequencesByIncludingSecondNumber +
                    subsequencesBySkippingSecondNumber).withoutDuplicates()
            let result = selectLongest(subsequences)

            // print("compute(\(firstNumberIndex), \(secondNumberIndex), \(minDistance)) = \(result)")

            return result
        }

        /* Iterate over every starting pair */
        func compute(_ firstNumberIndex: Int, _ secondNumberIndex: Int) -> [[Int]] {

            // print("compute(\(firstNumberIndex), \(secondNumberIndex))")

            if firstNumberIndex > lastIndex - 1 {
                return [[]]
            }
            if secondNumberIndex > lastIndex {
                return compute(firstNumberIndex + 1, firstNumberIndex + 2)
            }
            let firstNumber = sequence[firstNumberIndex]
            let secondNumber = sequence[secondNumberIndex]
            let minDistanceByIncludingSecondNumber = secondNumber - firstNumber
            let nextNumberIndex = secondNumberIndex + 1
            let subsequences = compute(secondNumberIndex, nextNumberIndex,
                    minDistanceByIncludingSecondNumber)
                    .map { subsequence in
                        [firstNumber, secondNumber] + subsequence
                    }
            let subsequencesBySkippingSecondNumber = compute(
                    firstNumberIndex, secondNumberIndex + 1)
            let allSubsequences = (subsequences + subsequencesBySkippingSecondNumber)
                    .distinct()
                    .filter {
                        $0.count >= 3
                    }
            let result = selectLongest(allSubsequences)

            // print("compute(\(firstNumberIndex), \(secondNumberIndex)) = \(result)")

            return result
        }

        let result = compute(0, 1)

        return result
    }

    /* Processes 2 numbers at a time. Iteration of all possible suffixes of sequence is
     handled during recursion by compute(), rather than as a separate step.

     To know when to prepend a qualifying number, we keep track of which subsequences
     are open or closed to extension by grouping them.  */

    static func computeByBruteForceWithRecursion6(sequence: [Int]) -> [[Int]] {
        if sequence.count <= 2 {
            return []
        }

        let lastIndex = sequence.endIndex - 1

        struct Result {
            let open: [[Int]]
            let closed: [[Int]]
            init(_ open: [[Int]] = [], _ closed: [[Int]] = []) {
                self.open = open
                self.closed = closed
            }
        }

        func compute(_ prefixNumberIndex: Int,
                     _ suffixStartIndex: Int,
                     _ previousDistance: Int?) -> Result {

            if suffixStartIndex > lastIndex {
                return Result()
            }

            let prefixNumber = sequence[prefixNumberIndex]
            let suffixStartNumber = sequence[suffixStartIndex]
            let nextSuffixStartIndex = suffixStartIndex + 1
            let atSubsequenceStart = previousDistance == nil
            let minDistance = previousDistance ?? suffixStartNumber - prefixNumber - 1

            // Explore subsequent suffixes.
            let resultOfSuffixes = compute(suffixStartIndex, nextSuffixStartIndex, nil)

            // Include number at suffix start index.
            var resultBySelectingSuffixStartNumber: Result = Result()
            let distanceWhenSelectingSuffixStartNumber = suffixStartNumber - prefixNumber
            if distanceWhenSelectingSuffixStartNumber > minDistance {
                resultBySelectingSuffixStartNumber = compute(
                        suffixStartIndex, nextSuffixStartIndex,
                        distanceWhenSelectingSuffixStartNumber)
                var openSubsequencesBySelectingSuffixStartNumber =
                        resultBySelectingSuffixStartNumber.open
                let openSubsequencesExist = !openSubsequencesBySelectingSuffixStartNumber.isEmpty
                if (openSubsequencesExist && atSubsequenceStart) || !atSubsequenceStart {
                    var numbersToPrepend = [suffixStartNumber]
                    if atSubsequenceStart {
                        numbersToPrepend = [prefixNumber] + numbersToPrepend
                    }
                    if openSubsequencesBySelectingSuffixStartNumber.isEmpty {
                        openSubsequencesBySelectingSuffixStartNumber = [numbersToPrepend]
                    } else {
                        openSubsequencesBySelectingSuffixStartNumber =
                                openSubsequencesBySelectingSuffixStartNumber.map {
                                    numbersToPrepend + $0
                                }
                    }
                    if atSubsequenceStart {
                        let closedSubsequences = openSubsequencesBySelectingSuffixStartNumber +
                                resultBySelectingSuffixStartNumber.closed
                        resultBySelectingSuffixStartNumber = Result([], closedSubsequences)
                    } else {
                        resultBySelectingSuffixStartNumber =
                                Result(openSubsequencesBySelectingSuffixStartNumber,
                                        resultBySelectingSuffixStartNumber.closed)
                    }
                }
            }

            // Skip number at suffix start index and continue exploring the current suffix.
            let resultByExploringRemainderOfSuffix =
                    compute(prefixNumberIndex, nextSuffixStartIndex, previousDistance)

            // Select optimal result.
            let openSubsequences = resultBySelectingSuffixStartNumber.open
                    + resultByExploringRemainderOfSuffix.open
            let closedSubsequences: [[Int]] = (resultOfSuffixes.closed +
                    resultByExploringRemainderOfSuffix.closed +
                    resultBySelectingSuffixStartNumber.closed)
                    .withoutDuplicates()
            let shortestOpenCount = openSubsequences.isEmpty ? 0 :
                    openSubsequences.reduce(Int.max) { acc, curr in
                return acc > curr.count ? curr.count : acc
            }
            let longestClosedCount = closedSubsequences.reduce(0) { acc, curr in
                acc < curr.count ? curr.count : acc
            }
            let longestClosedSubsequences = closedSubsequences.filter {
                $0.count == longestClosedCount &&
                        $0.count >= shortestOpenCount
            }

            let result = Result(openSubsequences, longestClosedSubsequences)

            return result
        }

        let result = compute(0, 1, nil)
        return result.closed
    }

    /* Instead of grouping open and closed subsequences, each subsequence can be
     open or closed. */

    static func computeByBruteForceWithRecursion7(sequence: [Int]) -> [[Int]] {
        if sequence.count <= 2 {
            return []
        }

        let lastIndex = sequence.endIndex - 1

        struct Subsequence : Equatable {
            let subsequence: [Int]
            let open: Bool
            init(_ subsequence: [Int] = [], _ open: Bool = true) {
                self.subsequence = subsequence
                self.open = open
            }
        }

        func compute(_ prefixNumberIndex: Int,
                     _ suffixStartIndex: Int,
                     _ previousDistance: Int?) -> [Subsequence] {

            if suffixStartIndex > lastIndex {
                return [Subsequence]()
            }

            let prefixNumber = sequence[prefixNumberIndex]
            let suffixStartNumber = sequence[suffixStartIndex]
            let nextSuffixStartIndex = suffixStartIndex + 1
            let atSubsequenceStart = previousDistance == nil
            let minDistance = previousDistance ?? suffixStartNumber - prefixNumber - 1

            // Explore subsequent suffixes.
            let resultOfSuffixes = compute(suffixStartIndex, nextSuffixStartIndex, nil)

            // Include number at suffix start index.
            var resultBySelectingSuffixStartNumber = [Subsequence]()
            let distanceWhenSelectingSuffixStartNumber = suffixStartNumber - prefixNumber
            if distanceWhenSelectingSuffixStartNumber > minDistance {
                resultBySelectingSuffixStartNumber = compute(
                        suffixStartIndex, nextSuffixStartIndex,
                        distanceWhenSelectingSuffixStartNumber)
                var openSubsequencesBySelectingSuffixStartNumber =
                        resultBySelectingSuffixStartNumber.filter {
                            $0.open
                        }
                let closedSubsequencesBySelectingSuffixStartNumber =
                        resultBySelectingSuffixStartNumber.filter {
                            !$0.open
                        }
                let openSubsequencesExist = !openSubsequencesBySelectingSuffixStartNumber.isEmpty
                if (openSubsequencesExist && atSubsequenceStart) || !atSubsequenceStart {
                    var numbersToPrepend = [suffixStartNumber]
                    if atSubsequenceStart {
                        numbersToPrepend = [prefixNumber] + numbersToPrepend
                    }
                    if openSubsequencesBySelectingSuffixStartNumber.isEmpty {
                        openSubsequencesBySelectingSuffixStartNumber = [Subsequence(numbersToPrepend)]
                    } else {
                        openSubsequencesBySelectingSuffixStartNumber =
                                openSubsequencesBySelectingSuffixStartNumber.map {
                                    Subsequence(numbersToPrepend + $0.subsequence)
                                }
                    }
                    resultBySelectingSuffixStartNumber =
                            closedSubsequencesBySelectingSuffixStartNumber
                    if atSubsequenceStart {
                        resultBySelectingSuffixStartNumber +=
                                openSubsequencesBySelectingSuffixStartNumber.map {
                                    Subsequence($0.subsequence, false)
                                }
                    } else {
                        resultBySelectingSuffixStartNumber +=
                                openSubsequencesBySelectingSuffixStartNumber
                    }
                }
            }

            // Skip number at suffix start index and continue exploring the current suffix.
            let resultByExploringRemainderOfSuffix =
                    compute(prefixNumberIndex, nextSuffixStartIndex, previousDistance)

            // Select optimal result.
            let allSubsequences = resultOfSuffixes +
                    resultBySelectingSuffixStartNumber +
                    resultByExploringRemainderOfSuffix
            let distinctSubsequences = allSubsequences.reduce([Subsequence]()) {
                (acc: [Subsequence], curr: Subsequence) in
                return acc + (acc.contains(curr) ? [] : [curr])
            }
            let openSubsequences = distinctSubsequences.filter {
                $0.open
            }
            let shortestOpenCount = openSubsequences.isEmpty ? 0 :
                    openSubsequences.reduce(Int.max) { (acc: Int, curr: Subsequence) in
                acc > curr.subsequence.count ? curr.subsequence.count : acc
            }
            let longestClosedCount = distinctSubsequences.filter {
                !$0.open
            }.reduce(0) { (acc: Int, curr: Subsequence) in
                acc < curr.subsequence.count ? curr.subsequence.count : acc
            }

            let result = distinctSubsequences.filter {
                $0.open || ($0.subsequence.count == longestClosedCount &&
                        $0.subsequence.count >= shortestOpenCount)
            }

            return result
        }

        let result = compute(0, 1, nil).map {
            $0.subsequence
        }

        return result
    }

    /* Instead of providing minimum distance to compute(), we provide the min number
     that can qualify for extending the current subsequence with. */

    static func computeByBruteForceWithRecursion8(sequence: [Int]) -> [[Int]] {

        if sequence.count <= 2 {
            return []
        }

        let lastIndex = sequence.endIndex - 1

        func compute(_ firstNumberIndex: Int,
                     _ secondNumberIndex: Int,
                     _ min: Int) -> [[Int]] {

            // print("compute(\(firstNumberIndex), \(secondNumberIndex), \(min))")

            if secondNumberIndex > lastIndex {
                return [[]]
            }

            let firstNumber = sequence[firstNumberIndex]
            let secondNumber = sequence[secondNumberIndex]
            let nextNumberIndex = secondNumberIndex + 1

            // Keep current number
            var subsequencesBySelectingSecondNumber: [[Int]] = []

            // Include the second number
            if secondNumber > min {
                let nextMin = 2 * secondNumber - firstNumber
                subsequencesBySelectingSecondNumber = compute(
                        secondNumberIndex, nextNumberIndex, nextMin).map {
                    [secondNumber] + $0
                }
            }

            // Skip the second number
            let subsequencesBySelectingNextNumber = compute(
                    firstNumberIndex, nextNumberIndex, min)

            // Select optimal result
            let subsequences = (subsequencesBySelectingSecondNumber +
                    subsequencesBySelectingNextNumber).distinct()

            let result = selectLongest(subsequences)

            // print("compute(\(firstNumberIndex), \(secondNumberIndex), \(min)) = \(result)")

            return result
        }

        var subsequences: [[Int]] = (0...lastIndex - 2).flatMap {
            (firstNumberIndex: Int) -> [[Int]] in
            let firstNumber = sequence[firstNumberIndex]
            let result = (firstNumberIndex + 1...lastIndex - 1).flatMap {
                secondNumberIndex -> [[Int]] in
                let secondNumber = sequence[secondNumberIndex]
                let min = 2 * secondNumber - firstNumber
                let nextNumberIndex = secondNumberIndex + 1
                return compute(secondNumberIndex, nextNumberIndex, min)
                        .filter { $0.count > 0 }
                        .map {
                            [firstNumber, secondNumber] + $0
                        }
            }
            return result
        }

        subsequences = selectLongest(subsequences)

        return subsequences

    }

    /* We call compute() once per possible starting pair of numbers.  In compute(),
     we loop to iterate over the third number instead of using recursion.  We pass
     pairs of numbers to compute(), not triples.

     We don't pass a minDistance to compute().  We would lose some of the
     runtime gains of memoization if we applied it to this implementation, because
     results for pairs of numbers won't be differentiated by previous distance.
     We'd have to iterate over the result of a pair to determine the subset constrained
     by a given previous distance.  */

    static func computeByBruteForceWithRecursion9(sequence: [Int]) -> [[Int]] {
        if sequence.count <= 2 {
            return []
        }

        let lastIndex = sequence.endIndex - 1

        func compute(_ firstNumberIndex: Int,
                     _ secondNumberIndex: Int) -> [[Int]] {

            if secondNumberIndex >= lastIndex {
                return [[]]
            }

            let firstNumber = sequence[firstNumberIndex]
            let secondNumber = sequence[secondNumberIndex]

            var subsequences: [[Int]] = [[]]
            // We could make thirdNumberIndex a parameter of compute(), in order
            // to recurse instead of iterate here.
            for thirdNumberIndex in (secondNumberIndex + 1...lastIndex) {
                let thirdNumber = sequence[thirdNumberIndex]
                let isConvex = secondNumber < Int(round(Double(firstNumber + thirdNumber) / 2.0))
                if isConvex {
                    let subsequenceSuffixes = compute(secondNumberIndex, thirdNumberIndex)
                    subsequences += subsequenceSuffixes.map { subsequence in
                        [thirdNumber] + subsequence
                    }
                }
            }

            return subsequences
        }

        var subsequences: [[Int]] = []
        for firstNumberIndex in (0...lastIndex - 1) {
            let firstNumber = sequence[firstNumberIndex]
            for secondNumberIndex in (firstNumberIndex + 1...lastIndex) {
                let secondNumber = sequence[secondNumberIndex]
                subsequences += compute(firstNumberIndex, secondNumberIndex).map {
                    [firstNumber, secondNumber] + $0
                }
            }
        }

        subsequences = subsequences.filter { $0.count >= 3 }
        let longestSubsequences = selectLongest(subsequences)
        let uniqueLongestSubsequences = longestSubsequences.distinct()

        return uniqueLongestSubsequences
    }

    /* In compute(), We use recursion instead of loops to iterate over pairs of numbers.
     We don't pass a minDistance to compute().  We would lose some of the runtime gains
     of memoization if we applied it to this implementation, because results for pairs
     of numbers won't be differentiated by previous distance. We'd have to iterate
     over the result of a pair to determine the subset constrained by a given previous
     distance. */

    static func computeByBruteForceWithRecursion10(sequence: [Int]) -> [[Int]] {
        if sequence.count <= 2 {
            return []
        }

        let lastIndex = sequence.endIndex - 1

        func computeSubsequences(_ firstNumberIndex: Int,
                                 _ secondNumberIndex: Int) -> [[Int]] {

            if secondNumberIndex >= lastIndex {
                return [[]]
            }

            let firstNumber = sequence[firstNumberIndex]
            let secondNumber = sequence[secondNumberIndex]

            var subsequences: [[Int]] = [[]]
            // We could make thirdNumberIndex a parameter of compute(), in order
            // to recurse instead of iterate here.
            for thirdNumberIndex in (secondNumberIndex + 1...lastIndex) {
                let thirdNumber = sequence[thirdNumberIndex]
                let isConvex = secondNumber < Int(round(Double(firstNumber + thirdNumber) / 2.0))
                if isConvex {
                    let subsequenceSuffixes = computeSubsequences(
                            secondNumberIndex, thirdNumberIndex)
                    subsequences += subsequenceSuffixes.map { subsequence in
                        [thirdNumber] + subsequence
                    }
                }
            }

            return subsequences
        }

        /* Any pair of numbers could be the start of a convex subsequence, so we
         iterate over every pair. */
        func computePairs(_ firstNumberIndex: Int,
                          _ secondNumberIndex: Int) -> [[Int]] {
            if firstNumberIndex > lastIndex - 1 {
                return []
            }
            if secondNumberIndex > lastIndex {
                return computePairs(firstNumberIndex + 1, firstNumberIndex + 2)
            }
            var subsequences: [[Int]] = []
            let firstNumber = sequence[firstNumberIndex]
            let secondNumber = sequence[secondNumberIndex]
            // We see what subsequences can be generated if the current pair
            // is the start of the subsequences.
            subsequences += computeSubsequences(firstNumberIndex, secondNumberIndex)
                    .filter { $0.count >= 1 }
                    .map { subsequence in
                        [firstNumber, secondNumber] + subsequence
                    }
            subsequences += computePairs(firstNumberIndex, secondNumberIndex + 1)
            return subsequences
        }

        let subsequences = computePairs(0, 1)
        let longestSubsequences = selectLongest(subsequences)
        let uniqueLongestSubsequences = longestSubsequences.distinct()

        return uniqueLongestSubsequences
    }

    /* We iterate over every possible pair of numbers that could be the start of a
     convex subsequence.  Instead of using the pair directly to compute subsequences
     that begin with the pair, we compute subsequences that begin with the second
     number of the pair and are constrained by the distance between the first and
     second numbers of the pair.  That is, we pass the distance between the numbers,
     and the second number, to compute().

     When the sequence contains duplicates, this will result in more opportunities
     for caching results than when we pass the indices of the pair to compute().
     For example: [1,2,1,3,4,7].  Here we have the opportunity to reuse the results
     of the pair (1,4).

     Since we're not passing the second number to compute(), we use looping within
     compute() to iterate over all possible pairs that include the first number.

     The same caching optimization could be achieved by passing the first number
     itself to compute(), not the distance or the numbers index. */

    static func computeByBruteForceWithRecursion11(sequence: [Int]) -> [[Int]] {
        if sequence.count <= 2 {
            return []
        }

        let lastIndex = sequence.endIndex - 1

        func compute(_ minSecondNumber: Int,
                     _ firstNumberIndex: Int) -> [[Int]] {

            if firstNumberIndex >= lastIndex {
                return [[]]
            }

            let firstNumber = sequence[firstNumberIndex]

            var subsequences: [[Int]] = [[]]
            for secondNumberIndex in (firstNumberIndex + 1...lastIndex) {
                let secondNumber = sequence[secondNumberIndex]
                let isConvex = secondNumber > minSecondNumber
                if isConvex {
                    let nextMinDistance = 2 * secondNumber - firstNumber
                    let subsequenceSuffixes = compute(nextMinDistance, secondNumberIndex)
                           .distinct()
                    let currentSubsequences = subsequenceSuffixes.map { subsequence in
                        [secondNumber] + subsequence
                    }
                    subsequences += selectLongest(currentSubsequences)
                }
            }

            return subsequences
        }

        var subsequences: [[Int]] = []
        for firstNumberIndex in (0...lastIndex - 1) {
            let firstNumber = sequence[firstNumberIndex]
            for secondNumberIndex in (firstNumberIndex + 1...lastIndex) {
                let secondNumber = sequence[secondNumberIndex]
                let minNextNumber = 2 * secondNumber - firstNumber
                subsequences += compute(minNextNumber, secondNumberIndex)
                        .filter { $0.count >= 1 }
                        .map { [firstNumber, secondNumber] + $0 }
            }
        }

        let longestSubsequences = selectLongest(subsequences)
        let uniqueLongestSubsequences = longestSubsequences.distinct()

        return uniqueLongestSubsequences
    }

    /* We iterate over every possible pair of numbers that could be the start of a
     convex subsequence.  Instead of using the pair directly to compute subsequences
     that begin with the pair, we compute subsequences that begin with the second
     number of the pair and are constrained by the distance between the first and
     second numbers of the pair.  That is, we pass the distance between the numbers,
     and the second number, to compute().

     When the sequence contains duplicates, this will result in more opportunities
     for caching results than when we pass the indices of the pair to compute().
     For example: [1,2,1,3,4,7].  Here we have the opportunity to reuse the results
     of the pair (1,4).

     The same caching optimization could be achieved by passing the first number
     itself to compute(), not the distance or the numbers index.

     Instead of iterating over the starting pairs by looping, we use recursion. */

    static func computeByBruteForceWithRecursion12(sequence: [Int]) -> [[Int]] {
        if sequence.count <= 2 {
            return []
        }

        let lastIndex = sequence.endIndex - 1

        func computeSubsequences(_ minSecondNumber: Int,
                                 _ firstNumberIndex: Int) -> [[Int]] {

            if firstNumberIndex >= lastIndex {
                return [[]]
            }

            let firstNumber = sequence[firstNumberIndex]

            var subsequences: [[Int]] = [[]]
            for secondNumberIndex in (firstNumberIndex + 1...lastIndex) {
                let secondNumber = sequence[secondNumberIndex]
                let isConvex = secondNumber > minSecondNumber
                if isConvex {
                    let minNextNumber = 2 * secondNumber - firstNumber
                    let subsequenceSuffixes = computeSubsequences(
                            minNextNumber, secondNumberIndex).distinct()
                    let currentSubsequences = subsequenceSuffixes.map { subsequence in
                        [secondNumber] + subsequence
                    }
                    subsequences += selectLongest(currentSubsequences)
                }
            }

            return subsequences
        }

        /* Any pair of numbers could be the start of a convex subsequence, so we
         iterate over every pair. */
        func computePairs(_ firstNumberIndex: Int,
                          _ secondNumberIndex: Int) -> [[Int]] {
            if firstNumberIndex > lastIndex - 1 {
                return []
            }
            if secondNumberIndex > lastIndex {
                return computePairs(firstNumberIndex + 1, firstNumberIndex + 2)
            }
            let firstNumber = sequence[firstNumberIndex]
            let secondNumber = sequence[secondNumberIndex]
            let minNextNumber = 2 * secondNumber - firstNumber
            // We see what subsequences can be generated if the current pair
            // is the start of the subsequences.
            var subsequences = computeSubsequences(minNextNumber, secondNumberIndex)
                    .filter { $0.count >= 1 }
                    .map { subsequence in
                        [firstNumber, secondNumber] + subsequence
                    }
            subsequences += computePairs(firstNumberIndex, secondNumberIndex + 1)
            return subsequences
        }

        let subsequences = computePairs(0, 1)
        let longestSubsequences = selectLongest(subsequences)
        let uniqueLongestSubsequences = longestSubsequences.distinct()

        return uniqueLongestSubsequences
    }

    /* We accumulate an array of indices of 3 numbers, using its size to decide
     what to do next.  This allows us to accomplish both iteration over all suffixes
     and exploration of subsequences of each suffix.  */

    static func computeByBruteForceWithRecursion13(sequence: [Int]) -> [[Int]] {

        if sequence.count <= 2 {
            return []
        }

        let lastIndex = sequence.endIndex - 1

        func compute(_ triple: [Int]) -> [[Int]] {

            // print("compute(\(triple))")

            var result: [[Int]] = []

            if triple.count == 1 {
                guard triple[0] <= lastIndex - 2 else {
                    return [[]]
                }
                let firstNumberIndex = triple[0]
                let firstNumber = sequence[firstNumberIndex]
                let subsequencesByExcludingFirstNumber = compute([firstNumberIndex + 1])
                let secondNumberIndex = firstNumberIndex + 1
                let subsequencesByIncludingFirstNumber =
                        compute([firstNumberIndex, secondNumberIndex])
                                .filter {
                                    $0.count >= 2
                                }
                                .map {
                                    [firstNumber] + $0
                                }
                result = selectLongest(
                        subsequencesByExcludingFirstNumber +
                                subsequencesByIncludingFirstNumber)
            }

            else if triple.count == 2 {
                guard triple[1] <= lastIndex - 1 else {
                    return [[]]
                }
                let firstNumberIndex = triple[0]
                let secondNumberIndex = triple[1]
                let secondNumber = sequence[secondNumberIndex]
                let subsequencesByExcludingSecondNumber =
                        compute([firstNumberIndex, secondNumberIndex + 1])
                let thirdNumberIndex = secondNumberIndex + 1
                let subsequencesByIncludingSecondNumber =
                        compute([firstNumberIndex, secondNumberIndex, thirdNumberIndex])
                                .filter {
                                    $0.count >= 1
                                }
                                .map {
                                    [secondNumber] + $0
                                }
                result = selectLongest(
                        subsequencesByExcludingSecondNumber +
                                subsequencesByIncludingSecondNumber)
            }

            else if triple.count == 3 {
                guard triple[2] <= lastIndex else {
                    return [[]]
                }
                let firstNumberIndex = triple[0]
                let secondNumberIndex = triple[1]
                let thirdNumberIndex = triple[2]
                let nextNumberIndex = thirdNumberIndex + 1
                let firstNumber = sequence[firstNumberIndex]
                let secondNumber = sequence[secondNumberIndex]
                let thirdNumber = sequence[thirdNumberIndex]
                let subsequencesByExcludingThirdNumber =
                        compute([firstNumberIndex,
                                 secondNumberIndex,
                                 nextNumberIndex])
                let firstDistance = secondNumber - firstNumber
                let secondDistance = thirdNumber - secondNumber
                var subsequencesByIncludingThirdNumber: [[Int]] = []
                if secondDistance > firstDistance {
                    subsequencesByIncludingThirdNumber = compute(
                            [secondNumberIndex,
                             thirdNumberIndex,
                             nextNumberIndex])
                            .map {
                                [thirdNumber] + $0
                            }
                }
                result = selectLongest(
                        subsequencesByExcludingThirdNumber +
                                subsequencesByIncludingThirdNumber)
            }

            // print("compute(\(triple)) \(result)")

            return result
        }

        let result = compute([0]).filter {
            $0.count >= 3
        }

        return result

    }

    /* We append qualifying numbers to the current convex subsequence being
     constructed, and then supply the result to the recursive sub-call as a parameter.
     This contrasts with relying on the sub-call to do the appending for us.

     This approach would be most helpful when the recursive function is being used to
     start new subsequences, in addition to extending existing ones.  It would make
     it unnecessary to filter the result of recursive sub-calls to determine which
     subsequences we should prepend a qualifying number to.  */

    static func computeByBruteForceWithRecursion14(sequence: [Int]) -> [[Int]] {
        if sequence.count <= 2 {
            return []
        }

        let lastIndex = sequence.endIndex - 1

        func compute(_ minDistance: Int, _ firstNumberIndex: Int,
                     _ secondNumberIndex: Int, _ subsequence: [Int]) -> [[Int]] {

            if firstNumberIndex > lastIndex - 1 || secondNumberIndex > lastIndex {
                return subsequence.count >= 3 ? [subsequence] : []
            }

            var subsequences: [[Int]]

            // Skip second number.
            subsequences = compute(minDistance, firstNumberIndex,
                    secondNumberIndex + 1, subsequence)

            // Include second number, if possible.
            let firstNumber = sequence[firstNumberIndex]
            let secondNumber = sequence[secondNumberIndex]
            if secondNumber > minDistance {
                let nextFirstNumberIndex = secondNumberIndex
                let nextSecondNumberIndex = secondNumberIndex + 1
                let nextMinDistance = 2 * secondNumber - firstNumber
                let nextSubsequence = subsequence + [secondNumber]
                subsequences += compute(nextMinDistance, nextFirstNumberIndex,
                        nextSecondNumberIndex, nextSubsequence)
            }

            subsequences = subsequences.distinct()
            subsequences = selectLongest(subsequences)

            return subsequences
        }

        func computeInitialParametersByLooping(_ sequence: [Int]) -> [(Int, Int)] {
            var pairs = [(Int, Int)]()
            for firstNumberIndex in (0...lastIndex - 1) {
                for secondNumberIndex in (firstNumberIndex + 1...lastIndex) {
                    pairs.append((firstNumberIndex, secondNumberIndex))
                }
            }
            return pairs
        }

        let subsequences: [[Int]] = computeInitialParametersByLooping(sequence).flatMap {
            (firstNumberIndex, secondNumberIndex) -> [[Int]] in
            let firstNumber = sequence[firstNumberIndex]
            let secondNumber = sequence[secondNumberIndex]
            let thirdNumberIndex = secondNumberIndex + 1
            let minDistance = 2 * secondNumber - firstNumber
            return compute(minDistance, secondNumberIndex, thirdNumberIndex,
                    [firstNumber, secondNumber])
        }

        return subsequences
    }

    /* We cache results using a key consisting of the indices provided to compute().
     This isn't the most efficient approach because we get two different keys when
     two triples of numbers are identical except for the index of the first
     number being different.  For example, two keys representing numbers (7,8,9),
     but having indices (0,3,4), (2,3,4).  The distance between the first and
     second numbers is relevant for caching results, not the actual index of the
     first number. */

    static func computeWithRecursionAndMemoization1(sequence: [Int]) -> [[Int]] {

        if sequence.count <= 2 {
            return []
        }

        let lastIndex = sequence.endIndex - 1

        struct Key : Hashable {
            let triple: [Int]
            var hashValue: Int {
                get {
                    let result = triple.enumerated().reduce(0) { acc, curr in
                        let (index, value) = curr
                        return (((acc * 31) + index) * 31) + value
                    }
                    return result
                }
            }
        }

        var cache = [Key : [[Int]]]()

        func compute(_ triple: [Int]) -> [[Int]] {

            // print("compute(\(triple))")

            let key = Key(triple: triple)

            if let result = cache[key] {
                // print("cache hit compute(\(triple)) \(result)")
                return result
            }

            var result: [[Int]] = []

            if triple.count == 1 {
                guard triple[0] <= lastIndex - 2 else {
                    return [[]]
                }
                let firstNumberIndex = triple[0]
                let firstNumber = sequence[firstNumberIndex]
                let subsequencesByExcludingFirstNumber = compute([firstNumberIndex + 1])
                let secondNumberIndex = firstNumberIndex + 1
                let subsequencesByIncludingFirstNumber =
                        compute([firstNumberIndex, secondNumberIndex])
                                .filter {
                                    $0.count >= 2
                                }
                                .map {
                                    [firstNumber] + $0
                                }
                result = selectLongest(
                        subsequencesByExcludingFirstNumber +
                                subsequencesByIncludingFirstNumber)
                        .distinct()
            }

            else if triple.count == 2 {
                guard triple[1] <= lastIndex - 1 else {
                    return [[]]
                }
                let firstNumberIndex = triple[0]
                let secondNumberIndex = triple[1]
                let secondNumber = sequence[secondNumberIndex]
                let subsequencesByExcludingSecondNumber =
                        compute([firstNumberIndex, secondNumberIndex + 1])
                let thirdNumberIndex = secondNumberIndex + 1
                let subsequencesByIncludingSecondNumber =
                        compute([firstNumberIndex, secondNumberIndex, thirdNumberIndex])
                                .filter {
                                    $0.count >= 1
                                }
                                .map {
                                    [secondNumber] + $0
                                }
                result = selectLongest(
                        subsequencesByExcludingSecondNumber +
                                subsequencesByIncludingSecondNumber)
                        .distinct()
            }

            else if triple.count == 3 {
                guard triple[2] <= lastIndex else {
                    return [[]]
                }
                let firstNumberIndex = triple[0]
                let secondNumberIndex = triple[1]
                let thirdNumberIndex = triple[2]
                let nextNumberIndex = thirdNumberIndex + 1
                let firstNumber = sequence[firstNumberIndex]
                let secondNumber = sequence[secondNumberIndex]
                let thirdNumber = sequence[thirdNumberIndex]
                let subsequencesByExcludingThirdNumber =
                        compute([firstNumberIndex,
                                 secondNumberIndex,
                                 nextNumberIndex])
                let firstDistance = secondNumber - firstNumber
                let secondDistance = thirdNumber - secondNumber
                var subsequencesByIncludingThirdNumber: [[Int]] = []
                if secondDistance > firstDistance {
                    subsequencesByIncludingThirdNumber = compute(
                            [secondNumberIndex,
                             thirdNumberIndex,
                             nextNumberIndex])
                            .map {
                                [thirdNumber] + $0
                            }
                }
                result = selectLongest(
                        subsequencesByExcludingThirdNumber +
                                subsequencesByIncludingThirdNumber)
                        .distinct()
            }

            // print("compute(\(triple)) \(result)")

            cache[key] = result

            return result
        }

        let result = compute([0]).filter {
            $0.count >= 3
        }

        return result

    }

    struct DistanceAndPairKey : Hashable, Equatable {
        let distance: Int
        let pair: (Int, Int)
        init (_ distance: Int, _ pair: (Int, Int)) {
            self.distance = distance
            self.pair = pair
        }
        var hashValue: Int {
            get {
                return ((distance * 31) + pair.0 * 31) + pair.1 * 31
            }
        }
        static func ==(lhs: EPLongestConvexSubsequence.DistanceAndPairKey,
                       rhs: DistanceAndPairKey) -> Bool {
            return lhs.distance == rhs.distance &&
                    lhs.pair == rhs.pair
        }
    }

    /* We squeeze additional optimization from memoization by using a key that
     consists of distance between first and second numbers, and the indices of the
     second and third numbers.  We don't cache results when there are fewer than
     three indices provided to compute(), because we don't expect those combinations
     of indices to be called more than once.  So no advantage to caching their results.
     This also reduces the size of our cache. */

    static func computeWithRecursionAndMemoization2(sequence: [Int]) -> [[Int]] {

        if sequence.count <= 2 {
            return []
        }

        let lastIndex = sequence.endIndex - 1
        var cache = [DistanceAndPairKey : [[Int]]]()

        func compute(_ triple: [Int]) -> [[Int]] {

            // print("compute(\(triple))")

            var result: [[Int]] = []

            if triple.count == 1 {
                guard triple[0] <= lastIndex - 2 else {
                    return [[]]
                }
                let firstNumberIndex = triple[0]
                let firstNumber = sequence[firstNumberIndex]
                let subsequencesByExcludingFirstNumber = compute([firstNumberIndex + 1])
                let secondNumberIndex = firstNumberIndex + 1
                let subsequencesByIncludingFirstNumber =
                        compute([firstNumberIndex, secondNumberIndex])
                                .filter {
                                    $0.count >= 2
                                }
                                .map {
                                    [firstNumber] + $0
                                }
                result = selectLongest(
                        subsequencesByExcludingFirstNumber +
                                subsequencesByIncludingFirstNumber)
                        .distinct()
            }

            else if triple.count == 2 {
                guard triple[1] <= lastIndex - 1 else {
                    return [[]]
                }
                let firstNumberIndex = triple[0]
                let secondNumberIndex = triple[1]
                let secondNumber = sequence[secondNumberIndex]
                let subsequencesByExcludingSecondNumber =
                        compute([firstNumberIndex, secondNumberIndex + 1])
                let thirdNumberIndex = secondNumberIndex + 1
                let subsequencesByIncludingSecondNumber =
                        compute([firstNumberIndex, secondNumberIndex, thirdNumberIndex])
                                .filter {
                                    $0.count >= 1
                                }
                                .map {
                                    [secondNumber] + $0
                                }
                result = selectLongest(
                        subsequencesByExcludingSecondNumber +
                                subsequencesByIncludingSecondNumber)
                        .distinct()
            }

            else if triple.count == 3 {
                guard triple[2] <= lastIndex else {
                    return [[]]
                }
                let firstNumberIndex = triple[0]
                let secondNumberIndex = triple[1]
                let thirdNumberIndex = triple[2]
                let firstNumber = sequence[firstNumberIndex]
                let secondNumber = sequence[secondNumberIndex]
                let distance = secondNumber - firstNumber
                let key = DistanceAndPairKey(distance, (secondNumberIndex,
                        thirdNumberIndex))
                if let cachedResult = cache[key] {
                    result = cachedResult
                    // print("cache hit compute(\(triple)) \(result)")
                } else {
                    let nextNumberIndex = thirdNumberIndex + 1
                    let thirdNumber = sequence[thirdNumberIndex]
                    let subsequencesByExcludingThirdNumber =
                            compute([firstNumberIndex,
                                     secondNumberIndex,
                                     nextNumberIndex])
                    let firstDistance = secondNumber - firstNumber
                    let secondDistance = thirdNumber - secondNumber
                    var subsequencesByIncludingThirdNumber: [[Int]] = []
                    if secondDistance > firstDistance {
                        subsequencesByIncludingThirdNumber = compute(
                                [secondNumberIndex,
                                 thirdNumberIndex,
                                 nextNumberIndex])
                                .map {
                                    [thirdNumber] + $0
                                }
                    }
                    result = selectLongest(
                            subsequencesByExcludingThirdNumber +
                                    subsequencesByIncludingThirdNumber)
                            .distinct()

                    cache[key] = result
                }
            }

            // print("compute(\(triple)) \(result)")

            return result
        }

        let result = compute([0]).filter {
            $0.count >= 3
        }

        return result

    }

    /* We take the middle of the convex triple as our stable/reference point and
     generate triples of numbers by combining the middle with each of the numbers
     to its left and right. The left pairing provides the minimum distance.  When
     we encounter a right pairing that has a distance exceeding this minimum, we
     extend and store its subsequences in the cache.

     We do this for every possible subsequence start position in the sequence and
     select the longest of these as our solution. */

    static func computeBottomUpWithMemoization1(sequence: [Int]) -> [[Int]] {

        if sequence.count <= 2 {
            return []
        }

        let lastIndex = sequence.endIndex - 1
        var cache: [Int : [Int : [[Int]]]] = [:]

        /* Initialize cache by computing subsequences where last position in sequence
         is middle number of a convex triple. */

        let lastNumber = sequence[lastIndex]
        cache[lastIndex] = [:]
        for firstNumberIndex in 0..<lastIndex {
            let firstNumber = sequence[firstNumberIndex]
            let distance = lastNumber - firstNumber
            cache[lastIndex]![distance] = [[lastNumber]]
        }

        // Compute subsequences at each possible middle position of a convex triple.

        for secondNumberIndex in stride(from: lastIndex - 1, through: 1, by: -1) {
            cache[secondNumberIndex] = [:]
            let secondNumber = sequence[secondNumberIndex]
            for firstNumberIndex in 0..<secondNumberIndex {
                let firstNumber = sequence[firstNumberIndex]
                let minDistance = secondNumber - firstNumber
                /* Given any distance between the first and second numbers, the second
                 number will always be part of a possible subsequence, even if we can't
                 can't extend the subsequence beyond the second number. */
                var subsequences: [[Int]] = [[secondNumber]]
                for thirdNumberIndex in secondNumberIndex + 1...lastIndex {
                    let thirdNumber = sequence[thirdNumberIndex]
                    let distance = thirdNumber - secondNumber
                    if distance > minDistance {
                        subsequences += cache[thirdNumberIndex]![distance]!.map {
                            [secondNumber] + $0
                        }
                    }
                }
                cache[secondNumberIndex]?[minDistance] =
                        selectLongest(subsequences.distinct())
            }
        }

        // Compute longest subsequences of each suffix.

        var longestSubsequencesOfSuffixes: [[Int]] = []
        for firstNumberIndex in 0...(lastIndex - 2) {
            let firstNumber = sequence[firstNumberIndex]
            var subsequences: [[Int]] = []
            for secondNumberIndex in (firstNumberIndex + 1)...lastIndex {
                let secondNumber = sequence[secondNumberIndex]
                let distance = secondNumber - firstNumber
                subsequences += cache[secondNumberIndex]![distance]!
            }
            longestSubsequencesOfSuffixes += selectLongest(subsequences).map {
                [firstNumber] + $0
            }.filter { $0.count >= 3 }
        }

        // Select the longest subsequences of all suffixes.

        return selectLongest(longestSubsequencesOfSuffixes)
    }

    /* IDEA: We pre-compute a matrix of distances between every pair of numbers.
     We use vertical axis to represent first number and horizontal for second number.
     We take each position in the vertical axis as the starting point of a suffix.
     We move through subsequences by treating a vertical axis position as a horizontal
     axis one, and vice-versa. */

    /* IDEA: We move from right to left, marking the position of the third number in a convex
     triple.  For each third number, we form a triple with every combination of first
     and second number.  When the triple is convex, we make a new triple from its second
     and third numbers.  We iterate over numbers to the right of the current position to
     complete the new triple, each position providing a possible third number. */

    // TODO

    /* We cache by indices, not distance.  For a given second number, this allows
     us to skip it and query the next second number to get the subsequences resulting
     from selecting any subsequent second number. */

    static func computeBottomUpWithMemoization2(sequence: [Int]) -> [[Int]] {

        if sequence.count <= 2 {
            return []
        }

        struct Key: Hashable {
            let firstIndex: Int
            let secondIndex: Int
            init (_ firstIndex: Int, _ secondIndex: Int) {
                self.firstIndex = firstIndex
                self.secondIndex = secondIndex
            }
            var hashValue: Int {
                get {
                    return (firstIndex * 31) + secondIndex
                }
            }
        }

        let lastIndex = sequence.endIndex - 1
        var cache: [Int : [Int : [[Int]]]] = [:]

        /* Initialize cache by computing subsequences where the last number in the
         sequence is the second number. */

        let lastNumber = sequence[lastIndex]
        cache[lastIndex] = [:]
        for firstNumberIndex in 0..<lastIndex {
            let firstNumber = sequence[firstNumberIndex]
            cache[lastIndex]![firstNumberIndex] = [[lastNumber]]
        }

        // Compute subsequences at each possible second position of a convex triple.

        for secondNumberIndex in stride(from: lastIndex - 1, through: 1, by: -1) {
            cache[secondNumberIndex] = [:]
            let secondNumber = sequence[secondNumberIndex]
            for firstNumberIndex in 0..<secondNumberIndex {
                let firstNumber = sequence[firstNumberIndex]
                let minDistance = secondNumber - firstNumber
                /* Given any distance between the first and second numbers, the second
                 number will always be part of a possible subsequence, even if we can't
                 can't extend the subsequence beyond the second number. */
                var subsequences: [[Int]] = [[secondNumber]]
                for thirdNumberIndex in secondNumberIndex + 1...lastIndex {
                    let thirdNumber = sequence[thirdNumberIndex]
                    let distance = thirdNumber - secondNumber
                    if distance > minDistance {
                        subsequences += cache[thirdNumberIndex]![distance]!.map {
                            [secondNumber] + $0
                        }
                    }
                }
                cache[secondNumberIndex]?[minDistance] =
                        selectLongest(subsequences.distinct())
            }
        }

        // Compute longest subsequences of each suffix.

        var longestSubsequencesOfSuffixes: [[Int]] = []
        for firstNumberIndex in 0...(lastIndex - 2) {
            let firstNumber = sequence[firstNumberIndex]
            var subsequences: [[Int]] = []
            for secondNumberIndex in (firstNumberIndex + 1)...lastIndex {
                let secondNumber = sequence[secondNumberIndex]
                let distance = secondNumber - firstNumber
                subsequences += cache[secondNumberIndex]![distance]!
            }
            longestSubsequencesOfSuffixes += selectLongest(subsequences).map {
                [firstNumber] + $0
            }.filter { $0.count >= 3 }
        }

        // Select the longest subsequences of all suffixes.

        return selectLongest(longestSubsequencesOfSuffixes)
    }
}
