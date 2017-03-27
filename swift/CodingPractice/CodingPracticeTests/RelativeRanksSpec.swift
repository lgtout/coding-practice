//  Copyright © 2017 lagostout. All rights reserved.

import Foundation
import Quick
import Nimble

class RelativeRanksSpec : QuickSpec {
    
    override func spec() {
        let relativeRanks = RelativeRanks()
        describe("RelativeRanks") {
            describe("findRelativeRanks") {
                let cases:[(scores:[Int], expected:[String])] = [
                    ([5,4,3,2,1],
                     ["Gold Medal", "Silver Medal", "Bronze Medal", "4", "5"]),
                    ([10,3,8,9,4],
                     ["Gold Medal","5","Bronze Medal","Silver Medal","4"])]
                for (scores, expected)in cases {
                    it("ranks scores")
                    { [scores = scores] in
                        expect(relativeRanks.findRelativeRanks(scores))
                            .to(equal(expected))
                    }
                }
            }
        }
    }

}
