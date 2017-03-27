
import Foundation

/// [Problem description](https://leetcode.com/problems/find-all-mutableNumsbers-disappeared-in-an-array)

class FindDisappearedNumbers {
    func findDisappearedNumbers(_ nums: [Int]) -> [Int] {
        if nums.count == 0 {
            return []
        }
        var nums = Array<Int>(nums)
        for num in nums {
            let index = num - 1
            if (nums[index] > 0) {
                nums[index] = -nums[index]
            }
        }
        var missingNums:[Int] = []
        for index in (0...nums.count-1) {
            let num = nums[index]
            if (num > 0) {
                missingNums += [index + 1]
            }
        }
        return missingNums
    }
}
