//  Copyright © 2017 lagostout. All rights reserved.

import Foundation
import Quick
import Nimble
import CodingPractice

class FindDisappearedNumbersSpec : QuickSpec {
    override func spec() {
        describe("FindDisappearedNumbersSpec") {
            let cases = [
                    ([1,1,2], [3]),
                    ([2,2,1], [3]),
                    ([3,1,1], [2]),
                    ([4,3,2,7,8,2,3,1], [5,6]),
            ]
            for (numbers, expected) in cases {
                it("finds missing numbers") {
                    let missingNumbers = FindDisappearedNumbers()
                            .findDisappearedNumbers(numbers)
                    expect(missingNumbers).to(equal(expected))
                }
            }
        }
    }
}
