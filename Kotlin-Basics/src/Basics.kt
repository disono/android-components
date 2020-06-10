package com.webmons.basic.kotlin

class Basics {
    fun hello() {
        println("Hello, World")
    }

    // Declaring function
    fun sum(a: Int, b: Int): Int {
        return a + b
    }

    // Single expression function
    fun sumSingle(a: Int, b: Int) = a + b

    // Declaring variables
    fun declareVariables() {
        val name = "Archie" // Can't be changed
        var age = 18        // Can be changed
        age++

        // Variables with nullable types

    }
}