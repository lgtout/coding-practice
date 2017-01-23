//  Copyright © 2017 lagostout. All rights reserved.

import Foundation
import Quick
import Nimble

class IslandPerimeterSpec : QuickSpec {
    override func spec() {
        it("computes the perimeter") {
            var aCase:[Any] = [[[0,1,0,0],
                        [1,1,1,0],
                        [0,1,0,0],
                        [1,1,0,0]], 16]
            let perimeter = IslandPerimeter().islandPerimeter(aCase[0] as! [[Int]])
            expect(perimeter).to(equal(aCase[1] as? Int))
        }
    }
}
