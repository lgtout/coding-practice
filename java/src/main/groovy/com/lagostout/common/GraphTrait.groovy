package com.lagostout.common

trait GraphTrait {

    int randomVertex(int vertexCount) {
        randomDataGenerator.nextInt(0, vertexCount - 1)
    }

}
