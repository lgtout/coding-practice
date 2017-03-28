package com.lagostout.competitiveprogramming3.util

class Util {

    static File getFile(String file) {
        new File(Util.getClassLoader().getResource(file).getFile())
    }

    static InputStream createStream(String file) {
        getFile(file).newInputStream()
    }

}
