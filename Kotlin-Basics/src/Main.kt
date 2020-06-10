package com.webmons.basic.kotlin

object Main {
    @JvmStatic
    fun main(args: Array<String>) {
        Operators().run()
        Methods().run()

        val classes = Classes()
        classes.h = 100
        classes.volume = 1333
        println(classes.volume)
        classes.movies.add(1)
    }
}