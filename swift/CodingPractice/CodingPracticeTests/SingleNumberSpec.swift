//  Copyright © 2017 lagostout. All rights reserved.

import Foundation
import Quick
import Nimble

class CodingPracticeSpec : QuickSpec {
    override func spec() {
        describe("SingleNumber") {
            let cases = [
                ([1,2,1], 2),
                ([2,2,3], 3),
                ([2,2,4,5,6,4,5], 6)
            ]
            for (numbers, expected) in cases {
                it("finds the single number non-repeated number") {
                    let singleNumber = SingleNumber().singleNumber(numbers)
                    expect(singleNumber).to(equal(expected))
                }
            }
        }
    }
}
