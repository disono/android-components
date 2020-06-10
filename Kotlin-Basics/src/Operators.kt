package com.webmons.basic.kotlin

import java.util.*
import kotlin.math.pow

class Operators {
    fun run() {
        // use primitive 'int' as an object
        println(1.toLong())

        // or, put int ia box
        val boxed: Number = 1
        println(boxed.toLong())

        val i: Int = 1
        val b: Byte = i.toByte()
        val f: Float = 10f
        val s: Short = 10
        var ch: Char = 'C'
        var str: String = "String"
        var bol: Boolean = true

        println(nullable())
        conditions(12)
        arraysLoops()
    }

    private fun nullable(): Int {
        val address: String? = null
        val amount: Double? = 10.0
        val list: List<String?>? = listOf(null, null, "A", "B")

        address?.let {
            println("Not Null")
        }

        val fishFoodTreats: Int? = 5

        // use ?: to return zero or default value if variable is null
        // use !! to force for null
        println("List Size: ${list?.size ?: 0}")

        var fish = 2
        var produceFirst = 71
        var produceSecond = 233
        var diedFish = 13
        var eel = 1
        var maxFish = 30

        var totalFish = ((fish + produceFirst + produceSecond + eel) - diedFish)
        var totalPerAquarium = totalFish / maxFish

        println("Total Fish: $totalFish")
        println("Total Aquarium Required: $totalPerAquarium")

        var nullTest: Int? = 0
        println(nullTest?.inc() ?: 0)

        // variable
        var rainbowColor: String = "Blue"
        rainbowColor = "Yellow"

        // constant
        val color: String = "Black"

        println("Sum: ${maxFish + eel}")

        return fishFoodTreats?.dec() ?: 0
    }

    private fun conditions(value: Int) {
        val people: Int = value
        if (people > 1) {
            println("More People")
        } else {
            println("No People")
        }

        when (value) {
            0 -> println("No People")
            12 -> {
                println("12 People")
            }
            else -> println("Undefined")
        }

        var trout = "Trout"
        var haddock = "Haddock"
        var snapper = "Snapper"
        println("Do not eat any of this freaking fish $trout, $haddock, $snapper.")

        var fishName: String? = "Fish Name"
        when (fishName?.length ?: 0) {
            0 -> println("Oooops no name.")
            in 1..50 -> println("Perfect")
            else -> println("Too long")
        }
    }

    private fun arraysLoops() {
        var list = listOf("People", "Fish")
        var mix = arrayOf("Fish", 3, false, list)
        println(Arrays.toString(mix))

        // it = index
        var arrays = Array(5) { it + 2 }
        println(arrays.asList())

        // loops
        for (element in mix) {
            println(element)
        }

        // loop with index
        for ((i, element) in mix.withIndex()) {
            println("Index $i Element $element")
        }

        // ranges
        for (i in 'a'..'e') {
            println(i)
        }

        for (i in 4..55) {
            println(i)
        }

        for (i in 5 downTo 1) println(i)
        for (i in 3..6 step 2) println(i)
        var listOfNums = listOf(1, 2, 100, 55)
        for (i in listOfNums) println(i)

        val array = Array(7) { 1000.0.pow(it) }
        val sizes = arrayOf("byte", "kilobyte", "megabyte", "gigabyte", "terabyte", "petabyte", "exabyte")
        for ((i, value) in array.withIndex()) {
            println("1 ${sizes[i]} = ${value.toLong()} bytes")
        }

        val numbers = listOf(11, 12, 13, 14, 15)
        val numberNames = arrayOf("Ele", "Twe", "Thr", "For", "Fif")
        for ((i, value) in numbers.withIndex()) println("Num: $value Name: ${numberNames[i]}")

        var muList: MutableList<Int> = mutableListOf()
        for (i in 0..100 step 7) muList.add(i)
        println(muList)

        var immuList: List<Int> = mutableListOf(1, 2, 3)
        println(immuList)

        for (i in 0..100 step 7) println("$i - ")

        var otherClass = OtherClass()
        otherClass.name = "Updated Name"
        val lAny = listOf(1, true, otherClass, "Bla bla")
        typeChecking(lAny)

        println(Calendar.getInstance().get(Calendar.DAY_OF_WEEK))
    }

    private fun typeChecking(list: List<Any>) {
        for (value in list) {
            when (value) {
                is String -> println("This is string: $value")
                is OtherClass -> println("This is OtherClass ${value.name}")
                else -> println("Unknown type.")
            }
        }
    }
}