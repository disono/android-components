package com.webmons.basic.kotlin

class Classes {
    var w: Int = 2
    var h: Int = 2
    var l: Int = 100

    var volume: Int
        get() = l
        set(value) { l = value }

    internal var movies = mutableListOf<Any>()
}