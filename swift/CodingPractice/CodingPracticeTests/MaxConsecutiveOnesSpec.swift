//  Copyright © 2017 lagostout. All rights reserved.

import Foundation
import Quick
import Nimble

class MaxConsecutiveOnesSpec : QuickSpec {
    override func spec() {
        it("finds max consecutive ones") {
            let maxConsecutiveOnes = MaxConsecutiveOnes()
            let cases = [
                [[1,1,0,1,1,1], 3],
                [[0,1,1,0,0,1,1,1,0], 3]
            ]
            for aCase in cases {
                let max = maxConsecutiveOnes
                    .findMaxConsecutiveOnes(aCase[0] as! [Int])
                expect(max).to(equal(aCase[1] as? Int))
            }
        }
    }
}
