#!/usr/bin/env kscript
//DEPS com.offbytwo:docopt:0.6.0.20150202,log4j:log4j:1.2.14


println("Hello from Kotlin!")
for (arg in args) {
    println("arg: $arg")
}
