//  Copyright © 2017 lagostout. All rights reserved.

import Foundation

/// [Problem description](https://leetcode.com/problems/construct-the-rectangle)

class ConstructTheRectangle {
    
    /**
        Fastest minimal solution.  Beats 88% of submissions.
        
        Based on https://discuss.leetcode.com/topic/83114/4-lines-javascript-solution
     */

    func constructRectangle(_ area: Int) -> [Int] {
        var width = Int(sqrt(Double(area)))
        while (Int(Double(area)/Double(width)) * width != area) {
            width -= 1   
        }
        return [area/width, width]
    }
    
    /// Fastest solution.  Beats 90% of submissions.
    
    func __constructRectangle(_ area: Int) -> [Int] {
        var length = area
        var width = 1
        var result = [length, width]
        while (true) {
            width += 1
            length = area / width
            if (width > length) {
                break
            }
            if (width * length == area) {
               result = [length, width]
            }
        }
        return result
    }
    
    /**
        Faster solution.  Beats 50% of submissions.
     
        Based on this [solution](https://discuss.leetcode.com/topic/83114/4-lines-javascript-solution).
     */
    
    func ___constructRectangle(_ area: Int) -> [Int] {
        var width = Int(sqrt(Double(area)))
        while (area % width != 0) {
            width -= 1   
        }
        return [area/width, width]
    }
    
    /**
         Slower solution. Beats 30% of submissions.
     
         Compute the square root of the area. If it's a
         whole number, we're done. Otherwise, step down
         by 1, from the rounded square root to 0, to
         generate a candidate factor. If at any point
         (area % candidate) == 0, the candidate is a 
         factor. and the other factor is (area / candidate).
     */
    
    func _constructRectangle(_ area: Int) -> [Int] {
        let squareSideLength = sqrt(Double(area))
        var roundedSideLength = Int(squareSideLength)
        var sides:[Int] = []
        if roundedSideLength * roundedSideLength == area {
            sides = [roundedSideLength, roundedSideLength]
        } else {
            roundedSideLength += 1
            while roundedSideLength <= area {
                if area % roundedSideLength == 0 {
                    sides = [roundedSideLength, area / roundedSideLength]
                    break
                }
                roundedSideLength += 1
            }
        }
        return sides
    }

}
