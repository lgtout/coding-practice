//  Copyright © 2017 lagostout. All rights reserved.

import Foundation
import Quick
import Nimble

class ConstructTheRectangleSpec : QuickSpec {
    override func spec() {
        describe("ConstructTheRectangle") {
            let cases:[(area:Int, expected:[Int])] = [
                (1, [1,1]),
                (2, [2,1]),
                (4, [2,2]),
                (5, [5,1]),
                (6, [3,2]),
                (8, [4,2]),
                (12, [4,3]),
            ]
            let constructTheRectangle = ConstructTheRectangle()
            for (area, expected) in cases {
                it("computes \(expected) are the sides with " +
                        "minimal difference for rectangle with area \(area)") {
                    expect(constructTheRectangle.constructRectangle(area))
                            .to(equal(expected))
                }
            }
        }
    }
}
