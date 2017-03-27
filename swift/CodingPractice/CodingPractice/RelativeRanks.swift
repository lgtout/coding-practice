//  Copyright © 2017 lagostout. All rights reserved.

import Foundation

/// [Problem description](https://leetcode.com/problems/relative-ranks/)

class RelativeRanks {
    
    func findRelativeRanks(_ nums: [Int]) -> [String] {
        var result = [String](repeating:"", count:nums.count)
        let sortedScores = nums.sorted().reversed()
        var scoresToRank = Dictionary<Int, Int>()
        for (rank, score) in sortedScores.enumerated() {
            scoresToRank[score] = rank
        }
        for (index, score) in nums.enumerated() {
            if var rank = scoresToRank[score] {
                rank += 1
                var scoreOrMedal:String = String(describing:rank)
                if (rank <= 3) {
                    switch rank {
                    case 1:
                        scoreOrMedal = "Gold Medal"
                    case 2:
                        scoreOrMedal = "Silver Medal"
                    case 3:
                        scoreOrMedal = "Bronze Medal"
                    default:
                        break
                    }
                }
                result[index] = scoreOrMedal
            }
        }
        return result
    }
    
}

// The online judge can't run this solution because of use of arc4random

class RelativeRanks_QuickSort {
    
    func findRelativeRanks(_ nums: inout [Int]) -> [String] {
        randomShuffle(&nums)
        sort(&nums, 0, nums.count - 1)
        let result = toMedalsOrScores(nums)
        return result
    }
    
    func toMedalsOrScores(_ scores: [Int]) -> [String] {
        var result: [String] = []
        for (index, score) in scores.reversed().enumerated() {
            var scoreOrMedal:String = String(describing:score)
            if (index <= 2) {
                switch index {
                case 0:
                    scoreOrMedal = "Gold medal"
                case 1:
                    scoreOrMedal = "Silver medal"
                case 2:
                    scoreOrMedal = "Bronze medal"
                default:
                    break
                }
            }
            result.append(scoreOrMedal)
        }
        return result
    }
    
    func partition(_ nums: inout [Int], _ lo: Int, _ hi: Int) -> Int {
        if (nums.count == 1) {
            return 0   
        }
        var i = lo + 1
        var j = hi
        let pivot = nums[lo]
        while (true) {
            while (nums[i] < pivot) {
                if (i == hi) {
                    break
                }
                i += 1
            }
            while (nums[j] > pivot) {
                if (j == lo) {
                    break
                }
                j -= 1
            }
            if (i < j) {
                swap(&nums[i], &nums[j])
            } else {
                break
            }
        }
        if (j != lo) {
            swap(&nums[j], &nums[lo])
        }
        return j
    }
    
    func sort(_ nums: inout [Int], _ lo: Int, _ hi: Int) {
        if (lo < hi) {
            let p = partition(&nums, lo, hi)
            sort(&nums, lo, p - 1)
            sort(&nums, p + 1, hi)
        }
    }
    
    func randomShuffle(_ nums: inout [Int]) {
        for index in stride(from:nums.count - 1, to:0, by:-1) {
            let randomIndex = Int(arc4random_uniform(UInt32(index + 1)))
            if randomIndex != index {
                swap(&nums[index], &nums[randomIndex])
            }
        }
    }
    
}
