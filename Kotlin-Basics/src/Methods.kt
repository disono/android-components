package com.webmons.basic.kotlin

import java.util.*

class Methods {
    fun run() {
        mainArgs()
        feedTheFish()
        randomDay()
        // getFortune()
        defaultParams()

        val deco = listOf("rock", "stone")
        println("Filters: ${deco.filter{true}}")

        println(deco.filter { it.contains("ck") }.sortedBy { it.length })
        println(deco.filter { it.startsWith("r") }.filter { it.endsWith("k") })

        val lamb = {
            println("Lambda")
        }
        lamb()

        dirty = updateDirty(dirty, waterFilter)
        dirty = updateDirty(dirty, ::anotherMethod)
        dirty = updateDirty(dirty) { dirty ->
            dirty + 30
        }
        println(dirty)
    }

    private var dirty = 20

    private val waterFilter: (Int) -> Int = { dirty -> dirty / 2 }
    private fun feedFish(dirty: Int) = dirty + 10
    private fun anotherMethod(value: Int): Int {
        return 0
    }
    private fun updateDirty (dirty: Int, operation: (Int) -> Int): Int {
        return operation(dirty)
    }

    private fun sum() {
        {println("show")}()
    }

    private fun mainArgs() {
        val time = "12"
        println("Kotlin ${if (time.toInt() <= 12) "Good Morning" else "Good Night"}")
    }

    private fun feedTheFish() {
        val day = "Tuesday"
        val food = "Pellets"
        println("Today is ${randomDay()} and the fish eat ${fishFood(day)}.")
    }

    private fun fishFood(day: String): String {
        var food = "poison"

        when (day) {
            "M" -> food = "Super poison"
        }

        return food
    }

    private fun randomDay(): String {
        val week = listOf("M", "T", "W", "Th", "F", "S", "Su")
        return week[Random().nextInt(7)]
    }

    private fun getFortune() {
        print("Enter your birthday: ")
        var birth = readLine()?.toIntOrNull() ?: 0
        println("Wow: ${fortunes(birth)}")
    }

    private fun fortunes(select: Int?): String {
        if (select == null) {
            return ":("
        }

        val fortuneList = listOf(
                "You will have a great day!", "Things will go well for you today.", "Enjoy a wonderful day of success.",
                "Be humble and all will turn out well.", "Today is a good day for exercising restraint.",
                "Take it easy and enjoy life!"
        )

        if (select > fortuneList.size - 1) {
            return ":( too old."
        }

        var selected = when (select) {
            1, 2 -> 2
            in 3..28 -> 4
            else -> select.rem(fortuneList.size)
        }

        return fortuneList[selected]
    }

    private fun defaultParams(swim: String = "Slow") {
        println("Swim $swim")
    }

    private fun canAddFish(tankSize: Double, currentFish: List<Int>, fishSize: Int = 2, hasDecorations: Boolean = true): Boolean {
        return (tankSize * if (hasDecorations) 0.8 else 1.0) >= (currentFish.sum() + fishSize)
    }

    private fun whatShouldIDoToday(mood: String, weather: String = "sunny", temp: Int = 24): String {
        return when {
            mood == "happy" && weather == "sunny" -> "bang"
            mood == "sad" && weather == "rainy" && temp == 0 -> "stay in bed"
            temp > 35 -> "go swimming"
            else -> "stay"
        }
    }

    fun shouldChangeWater(
            day: String,
            temp: Int = 22,
            dirty: Int = 20
    ) : Boolean {
        val isTooHot = temp > 30

        var bubbles = 0
        while (bubbles < 30) {
            bubbles++
        }

        return when {
            isTooHot -> true
            else -> false
        }
    }

    fun getDirtySensorReading() = 20
}